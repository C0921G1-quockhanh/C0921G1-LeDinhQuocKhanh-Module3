use furama_resort_management;

/* 12.	Hiển thị thông tin ma_hop_dong, ho_ten (nhân viên), ho_ten (khách hàng), so_dien_thoai (khách hàng), 
ten_dich_vu, so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem), tien_dat_coc của tất cả các
dịch vụ đã từng được khách hàng đặt vào 3 tháng cuối năm 2020 nhưng chưa từng được khách hàng đặt vào 6 tháng đầu năm 2021. */
select Contr.contract_id, E.employee_name, C.customer_name, C.phone_number, Ser.service_name, coalesce(sum(DContr.quantity),0) as number_of_service, Contr.deposit
from contract Contr
join employee E on E.employee_id = Contr.employee_id
join customer C on C.customer_id = Contr.customer_id
join service Ser on Ser.service_id = Contr.service_id
left join detail_contract DContr on DContr.contract_id = Contr.contract_id
where Contr.contract_id not in (
	select contract_id
	from contract
	where (quarter(start_date) between 1 and 2) and year(start_date) = 2021
) and (quarter(Contr.start_date) = 4 and year(Contr.start_date) = 2020)
group by Contr.contract_id;

/* 13.	Hiển thị thông tin các Dịch vụ đi kèm được sử dụng nhiều nhất bởi các Khách hàng đã đặt phòng. 
(Lưu ý là có thể có nhiều dịch vụ có số lần sử dụng nhiều như nhau). */
select ASer.accompanied_service_id, ASer.accompanied_service_name, sum(DContr.quantity) as max_number_of_use
from accompanied_service ASer
join detail_contract DContr on ASer.accompanied_service_id = DContr.accompanied_service_id
group by ASer.accompanied_service_id
having max_number_of_use >= all(
	select sum(quantity)
	from detail_contract
	group by accompanied_service_id
);

/* 14.	Hiển thị thông tin tất cả các Dịch vụ đi kèm chỉ mới được sử dụng một lần duy nhất. Thông tin hiển thị bao gồm 
ma_hop_dong, ten_loai_dich_vu, ten_dich_vu_di_kem, so_lan_su_dung (được tính dựa trên việc count các ma_dich_vu_di_kem). */
select Contr.contract_id, SerT.service_type_name, ASer.accompanied_service_name, count(ASer.accompanied_service_id) as number_of_use
from service Ser
join service_type SerT on SerT.service_type_id = Ser.service_type_id
join contract Contr on Ser.service_id = Contr.service_id
join detail_contract DContr on Contr.contract_id = DContr.contract_id
join accompanied_service ASer on ASer.accompanied_service_id = DContr.accompanied_service_id
group by ASer.accompanied_service_id
having number_of_use = 1
order by Contr.contract_id;

/* 15.	Hiển thi thông tin của tất cả nhân viên bao gồm ma_nhan_vien, ho_ten, ten_trinh_do,
ten_bo_phan, so_dien_thoai, dia_chi mới chỉ lập được tối đa 3 hợp đồng từ năm 2020 đến 2021. */
select E.employee_id, E.employee_name, Q.qualification_name, D.department_name, E.phone_number, E.address, count(Contr.contract_id) as number_of_contract
from employee E
join qualification Q on Q.qualification_id = E.qualification_id
join department D on D.department_id = E.department_id
join contract Contr on E.employee_id = Contr.employee_id
where year(start_date) = 2020 or year(start_date) = 2021
group by E.employee_id
having number_of_contract <= 3
order by E.employee_id;

-- 16.	Xóa những Nhân viên chưa từng lập được hợp đồng nào từ năm 2019 đến năm 2021.
set sql_safe_updates = 0;

delete from employee
where employee_id not in (
	select employee_id
	from contract
	where year(start_date) between 2019 and 2021
	group by employee_id
);

select *
from employee;