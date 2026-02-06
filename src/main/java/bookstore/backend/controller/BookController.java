package bookstore.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bookstore.backend.domain.BookRepository;
import bookstore.backend.domain.Book;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/booklist")
    public String getBookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
        }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }

    @GetMapping("/add")
    public String addBooks(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/save")
    public String saveBooks(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id).orElse(null);
        model.addAttribute("book", book);
            return "editbook";
    }

    @PostMapping("/edit")
    public String updateBook(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }
}

