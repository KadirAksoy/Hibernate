package org.kadiraksoy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Aynı şekilde oyuncuyu kayıt edebilmek için oyuncunun adı, oynadığı filmin adı olmalıdır.
    private String actorName;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Film> films;

    @OneToMany (cascade = CascadeType.ALL)
    private List<Award> awards;

    public Actor(String actorName) {
        this.actorName = actorName;
    }

    public Actor(String actorName, List<Film> films, List<Award> awards) {
        this.actorName = actorName;
        this.films = films;
        this.awards = awards;
    }

    public Actor(Integer id, String actorName, List<Film> films, List<Award> awards) {
        this.id = id;
        this.actorName = actorName;
        this.films = films;
        this.awards = awards;
    }
}
