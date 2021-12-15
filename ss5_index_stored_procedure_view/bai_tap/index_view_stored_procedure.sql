drop database if exists demo;

create database demo;

use demo;

-- Tạo bảng Products với các trường dữ liệu
create table product (
	product_id int auto_increment primary key,
    product_code varchar(30),
    product_name varchar(30),
    product_price float,
    product_amount int,
    product_description varchar(50),
    product_status bit(1)
);

-- Chèn một số dữ liệu mẫu cho bảng Products
insert into product(product_code, product_name, product_price, product_amount, product_description, product_status)
values
('P001','Co Gai Ha Lan',260000,5,'Milk',1),
('P002','Hao Hao',90000,2,'Noodles',1),
('P003','Chocopie',45000,1,'Cake',1),
('P004','Yomost',240000,4,'Milk',0),
('P005','Omachi',120000,2,'Noodles',1),
('P006','BMW',8000000000,1,'Luxury Car',1),
('P007','DW',1500000,2,'Watch',0);

-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
-- So sánh câu truy vấn trước và sau khi tạo index
create unique index index_product_code
on product(product_code);

explain select *
from product
where product_code = 'P001';

-- Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
-- So sánh câu truy vấn trước và sau khi tạo index
create unique index index_name_price
on product(product_name, product_price);

explain select *
from product
where product_name = 'DW';

-- Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products
-- Tiến hành sửa đổi view
-- Tiến hành xoá view
create view view_product
as
select product_code, product_name, product_price, product_status
from product;

select *
from view_product;

create or replace view view_product
as
select product_code, product_name, product_price, product_status, product_description
from product
where product_id >= 3;

select *
from view_product;

drop view view_product;

-- Tạo stored procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
DELIMITER //
create procedure findAllProducts()
begin
	select * from product;
end //
DELIMITER ;

call findAllProducts();

-- Tạo stored procedure thêm một sản phẩm mới
DELIMITER //
create procedure addNewProduct(in product_code varchar(30), in product_name varchar(30), in product_price float,
							   in product_amount int, in product_description varchar(50), in product_status bit(1))
begin
	insert into product(product_code, product_name, product_price, product_amount, product_description, product_status)
    values
    (product_code, product_name, product_price, product_amount, product_description, product_status);
end //
DELIMITER ;

call addNewProduct('P008','TH True Milk',320000,3,'Milk',1);
call findAllProducts();

-- Tạo stored procedure sửa thông tin sản phẩm theo id
DELIMITER //
create procedure editProduct(in id int, in `name` varchar(30), in price float)
begin
	update product
    set product_name = `name`, product_price = price
    where product_id = id;
end //
DELIMITER ;

call editProduct(1,'Sua Co Gai Ha Lan',280000);
call findAllProducts();

-- Tạo stored procedure xoá sản phẩm theo id
DELIMITER //
create procedure deleteProduct(in id int)
begin
	delete from product
    where product_id = id;
end //
DELIMITER ;

call deleteProduct(5);
call findAllProducts();