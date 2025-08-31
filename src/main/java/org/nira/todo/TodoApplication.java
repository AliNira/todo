package org.nira.todo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.nira.todo.entity.Role;
import org.nira.todo.entity.User;
import org.nira.todo.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public CommandLineRunner initAdmin(UserRepo userRepository) {
        return args -> {
            if (!userRepository.findByEmailOrUsername("dev.erfaniii@gmail.com","Admin_0").isPresent()) {
                User admin = new User();
                admin.setName("Ali Nira");
                admin.setUsername("Admin_0");
                admin.setEmail("dev.erfaniii@gmail.com");
                admin.setPassword("$2a$12$lL/qN.e4DZizxaxDaHcWdeFzNUNckYaQrQqcGxQXAQQBXAarYvoCG");
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
            }
        };
    }


}
