insert into user (neptun_code, password, type) values ('student','$2a$10$3FcOqxi/gs6fzGfQDuyAUuC9Fmv.MfRk9QqEO73xBkI6nlq3PsbFa', 'REGISTERED');
insert into user (neptun_code, password, type) values ('asdf','$2a$10$frxttDeSRymMF./6yOxIA.GspOPGdQkPVQlXJN56HWHnrSPjdb9lK', 'REGISTERED');

insert into department(name,average_rating,does_contain_krisa) values ('numanal',3,true);
insert into department(name,average_rating,does_contain_krisa) values ('raptor',10,false);

insert into course(name,average_rating) values('linalg',3);
insert into course(name,average_rating) values('prog',5);
insert into course(name,average_rating) values('fonya',8);
insert into course(name,average_rating) values('dimat',5);
insert into course(name,average_rating) values('anal2',10);

insert into professor(name, average_rating, recommendation_count,department_id) values('Krisa',1,0,1);
insert into professor(name, average_rating, recommendation_count,department_id) values('Asdf',8,10,1);
insert into professor(name, average_rating, recommendation_count,department_id) values('Shrek',10,100,2);
insert into professor(name, average_rating, recommendation_count,department_id) values('AntiKrisa',10,1000,2);