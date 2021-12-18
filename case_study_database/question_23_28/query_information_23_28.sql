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
create table display_result (
	old_contract_id int,
	remaining_contracts int
);

drop trigger if exists after_contract_delete;
DELIMITER //

create trigger after_contract_delete after delete
on contract
for each row

begin
    declare count_remaining_contracts int;
    
    select count(contract_id)
    into count_remaining_contracts
    from contract;
    
    insert into display_result
    values
    (old.contract_id, count_remaining_contracts);
end //

DELIMITER ;

delete from contract
where contract_id = 1;

delete from contract
where contract_id = 2;

select *
from display_result;

/* 26.	Tạo Trigger có tên tr_cap_nhat_hop_dong khi cập nhật ngày kết thúc hợp đồng, cần kiểm tra xem thời gian cập nhật có phù
hợp hay không, với quy tắc sau: Ngày kết thúc hợp đồng phải lớn hơn ngày làm hợp đồng ít nhất là 2 ngày. Nếu dữ liệu hợp lệ thì cho phép
cập nhật, nếu dữ liệu không hợp lệ thì in ra thông báo “Ngày kết thúc hợp đồng phải lớn hơn ngày làm hợp đồng ít nhất là 2 ngày” trên console của database. */

drop trigger if exists before_contract_update;
DELIMITER //

create trigger before_contract_update before update
on contract
for each row
begin
	declare smaller_than_two boolean default false;
    declare `check` CONDITION FOR SQLSTATE '45000';
    
    select abs(datediff(start_date, new.end_date)) < 2
    into smaller_than_two
    from contract
    where contract_id = old.contract_id;
    
    if smaller_than_two is true then
		signal `check`
        set MESSAGE_TEXT = 'The end_date should be more than two days compared to the start_date!';
	end if;
end //

DELIMITER ;

select *
from contract;

update contract
set end_date = '2021-01-15'
where contract_id = 4;

/* 27.	Tạo Function thực hiện yêu cầu sau
a.	Tạo Function func_dem_dich_vu: Đếm các dịch vụ đã được sử dụng với tổng tiền là > 2.000.000 VNĐ. */

drop function if exists count_suitable_service;
DELIMITER //

create function count_suitable_service ()
returns int
deterministic
begin 
	declare number_of_service int;
    
    select count(service_id)
    into number_of_service
    from (
		select Ser.service_id, sum(rental_cost) as total_rental_cost
		from service Ser
		join contract Contr on Ser.service_id = Contr.service_id
		group by Contr.service_id
		having total_rental_cost > 2000000
    ) as tmp;
    
    return number_of_service;
end //
DELIMITER ;

select count_suitable_service() as number_of_services;

/* 
b.	Tạo Function func_tinh_thoi_gian_hop_dong: Tính khoảng thời gian dài nhất tính từ lúc bắt đầu làm hợp đồng đến lúc
kết thúc hợp đồng mà khách hàng đã thực hiện thuê dịch vụ (lưu ý chỉ xét các khoảng thời gian dựa vào từng lần làm hợp đồng
thuê dịch vụ, không xét trên toàn bộ các lần làm hợp đồng). Mã của khách hàng được truyền vào như là 1 tham số của function này. */

drop function if exists calculate_longest_time_contract;
DELIMITER //

create function calculate_longest_time_contract(id int)
returns int
deterministic
begin
	declare max_time_by_customer int;
    
	select max_time
    into max_time_by_customer
    from (
		select customer_id, max(abs(datediff(start_date, end_date))) as max_time
		from contract
		group by customer_id
    ) as tmp
    where tmp.customer_id = id;
    
    return max_time_by_customer;
end //

DELIMITER ;

select calculate_longest_time_contract(1);