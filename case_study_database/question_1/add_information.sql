use furama_resort_management;

-- `position` table
insert into `position`
values
(001, 'Receptionist'),
(002, 'Service Staff'),
(003, 'Expert'),
(004, 'Supervisor'),
(005, 'Manager'),
(006, 'President');

select * from `position`;

-- qualification table
insert into qualification
values
(001, 'Intermediate'),
(002, 'College'),
(003, 'University'),
(004, 'Postgraduate');

select * from qualification;

-- department table
insert into department
values
(001, 'Sales-Marketing'),
(002, 'Administration'),
(003, 'Service'),
(004, 'Management');

select * from department;