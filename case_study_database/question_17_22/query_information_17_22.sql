use furama_resort_management;

/* 17.	Cập nhật thông tin những khách hàng có ten_loai_khach từ Platinum lên Diamond,
chỉ cập nhật những khách hàng đã từng đặt phòng với Tổng Tiền thanh toán trong năm 2021 là lớn hơn 10.000.000 VNĐ */
update customer
set customer_type_id = 1
where customer_id in (
	select customer_id
    from (
		select C.customer_id
		from customer C
		join customer_type CT on CT.customer_type_id = C.customer_type_id
		join contract Contr on C.customer_id = Contr.customer_id
		join service Ser on Ser.service_id = Contr.service_id
		join detail_contract DContr on DContr.contract_id = Contr.contract_id
		join accompanied_service ASer on ASer.accompanied_service_id = DContr.accompanied_service_id
		where CT.customer_type_name = 'Platinium' and year(Contr.start_date) = 2021
		group by C.customer_id
		having sum(coalesce(Ser.rental_cost,0) + coalesce(ASer.price,0) * coalesce(DContr.quantity)) > 1000000
    ) as tmp
);

select *
from customer;

-- 18.	Xóa những khách hàng có hợp đồng trước năm 2021 (chú ý ràng buộc giữa các bảng).
delete from customer
where customer_id in (
	select customer_id
    from (
		select C.customer_id
		from customer C
		join contract Contr on C.customer_id = Contr.customer_id
		where year(Contr.start_date) < 2021
		group by C.customer_id
    ) as tmp
);

select *
from customer;

-- 19.	Cập nhật giá cho các dịch vụ đi kèm được sử dụng trên 10 lần trong năm 2020 lên gấp đôi
set sql_safe_updates = 0;

update accompanied_service
set price = price * 2
where accompanied_service_id in (
	select accompanied_service_id
    from (
		select ASer.accompanied_service_id
		from contract Contr
		join detail_contract DContr on Contr.contract_id = DContr.contract_id
		join accompanied_service ASer on ASer.accompanied_service_id = DContr.accompanied_service_id
		where year(Contr.start_date) = 2020 or year(Contr.end_date) = 2020
		group by ASer.accompanied_service_id
		having sum(DContr.quantity) > 10
    ) as tmp
);

select *
from accompanied_service;

/* 20.	Hiển thị thông tin của tất cả các nhân viên và khách hàng có trong hệ thống, thông tin hiển thị bao gồm
id (ma_nhan_vien, ma_khach_hang),ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi. */
select employee_id, employee_name, email, phone_number, date_of_birth, address
from employee
union
select customer_id, customer_name, email, phone_number, date_of_birth, address
from customer;

/* 21.	Tạo khung nhìn có tên là v_nhan_vien để lấy được thông tin của tất cả các nhân viên có địa chỉ là “Đà Nẵng” hoặc “Huế” và đã từng lập hợp đồng cho một hoặc
nhiều khách hàng bất kì với ngày lập hợp đồng là “02/09/2021”. */
create view view_employee
as
select E.employee_id, E.employee_name, E.phone_number, E.email, E.address
from employee E
join contract Contr on E.employee_id = Contr.employee_id
where (E.address like '%Đà Nẵng' or E.address like '%Huế') and Contr.start_date = str_to_date('September 2 2021','%M %d %Y')
group by E.employee_id;

select *
from view_employee;

-- 22.	Thông qua khung nhìn v_nhan_vien thực hiện cập nhật địa chỉ thành “Hà Nội” đối với tất cả các nhân viên được nhìn thấy bởi khung nhìn này.