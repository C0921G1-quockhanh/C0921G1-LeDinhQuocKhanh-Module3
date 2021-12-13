use practice_student_management;

-- Lấy ra thông tin các học viên
select * from Student;

-- Lấy ra thông tin các học viên, và cho biết các học viên đang theo học lớp nào
select Student.StudentID, Student.StudentName, Class.ClassName
from Student
join Class on Student.ClassID = Class.ClassID;

-- Lấy ra thông tin các học viên, và cho biết các học viên đang theo học lớp nào, lớp đó thuộc loại lớp nào
select Student.StudentID, Student.StudentName, Class.ClassName, ClassType.ClassTypeName
from Student
join Class on Student.ClassID = Class.ClassID
join ClassType on Class.ClassTypeID = ClassType.ClassTypeID;

-- Lấy ra thông tin các học viên đang theo học tại các lớp kể cả các học viên không theo học lớp nào

-- Lấy thông tin của các học viên tên 'Tien' và 'Toan'
select *
from Student
where StudentName = 'tien' or StudentName = 'toan';

-- Lấy ra số lượng học viên của từng lớp
select count(Student.StudentID), Class.ClassName
from Student
join Class on Student.ClassID = Class.ClassID
group by Student.ClassID;

-- Lấy ra danh sách học viên và sắp xếp tên theo alphabet
select *
from Student
order by StudentName asc;