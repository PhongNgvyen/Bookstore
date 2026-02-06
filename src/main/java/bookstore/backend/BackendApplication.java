package bookstore.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import bookstore.backend.domain.Book;
import bookstore.backend.domain.BookRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
	return (args) -> {
	  Book b1 = new Book("Testi", "Phong Nguyen", 2026, "12345");
		Book b2 = new Book("Testi2", "Lauri", 2025, "54321");
	  repository.save(b1);
		repository.save(b2);
	};
}

}
