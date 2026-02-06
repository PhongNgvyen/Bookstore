package bookstore.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO )

    private long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;


    public Book(){

}

    public long getId(){ return id; }
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public int getPublicationYear() {return publicationYear;}
    public String getIsbn() {return isbn;}


    public void setId(long id) { this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setPublicationYear(int publicationYear) {this.publicationYear = publicationYear;}
    public void setIsbn(String isbn) {this.isbn = isbn;}


    public Book(String title, String author, int publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

}
