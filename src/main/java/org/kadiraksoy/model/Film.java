package org.kadiraksoy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Film {

    //Ödül alan filmleri sisteme kayıt etmek için filmin adı ve ödülün adı, yönetmenin adı ve oyuncuların adları olmalıdır.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filmName;

    @ManyToOne (cascade = CascadeType.ALL)
    private Director director;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Actor> actors;

    @OneToMany (cascade = CascadeType.ALL)
    private List<FilmCategory> filmCategory;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Award> awards;

    public Film(String filmName, Director director, List<Actor> actors, List<FilmCategory> filmCategory, List<Award> awards) {
        this.filmName = filmName;
        this.director = director;
        this.actors = actors;
        this.filmCategory = filmCategory;
        this.awards = awards;
    }

    public Film(Integer id, String filmName, Director director, List<Actor> actors, List<FilmCategory> filmCategory, List<Award> awards) {
        this.id = id;
        this.filmName = filmName;
        this.director = director;
        this.actors = actors;
        this.filmCategory = filmCategory;
        this.awards = awards;
    }

    public Film(String filmName) {
        this.filmName = filmName;
    }
}
