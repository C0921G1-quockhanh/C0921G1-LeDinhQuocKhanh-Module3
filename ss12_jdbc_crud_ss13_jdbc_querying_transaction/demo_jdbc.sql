drop database if exists demo_jdbc;

create database demo_jdbc;
use demo_jdbc;

create table users (
	id int(3) not null auto_increment primary key,
    `name` varchar(120) not null,
    email varchar(220) not null,
    country varchar(120)
);

insert into users(`name`,email,country)
values
('Minh','minh@codegym.vn','Viet Nam'),
('Kante','kante@che.uk','Kenia');

select *
from users;

DELIMITER //

create procedure get_user_by_id(in user_id int)
begin
	select *
    from users
    where id = user_id;
end //

DELIMITER ;

DELIMITER //

create procedure insert_user(in user_name varchar(50), in user_email varchar(50), in user_country varchar(50))
begin
	insert into users(`name`,email,country)
    values
    (user_name,user_email,user_country);
end //

DELIMITER ;

create table permission(
id int(11) primary key,
name varchar(50)
);

drop table if exists user_permission;

create table user_permission(
permision_id int(11),
user_id int(11),

foreign key (permision_id) references permission(id),
foreign key (user_id) references users(id)
);

insert into permission
values
(1, 'add'),
(2, 'edit'),
(3, 'delete'),
(4, 'view');

select *
from users;

select *
from user_permission;

select *
from employee;

DELIMITER //

create procedure display_all_users()
begin
	select *
    from users;
end //

DELIMITER ;

call display_all_users();

DELIMITER //

create procedure update_user(in user_name varchar(50), in user_email varchar(50), in user_country varchar(50), in user_id int)
begin
	update users
    set `name` = user_name, email = user_email, country = user_country
    where id = user_id;
end//

DELIMITER ;

DELIMITER //

create procedure delete_user(in user_id int)
begin
	delete from users
    where id = user_id;
end //

DELIMITER ;

alter table user_permission
drop foreign key user_permission_ibfk_2;

alter table user_permission
add constraint user_permission_user_fk
foreign key (user_id) references users(id) on delete cascade;

select *
from users;