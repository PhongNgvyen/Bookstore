package bookstore.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import bookstore.backend.domain.AppUser;
import bookstore.backend.domain.AppUserRepository;
import bookstore.backend.domain.Book;
import bookstore.backend.domain.BookRepository;
import bookstore.backend.domain.Category;
import bookstore.backend.domain.CategoryRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository brepository,
								  CategoryRepository crepository,
								  AppUserRepository urepository,
								  PasswordEncoder passwordEncoder) {
		return (args) -> {
			Category c1 = new Category("Math");
			Category c2 = new Category("History");
			Category c3 = new Category("IT");

			crepository.save(c1);
			crepository.save(c2);
			crepository.save(c3);

			Book b1 = new Book("Testi", "Phong Nguyen", 2026, "12345");
			b1.setCategory(c1);

			Book b2 = new Book("Testi2", "zzzz", 2025, "54321");
			b2.setCategory(c3);

			brepository.save(b1);
			brepository.save(b2);

			if (urepository.findByUsername("user") == null) {
				urepository.save(new AppUser(
						"user",
						passwordEncoder.encode("user123"),
						"user@email.com",
						"ROLE_USER"
				));
			}

			if (urepository.findByUsername("admin") == null) {
				urepository.save(new AppUser(
						"admin",
						passwordEncoder.encode("admin123"),
						"admin@email.com",
						"ROLE_ADMIN"
				));
			}
		};
	}
}