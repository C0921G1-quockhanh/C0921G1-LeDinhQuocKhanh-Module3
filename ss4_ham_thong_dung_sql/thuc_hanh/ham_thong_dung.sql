use student_management;

select Address, count(StudentID)
from Student
group by Address;

select S.StudentID, S.StudentName, avg(Mark) as 'AverageScore'
from Student S
join Mark M on S.StudentID = M.StudentID
group by S.StudentID;

select S.StudentID, S.StudentName, avg(Mark) as 'AverageScore'
from Student S
join Mark M on S.StudentID = M.StudentID
group by S.StudentID
having AverageScore > 15;

select S.StudentID, S.StudentName, avg(Mark) as 'AverageScore'
from Student S
join Mark M on S.StudentID = M.StudentID
group by S.StudentID
having AverageScore >= all (
	select avg(Mark)
    from Mark
    group by Mark.StudentID
);