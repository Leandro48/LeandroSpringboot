package curso.springboot.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity //anotação entity para criar sozinho uma tabela no banco de dados
public class Pessoa implements Serializable{//toda classe que tem persistência implementa serializable

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id //toda classe de persistência precisa ser marcada com ID para o 
	@GeneratedValue(strategy = GenerationType.AUTO)//marca como gerador automatico de ID
	private Long id;
	
	private String nome;
	
	private String sobrenome;
	
	private int idade;
}
