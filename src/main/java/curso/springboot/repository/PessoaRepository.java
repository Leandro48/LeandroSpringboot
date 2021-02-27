package curso.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import curso.springboot.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	
	@Query("select p from Pessoa p where p.nome like %?1%")//anotação do jpa repository// %?1%" porque só tem 1 parâmetro, se tivesse mais colocaria and %?1%"
	List<Pessoa> findPessoaByName(String nome);//só criei essa linha para poder buscar no banco
}
