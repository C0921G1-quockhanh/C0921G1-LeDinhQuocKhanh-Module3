use furama_resort_management;

/* 23.	Tạo Stored Procedure sp_xoa_khach_hang dùng để xóa thông tin của một khách hàng nào đó với ma_khach_hang
được truyền vào như là 1 tham số của sp_xoa_khach_hang. */
DELIMITER //
create procedure deleteCustomer(in id int)
begin
	delete from customer
    where customer_id = id;
end //
DELIMITER ;

call deleteCustomer(10);

select *
from customer;

/* 24.	Tạo Stored Procedure sp_them_moi_hop_dong dùng để thêm mới vào bảng hop_dong với yêu cầu sp_them_moi_hop_dong phải
thực hiện kiểm tra tính hợp lệ của dữ liệu bổ sung, với nguyên tắc không được trùng khóa chính và đảm bảo toàn vẹn tham chiếu đến các bảng liên quan. */
drop procedure if exists createContract;
DELIMITER //

create procedure createContract(in contract_id int, in start_date datetime, in end_date datetime, deposit double, in employee_id int,
								in customer_id int, service_id int)
begin
	-- check contract_id
	declare contract_id_existed boolean default false;
    declare existed CONDITION FOR SQLSTATE '45000';
    
    declare employee_id_not_existed boolean default false;
	declare customer_id_not_existed boolean default false;
    declare service_id_not_existed boolean default false;
    declare not_existed CONDITION FOR SQLSTATE '45000';
    
    select count(Contr.contract_id) > 0
    into contract_id_existed
    from contract Contr
    where Contr.contract_id = contract_id;
	
    if contract_id_existed is true then
		signal existed
        set MESSAGE_TEXT = 'The contract_id is existed!';
	end if;
	
    -- check employee_id
    select count(E.employee_id) = 0
    into employee_id_not_existed
    from employee E
    where E.employee_id = employee_id;
    
    if employee_id_not_existed is true then
		signal not_existed
        set MESSAGE_TEXT = 'No suitable employee in the list!';
	end if;
    
    -- check customer_id
    select count(C.customer_id) = 0
    into customer_id_not_existed
    from customer C
    where C.customer_id = customer_id;
    
    if customer_id_not_existed is true then
		signal not_existed
        set MESSAGE_TEXT = 'No suitable customer in the list!';
	end if;
    
	 -- check service_id
     select count(Ser.service_id) = 0
     into service_id_not_existed
     from service Ser
     where Ser.service_id = service_id;
     
     if service_id_not_existed is true then
		signal not_existed
        set MESSAGE_TEXT = 'No suitable service in the list!';
	 end if;

	insert into contract
    values
    (contract_id, start_date, end_date, deposit, employee_id, customer_id, service_id);
end //
DELIMITER ;

/* 25.	Tạo Trigger có tên tr_xoa_hop_dong khi xóa bản ghi trong bảng hop_dong thì hiển thị tổng số lượng bản ghi còn lại
có trong bảng hop_dong ra giao diện console của database.
Lưu ý: Đối với MySQL thì sử dụng SIGNAL hoặc ghi log thay cho việc ghi ở console. */
drop trigger if exists after_contract_delete;

create table display_result (
	count_records int,
    old_contract_id int
);

DELIMITER //
create trigger after_contract_delete after delete
on contract
for each row
begin
	-- declare contract_id_ int;
	declare count_records int;
    
    select count(contract_id)
    into count_records
    from contract;
    
    insert into display_result
    values
    (count_records, old.contract_id);
end //
DELIMITER ;

delete from contract
where contract_id = 12;

select *
from display_result;

/* 27.	Tạo Function thực hiện yêu cầu sau
a.	Tạo Function func_dem_dich_vu: Đếm các dịch vụ đã được sử dụng với tổng tiền là > 2.000.000 VNĐ. */
select Ser.service_id, sum(rental_cost)
from service Ser
join contract Contr on Ser.service_id = Contr.service_id
group by Contr.service_id;



/* 
b.	Tạo Function func_tinh_thoi_gian_hop_dong: Tính khoảng thời gian dài nhất tính từ lúc bắt đầu làm hợp đồng đến lúc
kết thúc hợp đồng mà khách hàng đã thực hiện thuê dịch vụ (lưu ý chỉ xét các khoảng thời gian dựa vào từng lần làm hợp đồng
thuê dịch vụ, không xét trên toàn bộ các lần làm hợp đồng). Mã của khách hàng được truyền vào như là 1 tham số của function này. */



/* 28.	Tạo Stored Procedure sp_xoa_dich_vu_va_hd_room để tìm các dịch vụ được thuê bởi khách hàng với loại dịch vụ là “Room”
từ đầu năm 2015 đến hết năm 2019 để xóa thông tin của các dịch vụ đó (tức là xóa các bảng ghi trong bảng dich_vu) và xóa những
hop_dong sử dụng dịch vụ liên quan (tức là phải xóa những bản gi trong bảng hop_dong) và những bản liên quan khác. */