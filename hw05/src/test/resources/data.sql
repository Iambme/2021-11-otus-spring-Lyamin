insert into genre (`name`) values ('testGenreName1');
insert into genre (`name`) values ('testGenreName2');
insert into genre (`name`) values ('testGenreForDelete');

insert into author (first_name, last_name) values ('testAuthorFirstname1', 'TestAuthorLastname1');
insert into author (first_name, last_name) values ('testAuthorFirstname2', 'TestAuthorLastname2');
insert into author (first_name, last_name) values ('testAuthorForDelete', 'testAuthorForDelete');

insert into book (`name`, author_id, genre_id) values ('testBookTitle1', 1, 1);
insert into book (`name`, author_id, genre_id) values ('testBookTitle2', 2, 2);
