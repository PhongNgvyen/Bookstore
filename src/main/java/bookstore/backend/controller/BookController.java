package bookstore.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import bookstore.backend.domain.BookRepository;
import bookstore.backend.domain.CategoryRepository;
import bookstore.backend.domain.Book;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;


@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;

    @GetMapping("/booklist")
    public String getBookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
        }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }

    @GetMapping("/add")
    public String addBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
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
        model.addAttribute("categories", crepository.findAll());
            return "editbook";
    }

    @PostMapping("/edit")
    public String updateBook(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

// RESTful service to get all books
    @RequestMapping(value = "/books", method = RequestMethod.GET)
        public @ResponseBody List<Book> findAllBooksRest(){
            return(List<Book>) repository.findAll();
        }

// RESTful service to get book by ID
    @RequestMapping(value = "/books/{id}", method=RequestMethod.GET)
    public @ResponseBody Optional<Book> getOneBookRest(@PathVariable(name = "id") Long id){
        return repository.findById(id);
        }

// RESTful service to save new student
    @RequestMapping(value="/books", method = RequestMethod.POST)
    public @ResponseBody Book saveBooksRest(@RequestBody Book book) {
    	return repository.save(book);
    }

// RESTful service to update a book by its ID
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBookRest(@PathVariable Long id, @RequestBody Book updatedBook){
        return repository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setPublicationYear(updatedBook.getPublicationYear());
            return ResponseEntity.ok(repository.save(book));
        })
        .orElse(ResponseEntity.notFound().build());
    }

// RESTful service to delete a book by its ID
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookRest(@PathVariable Long id){
        return repository.findById(id).map(book -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

// login method
    @GetMapping("/login")
    public String login() {
        return "login";
}

}
