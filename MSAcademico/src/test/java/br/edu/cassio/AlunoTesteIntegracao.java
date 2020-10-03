package br.edu.cassio;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.cassio.entidades.Aluno;
import br.edu.cassio.entidades.Endereco;
import br.edu.cassio.repository.AlunoRepository;
import br.edu.cassio.repository.EnderecoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoTesteIntegracao {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

//	@Test
	public void salvarAluno() throws Exception {
		Aluno aluno = new Aluno();
		aluno.setNome("Jonathan");
		aluno.setCpf("234234");
		Endereco e = new Endereco();
		e.setBairro("centro");
		e.setCidade("Concordia");
		e.setRua("Rua Paulista, 4253");
		e.setCep("80799-000");
		e.setAluno(aluno);
		aluno.setEndereco(e);
		alunoRepository.save(aluno);
		assertNotNull(aluno.getId());
      }
	
	@Test
	public void encontrarAlunoNome () throws Exception{
		Aluno a = alunoRepository.findAlunosByNome("Axel");
		
		assertNotNull(a.getNome().equals("Axel"));
	}
}