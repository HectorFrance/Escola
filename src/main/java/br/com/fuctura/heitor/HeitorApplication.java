package br.com.fuctura.heitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableSpringDataWebSupport
@EnableCaching
public class HeitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeitorApplication.class, args);

	}

	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "<h1 style=\"text-align:center\">Parabéns Aluno, ta tudo certo</h1>";
	}
}
