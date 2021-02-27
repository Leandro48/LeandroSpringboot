package curso.springboot.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan(basePackages="curso.springboot.model")//anotação para o springboot scanear as entidades criadas, informar pacote
@ComponentScan(basePackages={"curso.*"}) //força o springboot a mapear todos os componentes do projeto
@EnableJpaRepositories(basePackages={"curso.springboot.repository"})
@EnableTransactionManagement
@EnableSwagger2

public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
