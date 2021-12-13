use sales_management;

-- Hiển thị các thông tin gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
update `Order` O
join (
	select OD.OrderID, sum(OD.OrderQuantity * P.ProductPrice) as TotalPrice
	from OrderDetail OD
	join Product P on P.ProductID = OD.ProductID
	group by OD.OrderID
) as Temp on O.OrderID = Temp.OrderID
set O.TotalPrice = Temp.TotalPrice;

select OrderID, OrderDate, TotalPrice
from `Order`;

-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
select C.CustomerName, P.ProductName, OD.OrderQuantity
from Customer C
join `Order` O on C.CustomerID = O.CustomerID
join OrderDetail OD on O.OrderID = OD.OrderID
join Product P on P.ProductID = OD.ProductID;

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select *
from Customer
where CustomerID not in (
	select CustomerID
	from `Order`
);