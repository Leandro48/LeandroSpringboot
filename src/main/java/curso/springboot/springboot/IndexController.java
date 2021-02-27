package curso.springboot.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//marca como controller do springboot
@Controller
public class IndexController {
	//interceptará requisições de uma url
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
