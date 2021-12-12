create database practice_student_management;

use practice_student_management;

create table ClassType (
	ClassTypeID int primary key,
    ClassTypeName varchar(20)
);

create table Class (
	ClassID int primary key,
    ClassName varchar(10),
    ClassTypeID int,
    
    foreign key(ClassTypeID)
		references ClassType(ClassTypeID)
);

create table `Account` (
	AccountName varchar(50) primary key,
    AccountPassword varchar(50)
);

create table Student (
	StudentID int auto_increment primary key,
    StudentName varchar(50),
    DatOfBirth date,
    Email varchar(50),
    ClassID int,
    AccountName varchar(50),
    
    foreign key(ClassID)
		references Class(ClassID),
	foreign key(AccountName)
		references `Account`(AccountName)
);

create table Instructor (
	InstructorID int auto_increment primary key,
    InstructorName varchar(50),
    DateOfBirth date,
    Email varchar(50)
);

create table InstructorTeachClass (
	ClassID int,
    InstructorID int,
    
    primary key(ClassID, InstructorID),
    foreign key(ClassID)
		references Class(ClassID),
	foreign key(InstructorID)
		references Instructor(InstructorID)
);