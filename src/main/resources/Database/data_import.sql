insert into user (last_name, name) values ('Ivanov', 'Ivan');
insert into user (last_name, name) values ('Martin', 'Robert');
insert into user (last_name, name) values ('Fauler', 'Martin');
insert into user (last_name, name) values ('Goslin', 'James');
insert into user (last_name, name) values ('Ranolds', 'Raion');
insert into user (last_name, name) values ('Geits', 'Bill');

insert into role (name) values ('admin');
insert into role (name) values ('user');
insert into role (name) values ('teacher');
insert into role (name) values ('superadmin');
insert into role (name) values ('moderator');
insert into role (name) values ('manager');


insert into user_role (registr_date, role_id, user_id) values ('2021-02-10 22:21:58', 1, 1);
insert into user_role (registr_date, role_id, user_id) values ('2021-02-10 22:21:58', 2, 1);
insert into user_role (registr_date, role_id, user_id) values ('2021-04-10 22:21:58', 3, 2);
insert into user_role (registr_date, role_id, user_id) values ('2021-05-10 22:21:58', 4, 2);
insert into user_role (registr_date, role_id, user_id) values ('2021-06-10 22:21:58', 1, 3);
insert into user_role (registr_date, role_id, user_id) values ('2021-07-10 22:21:58', 2, 3);
insert into user_role (registr_date, role_id, user_id) values ('2021-08-10 22:21:58', 5, 4);
insert into user_role (registr_date, role_id, user_id) values ('2021-09-10 22:21:58', 2, 5);
insert into user_role (registr_date, role_id, user_id) values ('2021-10-10 22:21:58', 3, 6);
insert into user_role (registr_date, role_id, user_id) values ('2021-11-10 22:21:58', 4, 6);