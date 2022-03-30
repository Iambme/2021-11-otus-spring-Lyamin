package ru.otus.lyamin.app.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
@SecondaryTable(name = "mongo_author", pkJoinColumns = @PrimaryKeyJoinColumn(name = "author_id"))
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "mongo_id", table = "mongo_author")
    private String mongoId;

}
