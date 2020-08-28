package br.edu.cassio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody;

@Controller     
@RequestMapping(path="/turma")  
public class TurmaController {
	@GetMapping(path="/todos")
	public @ResponseBody List<Turma> getTodos() {
		Turma t1 = new Turma("ENGSOFT2", 2020, 4);
		Turma t2 = new Turma("ENGSOFT6", 2020, 6);
		ArrayList<Turma> lstTurmas = new ArrayList<>();
		lstTurmas.add(t1);
		lstTurmas.add(t2);
		return lstTurmas;
	}
}