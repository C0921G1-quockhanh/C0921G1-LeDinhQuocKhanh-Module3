create database score_management;

use score_management;

create table Student (
	StudentCode varchar(20) primary key,
    StudentName varchar(50),
    DateOfBirth datetime,
    Class varchar(20),
    Sex varchar(20)
);

create table `Subject` (
	SubjectCode varchar(20) primary key,
    SubjectName varchar(50)
);

create table AcademicTranscript (
	StudentCode varchar(20),
    SubjectCode varchar(20),
    Score int,
    TestDay datetime,
    
    primary key(StudentCode, SubjectCode),
    foreign key (StudentCode)
		references Student(StudentCode),
	foreign key (SubjectCode)
		references `Subject`(SubjectCode)
);

create table Teacher (
	TeacherCode varchar(20) primary key,
    TeacherName varchar(20),
    PhoneNumber varchar(10)
);

alter table `Subject`
	add TeacherCode varchar(20);
    
alter table `Subject`
	add constraint FK_TeacherCode foreign key(TeacherCode) references Teacher(TeacherCode);



    