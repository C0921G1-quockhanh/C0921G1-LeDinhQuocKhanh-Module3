use sales_management;

insert into Customer(CustomerName, CustomerAge)
values
('Minh Quan',10),
('Ngoc Oanh',20),
('Hong Ha',50);

select *
from Customer;

insert into `Order`(CustomerID, OrderDate)
values
(1,'2006-03-21'),
(2,'2006-03-23'),
(1,'2006-03-16');

select *
from `Order`;

insert into Product(ProductName, ProductPrice)
values
('May Giat',3),
('Tu Lanh',5),
('Dieu Hoa',7),
('Quat',1),
('Bep Dien',2);

select *
from Product;

insert into OrderDetail
values
(1,1,3),
(1,3,7),
(1,4,2),
(2,1,1),
(3,1,8),
(2,5,4),
(2,3,3);

select *
from OrderDetail;