
/*
	User inserts in the database for testing
*/
insert into user values (1,"Andreia","andreia#0202",null);
insert into user values (2,"Bruno","bruno#0102",null);
insert into user values (3,"Laura","laura#0212",null);
insert into user values (4,"Gastão","gastão#0332",null);
insert into user values (5,"Bruna","bruna#0202",null);
insert into user values (6,"Antonio","antonio#1202",null);
insert into user values (7,"Ricardo","ricardo#2202",null);
insert into user values (8,"Pablo","pablo#5202",null);
insert into user values (9,"Gustavo","gustavo#6662",null);
insert into user values (10,"John","john#5502",null);


select * from user;


/*
	Movies inserts in the database for testing
*/

INSERT INTO `db`.`movie`(`id_movie`,`background`,`category`,`duration`,`image`,`name`,`rateimdb`,`rate_meta`,`date_selected`,`year`,`season_id`,`user_id`) VALUES ("tt9764362",null,'["Drama"]',107,"https://m.media-amazon.com/images/M/MV5BMzdjNjI5MmYtODhiNS00NTcyLWEzZmUtYzVmODM5YzExNDE3XkEyXkFqcGdeQXVyMTAyMjQ3NzQ1._V1_.jpg","The Menu",7.3,71.0,CURRENT_TIMESTAMP,2022,1,1);
INSERT INTO `db`.`movie`(`id_movie`,`background`,`category`,`duration`,`image`,`name`,`rateimdb`,`rate_meta`,`date_selected`,`year`,`season_id`,`user_id`) VALUES ("tt1630029",null,'[\"Action\",\"Adventure\",\"Fantasy\",\"Sci-Fi\"]',192.0,"https://m.media-amazon.com/images/M/MV5BYjhiNjBlODctY2ZiOC00YjVlLWFlNzAtNTVhNzM1YjI1NzMxXkEyXkFqcGdeQXVyMjQxNTE1MDA@._V1_.jpg","Avatar: The Way of Water",7.9,67.0,CURRENT_TIMESTAMP,2022,1,2);

select * from movie;

/*
	Season inserts in the database for testing
*/

INSERT INTO `db`.`season`
(`id`,
`date_end`,
`date_start`,
`name`)
VALUES
(1,"2023-01-18","2023-01-25","Season 1");
INSERT INTO `db`.`season`
(`id`,
`date_end`,
`date_start`,
`name`)
VALUES
(2,"2023-01-26","2023-02-02","Season 2");

select * from season
