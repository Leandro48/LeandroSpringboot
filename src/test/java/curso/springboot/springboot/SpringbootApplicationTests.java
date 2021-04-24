package curso.springboot.springboot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import curso.springboot.model.Pessoa;
import curso.springboot.repository.PessoaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class SpringbootApplicationTests {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	void testeEntrada() {
		
		Pessoa pessoaModel = new Pessoa();
		pessoaModel.setNome("Leadro");
		pessoaModel.setSobrenome("Martins");
		pessoaModel.setIdade(29);
		pessoaRepository.save(pessoaModel);
		assertEquals(pessoaRepository.save(pessoaModel), pessoaRepository.save(pessoaModel), "O objeto pessoa precisa ser preenchido");
	}

}
