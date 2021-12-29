package ru.otus.lyamin.app.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {
    @SuppressWarnings("JpaDataSourceORMInspection")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @SuppressWarnings("JpaDataSourceORMInspection")
    @Column(name = "name")
    private String name;

    public Genre(String name) {
        this(null, name);
    }

}
