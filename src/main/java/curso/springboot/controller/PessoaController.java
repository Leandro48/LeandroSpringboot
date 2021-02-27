package curso.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.model.Pessoa;
import curso.springboot.repository.PessoaRepository;

@RequestMapping("/api")
@Controller
public class PessoaController {
	//fazendo a injeção de dependência da Interface PessoaRepository
	@Autowired
	private PessoaRepository pessoaRepository;
	
	//metodo get para consultas de informações
	@RequestMapping(method=RequestMethod.GET, value="/cadastropessoa")//quando chamar esse link, será interceptado para cá
	public ModelAndView inicio() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoaobj", new Pessoa());//passando um objeto vazio para iniciar a tela, ela espera um objeto quando abre o formulário
		return andView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvarpessoa")//value="**/salvarpessoa" para ignorar o que vir antes de salvarpesosa na url
	public ModelAndView salvar(Pessoa pessoa) {//sempre que for passar um objeto para url, se usa o modelaandview
		pessoaRepository.save(pessoa);
		
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();//cria uma lista
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");//("cadastro/cadastropessoa") indicando que quero que ele mostre a mesma tela de cadastro
		andView.addObject("pessoas", pessoasIt);//(atributo pessoas(onde vai injetar os dados), a lista preenchida > pessoasIt)
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	@RequestMapping(method=RequestMethod.GET, value="/listapessoas")
	public ModelAndView pessoas() {
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();//cria uma lista
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");//("cadastro/cadastropessoa") indicando que quero que ele mostre a mesma tela de cadastro
		andView.addObject("pessoas", pessoasIt);//(atributo pessoas(onde vai injetar os dados), a lista preenchida > pessoasIt)
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	@GetMapping("/editarpessoa/{idpessoa}") //mapeando a url, depois lá em baixo consultou o objeto e colocou ele em edição passando pra view 
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {//no @PathVariable o idpessoa vai receber o valor do idque foi encontrado no formulário
		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");//("cadastro/cadastropessoa") tela que vai ser retornada
		andView.addObject("pessoaobj", pessoa.get());
		return andView;
	}
	@GetMapping("/removerpessoa/{idpessoa}") //mapeando a url, depois lá em baixo consultou o objeto e colocou ele em edição passando pra view 
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {//no @PathVariable o idpessoa vai receber o valor do idque foi encontrado no formulário
		pessoaRepository.deleteById(idpessoa);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");//("cadastro/cadastropessoa") tela que vai ser retornada
		andView.addObject("pessoas", pessoaRepository.findAll());//dessa vez eu não quero retornar um objeto vazio, mas sim os objetos que ficaram no banco, já que exclui um deles, quero ver os que restaram
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	@PostMapping("**/pesquisarpessoa")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoas", pessoaRepository.findPessoaByName(nomepesquisa));
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
}
