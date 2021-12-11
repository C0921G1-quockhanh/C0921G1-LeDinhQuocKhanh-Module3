create database goods_management;

use goods_management;

create table DeliveryNote (
	DeliveryID int not null primary key,
    DeliveryDate datetime not null
);

create table Supplies (
	MaterialID int not null primary key,
    MaterialName varchar(30) not null
);

create table ReceivedNote (
	ReceivedID int not null primary key,
    ReceivedDate datetime not null
);

create table Phone (
	PhoneID int not null primary key,
    PhoneNumber varchar(15) not null
);

create table Supplier (
	SupplierID int not null primary key,
    SupplierName varchar(30) not null,
    SupplierAddress varchar(50) not null,
    PhoneID int not null,
    
    foreign key(PhoneID)
		references Phone(PhoneID)
);

create table `Order` (
	OrderID int not null primary key,
    OrderDate datetime not null,
    SupplierID int not null,
    
    foreign key(SupplierID)
		references Supplier(SupplierID)
);

create table DeliveryDetail (
	DeliveryID int not null,
    MaterialID int not null,
    DeliveryPrice float not null,
    DeliveryQuantity int not null default 1,
    
    primary key(DeliveryID, MaterialID),
    foreign key(DeliveryID)
		references DeliveryNote (DeliveryID),
	foreign key(MaterialID)
		references Supplies(MaterialID)
);

create table ReceivedDetail (
	MaterialID int not null,
    ReceivedID int not null,
    ReceivedPrice float not null,
    ReceivedQuantity int not null default 1,
    
    primary key(MaterialID, ReceivedID),
    foreign key(MaterialID)
		references Supplies(MaterialID),
	foreign key(ReceivedID)
		references ReceivedNote (ReceivedID)
);

create table OrderDetail (
	MaterialID int not null,
    OrderID int not null,
    
    primary key(MaterialID, OrderID),
    foreign key(MaterialID)
		references Supplies(MaterialID),
	foreign key(OrderID)
		references `Order`(OrderID)
);
    