create database sales_management;

use sales_management;

create table Customer (
	CustomerID int not null auto_increment primary key,
    CustomerName varchar(30) not null,
	CustomerAge int not null default 1 check(CustomerAge between 1 and 100)
);

create table `Order` (
	OrderID int not null auto_increment primary key,
    CustomerID int not null,
    OrderDate datetime not null,
    TotalPrice float,
    
    foreign key(CustomerID)
		references Customer(CustomerID)
);

create table Product (
	ProductID int not null auto_increment primary key,
    ProductName varchar(30) not null,
    ProductPrice float not null
);

create table OrderDetail (
	OrderID int not null,
    ProductID int not null,
    OrderQuantity int not null,
    
    primary key(OrderID, ProductID),
    foreign key(OrderID)
		references `Order`(OrderID),
	foreign key(ProductID)
		references Product(ProductID)
);


    

