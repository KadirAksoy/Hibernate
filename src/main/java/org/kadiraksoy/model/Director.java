package org.kadiraksoy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Ödül alan yönetmeni sisteme kayıt etmek için adı, çektiği film ve aldığı ödülün adı olmalıdır.
    private String directorName;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Film> films;

    @OneToOne (cascade = CascadeType.ALL)
    private Award award;

    public Director(String directorName) {
        this.directorName = directorName;
    }

    public Director(String directorName, List<Film> films, Award award) {
        this.directorName = directorName;
        this.films = films;
        this.award = award;
    }

    public Director(Integer id, String directorName, List<Film> films, Award award) {
        this.id = id;
        this.directorName = directorName;
        this.films = films;
        this.award = award;
    }
}