use furama_resort_management;

-- 2.	Hiển thị thông tin của tất cả nhân viên có trong hệ thống có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
select *
from employee
where (employee_name like 'H%' or employee_name like 'T%' or employee_name like 'K%') and char_length(employee_name) <= 15;

-- 3.	Hiển thị thông tin của tất cả khách hàng có độ tuổi từ 18 đến 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
select *
from customer
where (abs(datediff(curdate(),date_of_birth)) / 365 between 18 and 50) and (address like '%Đà Nẵng' or address like '%Quảng Trị');

/* 4.	Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần. Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng. 
Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”. */

select C.customer_id, C.customer_name,CT.customer_type_name, count(Contr.contract_id) as number_of_bookings
from customer C
join customer_type CT on CT.customer_type_id = C.customer_type_id
join contract Contr on C.customer_id = Contr.customer_id
where CT.customer_type_name = 'Diamond'
group by C.customer_id
order by number_of_bookings asc;

/* 5.	Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien 
(Với tổng tiền được tính theo công thức như sau: Chi Phí Thuê + Số Lượng * Giá, với Số Lượng và Giá là từ bảng dich_vu_di_kem, hop_dong_chi_tiet) 
cho tất cả các khách hàng đã từng đặt phòng. (những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra). */
select C.customer_id, C.customer_name, CT.customer_type_name, Contr.contract_id, Ser.service_name, Contr.start_date, Contr.end_date, sum((coalesce(Ser.rental_cost,0) + coalesce(ASer.price,0) * coalesce(DContr.quantity,0))) as TotalPrice
from customer C
join customer_type CT on CT.customer_type_id = C.customer_type_id
left join contract Contr on C.customer_id = Contr.customer_id
left join service Ser on Ser.service_id = Contr.service_id
left join detail_contract DContr on Contr.contract_id = DContr.contract_id
left join accompanied_service ASer on ASer.accompanied_service_id = DContr.accompanied_service_id
group by C.customer_id, Ser.service_name
order by C.customer_id asc;

/* 6.	Hiển thị ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ten_loai_dich_vu của tất cả các loại 
dịch vụ chưa từng được khách hàng thực hiện đặt từ quý 1 của năm 2021 (Quý 1 là tháng 1, 2, 3). */
select Ser.service_id, Ser.service_name, Ser.service_area, Ser.rental_cost, SerT.service_type_name
from service Ser
join service_type SerT on SerT.service_type_id = Ser.service_type_id
join contract Contr on Ser.service_id = Contr.service_id
where Ser.service_id not in (
	select service_id
	from contract
    where not(end_date < str_to_date('January 1 2021','%M %d %Y') or start_date > str_to_date('March 31 2021','%M %d %Y'))
)
group by Ser.service_id;