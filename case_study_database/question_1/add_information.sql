use furama_resort_management;

-- `Position` table
insert into `Position`
values
(001, 'Receptionist'),
(002, 'Service Staff'),
(003, 'Expert'),
(004, 'Supervisor'),
(005, 'Manager'),
(006, 'President');

select * from `Position`;

-- Qualification table
insert into Qualification
values
(001, 'Intermediate'),
(002, 'College'),
(003, 'University'),
(004, 'Postgraduate');

select * from Qualification;

-- Department table
insert into Department
values
(001, 'Sales-Marketing'),
(002, 'Administration'),
(003, 'Service'),
(004, 'Management');

select * from Department;