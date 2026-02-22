package bookstore.backend.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long category_id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "category")
    private List<Book> books;

     public Category(){

}

     public Category(String name){
        super();
        this.name = name;
}

public Long getCategoryId() { return category_id; }
public String getName() { return name; }

public void setCategoryId(Long category_id) { this.category_id = category_id; }
public void setName(String name) { this.name = name; }

public List<Book> getBooks(){
    return books;
}

public void setBooks(List<Book> books){
    this.books = books;
}

}
