package br.edu.cassio.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.edu.cassio.dto.AlunoDTO;
import br.edu.cassio.dto.ClienteInadimplenteDTO;
import br.edu.cassio.dto.ParcelaDTO;
import br.edu.cassio.feign.AlunoProxy;
import br.edu.cassio.feign.FinanceiroProxy;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

@Controller
@RequestMapping(path = "/integracaoFinanceira")
@ComponentScan

@Slf4j

public class IntegracaoController {

	@Autowired
	LoadBalancerClient loadBalancer;

	@Autowired
	private AlunoProxy alunoProxy;

	@Autowired
	private FinanceiroProxy financeiroProxy;

	@Autowired
	private ApplicationContext appContext;

	@RequestMapping(value = "/parcelas", method = RequestMethod.GET)
	public ModelAndView parcelas() {

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("/alunos");

		return modelAndView;
	}

	@RequestMapping(value = "alunos", method = RequestMethod.GET)
	@ResponseBody
	public void alunosInad(HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = getClass().getResourceAsStream("/alunos.jasper");
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=alunos.pdf");
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}

	@GetMapping("/findAlunoByIdFeign/{id}")
	public @ResponseBody AlunoDTO findAlunoFeign(@PathVariable int id) {
		return alunoProxy.pegarDados(id);
	}

	@GetMapping("/parcelasInadimplentes")
	public @ResponseBody List<ClienteInadimplenteDTO> parcelasInacimpletes() {
		List<ParcelaDTO> lstParcelas = financeiroProxy.todasParcelasInadimplentes();

		ArrayList<ClienteInadimplenteDTO> lstInadim = new ArrayList<>();
		for (ParcelaDTO p : lstParcelas) {
			AlunoDTO a = alunoProxy.pegarDados(p.getAlunoId());
			long diferencaData = diferencaData(p);
			ClienteInadimplenteDTO cli = new ClienteInadimplenteDTO(a.getNome(), a.getCpf(), p.getDataVencimento(),
					diferencaData, p.getDesconto(), p.getAcrescimo(), p.getNumero());
			lstInadim.add(cli);
		}
		return lstInadim;
	}

	private long diferencaData(ParcelaDTO p) {
		LocalDate dataAtual = LocalDate.now();
		long diff = dataAtual.toEpochDay() - p.getDataVencimento().toEpochDay();
		return diff;
	}

}