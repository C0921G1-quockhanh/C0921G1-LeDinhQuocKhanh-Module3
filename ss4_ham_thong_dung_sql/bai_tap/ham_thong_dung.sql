use student_management;

-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
select *
from `Subject`
where Credit = (
	select max(Credit)
	from `Subject`
);

-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
select S.SubID, S.SubName, M.Mark
from `Subject` S
join Mark M on S.SubID = M.SubID
where M.Mark = (
	select max(Mark)
	from Mark
);

-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần.
select Stu.StudentID, Stu.StudentName, avg(M.Mark)
from Student Stu
join Mark M on Stu.StudentID = M.StudentID
group by Stu.StudentID
order by avg(M.Mark) desc;