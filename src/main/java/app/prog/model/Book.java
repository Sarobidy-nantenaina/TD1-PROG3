package app.prog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    /*
    TODO-4-a: Note that i use serial type for ID in database. What does serial do ?
    TODO-4-b: Should I map it with int ? Fix it if there is a problem
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pageNumber;
    @CreatedDate
    private LocalDate releaseDate;

    public boolean hasAuthor() {
        return author != null;
    }

}
