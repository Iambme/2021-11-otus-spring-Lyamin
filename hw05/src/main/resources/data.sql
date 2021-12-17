insert into genre (`name`) values ('Horror');
insert into genre (`name`) values ('Detective');

insert into author (first_name, last_name) values ('Stephen', 'King');
insert into author (first_name, last_name) values ('Agatha', 'Сhristie');

insert into book (`name`, author_id, genre_id) values ('It', 1, 1);
insert into book (`name`, author_id, genre_id) values ('Ten Little Niggers', 2, 2);
