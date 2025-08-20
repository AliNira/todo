package org.nira.todo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Todo API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Ali Nira",
                        email = "dev.erfaniii@gmail.com",
                        url = "https://www.linkedin.com/in/ali-nira-49425a21b/"
                )
        )
)
@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}


}
