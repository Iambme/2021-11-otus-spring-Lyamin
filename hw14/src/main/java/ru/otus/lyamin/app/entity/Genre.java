package ru.otus.lyamin.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genre")
@SecondaryTable(name = "mongo_genre", pkJoinColumns = @PrimaryKeyJoinColumn(name = "genre_id"))
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mongo_id", table = "mongo_genre")
    private String mongoId;


}
