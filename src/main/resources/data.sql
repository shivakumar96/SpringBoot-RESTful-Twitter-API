insert into user_details (id,birth_date,name)
values (1001,current_date(),'Shivakumar');

insert into user_details (id,birth_date,name)
values (1002,current_date(),'Raghava');

insert into user_details (id,birth_date,name)
values (1003,current_date(),'Rajaram');

insert into post (id, user_id, description)
values (2001,1001,'Learn Java');

insert into post (id, user_id, description)
values (2002,1001,'Learn AWS');

insert into post (id, user_id, description)
values (2003,1002,'Learn Spring');

insert into post (id, user_id, description)
values (2004,1003,'Learn Docker');

insert into post (id, user_id, description)
values (2005,1003,'Learn Kubernetes');