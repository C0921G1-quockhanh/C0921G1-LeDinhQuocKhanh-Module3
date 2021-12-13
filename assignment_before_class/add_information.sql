use practice_student_management;

insert into ClassType
values
(001, 'Full-time'),
(002, 'Part-time');

select * from ClassType;

insert into Class
values
(001, 'C0921G1', 001),
(002, 'C1021G1', 001),
(003, 'A1021G1', 002);

select * from Class;

insert into `Account`
values
('toan@gmail.com', 'toan12345'),
('hung@gmail.com', 'hung12345'),
('tien@gmail.com', 'tien12345'),
('huyen@gmail.com', 'huyen12345');

select * from `Account`;

insert into Student (StudentName, DatOfBirth, Email, ClassID, AccountName)
values
('toan','1997-01-01','toan@gmail.com',001,'toan@gmail.com'),
('hung','1997-02-04','hung@gmail.com',003,'hung@gmail.com'),
('tien','1997-05-09','tien@gmail.com',002,'tien@gmail.com'),
('huyen','1995-02-10','huyen@gmail.com',001,'huyen@gmail.com');

select * from Student;

insert into Instructor (InstructorName, DateOfBirth, Email)
values
('trung','1993-05-05','trung@gmail.com'),
('chien','1994-04-10','chien@gmail.com');

select * from Instructor;

insert into InstructorTeachClass
values
(001,001),
(002,002),
(003,002);

select * from InstructorTeachClass;