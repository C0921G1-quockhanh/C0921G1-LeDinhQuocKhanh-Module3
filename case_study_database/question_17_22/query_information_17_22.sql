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