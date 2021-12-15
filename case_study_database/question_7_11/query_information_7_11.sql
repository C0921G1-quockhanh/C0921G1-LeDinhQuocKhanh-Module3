use furama_resort_management;

/* 7.	Hiển thị thông tin ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ten_loai_dich_vu
 của tất cả các loại dịch vụ đã từng được khách hàng đặt phòng trong năm 2020 nhưng chưa từng được khách hàng đặt phòng trong năm 2021. */
select Ser.service_id, Ser.service_name, Ser.service_area, Ser.max_people, Ser.rental_cost, SerT.service_type_name
from service Ser
join service_type SerT on SerT.service_type_id = Ser.service_type_id
join contract Contr on Ser.service_id = Contr.service_id
where Ser.service_id not in (
	select service_id
	from contract
	where year(start_date) = 2021 or year(end_date) = 2021
) and (year(Contr.start_date) = 2020 or year(Contr.end_date) = 2020)
group by Ser.service_id;

-- 8.	Hiển thị thông tin ho_ten khách hàng có trong hệ thống, với yêu cầu ho_ten không trùng nhau.
-- Option 1
select customer_name from customer
union
select customer_name from customer;

-- Option 2
select customer_name
from customer
group by customer_name;

-- Option 3
select distinct customer_name
from customer;

-- 9.	Thực hiện thống kê doanh thu theo tháng, nghĩa là tương ứng với mỗi tháng trong năm 2021 thì sẽ có bao nhiêu khách hàng thực hiện đặt phòng.
