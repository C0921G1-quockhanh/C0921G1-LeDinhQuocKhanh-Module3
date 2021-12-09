create database student_management;

use student_management;

create table Student(
	StudentID int,
    `Name` varchar(45),
    Age int,
    Country varchar(45),
    primary key(StudentID)
);

create table Class(
	ClassID int,
    `Name` varchar(45),
    primary key(ClassID)
);

create table Teacher(
	TeacherID int,
    `Name` varchar(45),
    Age int,
    Country varchar(45),
    primary key(TeacherID)
);
