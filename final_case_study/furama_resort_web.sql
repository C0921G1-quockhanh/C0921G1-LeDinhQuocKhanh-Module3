drop database if exists furama_resort_web;

create database furama_resort_web;
use furama_resort_web;

create table `position` (
	position_id int primary key,
    position_name varchar(45)
);

create table qualification (
	qualification_id int primary key,
    qualification_name varchar(45)
);

create table department (
	department_id int primary key,
    department_name varchar(45)
);

create table `user` (
	username varchar(255) primary key,
    `password` varchar(255)
);

create table `role` (
	role_id int primary key,
    role_name varchar(255)
);

create table user_role (
	role_id int,
    username varchar(255),
    
    foreign key(role_id)
		references `role`(role_id),
	foreign key(username)
		references `user`(username)
);

create table employee (
	employee_id int auto_increment primary key,
    employee_name varchar(45),
    date_of_birth date,
    identity_number varchar(45),
    salary double,
    phone_number varchar(45),
    email varchar(45),
    address varchar(45),
    position_id int,
    qualification_id int,
    department_id int,
    username varchar(255),
    
    foreign key(position_id)
		references `position`(position_id),
    foreign key(qualification_id)
		references qualification(qualification_id),
	foreign key(department_id)
		references department(department_id),
	foreign key(username)
		references `user`(username)
);

create table customer_type (
	customer_type_id int primary key,
    customer_type_name varchar(45)
);

create table customer (
	customer_id int auto_increment primary key,
    customer_type_id int,
    customer_name varchar(45),
    date_of_birth date,
    sex bit(1),
    identity_number varchar(45),
    phone_number varchar(45),
    email varchar(45),
    address varchar(45),
    
    foreign key(customer_type_id)
		references customer_type(customer_type_id)
);

create table rental_type (
	rental_type_id int primary key,
    rental_type_name varchar(45)
);

create table service_type (
	service_type_id int primary key,
    service_type_name varchar(45)
);

create table service (
	service_id int auto_increment primary key,
    service_name varchar(45),
    service_area int,
    rental_cost double,
    max_people int,
    rental_type_id int,
    service_type_id int,
    room_standard varchar(45),
    extra_amenity varchar(45),
    pool_area double,
    levels int,
    
    foreign key(rental_type_id)
		references rental_type(rental_type_id),
	foreign key(service_type_id)
		references service_type(service_type_id)
);

create table contract (
	contract_id int auto_increment primary key,
    start_date datetime,
    end_date datetime,
    deposit double,
    employee_id int,
    customer_id int,
    service_id int,
    
    foreign key(employee_id)
		references employee(employee_id),
	foreign key(customer_id)
		references customer(customer_id),
	foreign key(service_id)
		references service(service_id)
);

alter table contract
drop foreign key contract_ibfk_2;

alter table contract
add constraint contract_customer_fk
	foreign key(customer_id)
		references customer(customer_id) on delete cascade;

alter table contract
drop foreign key contract_ibfk_1;

alter table contract
add constraint contract_employee_fk
	foreign key (employee_id)
		references employee(employee_id) on delete cascade;

create table accompanied_service (
	accompanied_service_id int auto_increment primary key,
    accompanied_service_name varchar(45),
    price double,
    unit varchar(10),
    `status` varchar(45)
);

create table detail_contract (
	detail_contract_id int auto_increment primary key,
    contract_id int,
    accompanied_service_id int,
    quantity int,
    
    foreign key(contract_id)
		references contract(contract_id),
	foreign key(accompanied_service_id)
		references accompanied_service(accompanied_service_id)
);

alter table detail_contract
drop foreign key detail_contract_ibfk_1;

alter table detail_contract
add constraint detail_contract_contract_fk
	foreign key (contract_id)
		references contract(contract_id) on delete cascade;