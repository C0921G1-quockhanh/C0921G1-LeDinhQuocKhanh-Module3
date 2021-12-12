use student_management;

select * from Student;

select *
from Student
where `Status` = true;

select *
from `Subject`
where Credit < 10;

select Student.StudentID, Student.StudentName, Class.ClassName
from Student join Class on Student.ClassID = Class.ClassID
where Class.ClassName = 'A1';

select Student.StudentID, Student.StudentName, `Subject`.SubName, Mark.Mark
from Student
join Mark on Mark.StudentID = Student.StudentID
join `Subject` on Mark.SubID = `Subject`.SubID
where `Subject`.SubName = 'CF';