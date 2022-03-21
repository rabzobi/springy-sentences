package garland.co.za.springy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SentenceTools {

	public static void main(String[] args) {
		SpringApplication.run(SentenceTools.class, args);
	}

	@GetMapping("/validate")
	public boolean hello(@RequestParam(value = "sentence", defaultValue = "") String sentence) {
		return Sentence.checkValidity(sentence);
	}
	
	
}