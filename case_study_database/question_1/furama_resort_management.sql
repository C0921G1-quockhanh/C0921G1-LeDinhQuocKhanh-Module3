create database furama_resort_management;

use furama_resort_management;

create table `Position` (
	PositionID int primary key,
    PositionName varchar(45)
);

create table Qualification (
	QualificationID int primary key,
    QualificationName varchar(45)
);

create table Department (
	DepartmentID int primary key,
    DepartmentName varchar(45)
);

create table Employee (
	EmployeeID int primary key,
    `Name` varchar(45),
    DateOfBirth date,
    IdentityNumber varchar(45),
    Salary double,
    PhoneNumber varchar(45),
    Email varchar(45),
    Address varchar(45),
    PositionID int,
    QualificationID int,
    DepartmentID int,
    
    foreign key(PositionID)
		references `Position`(PositionID),
    foreign key(QualificationID)
		references Qualification(QualificationID),
	foreign key(DepartmentID)
		references Department(DepartmentID)
);

create table CustomerType (
	CustomerTypeID int primary key,
    CustomerTypeName varchar(45)
);

create table Customer (
	CustomerID int primary key,
    CustomerTypeID int,
    `Name` varchar(45),
    DateOfBirth date,
    Sex bit(1),
    IdentityNumber varchar(45),
    PhoneNumber varchar(45),
    Email varchar(45),
    Address varchar(45),
    
    foreign key(CustomerTypeID)
		references CustomerType(CustomerTypeID)
);

create table RentalType (
	RentalTypeID int primary key,
    RentalTypeName varchar(45)
);

create table ServiceType (
	ServiceTypeID int primary key,
    ServiceTypeName varchar(45)
);

create table Service (
	ServiceID int primary key,
    `Name` varchar(45),
    `Area` int,
    RentalCost double,
    MaxPeople int,
    RentalTypeID int,
    ServiceTypeID int,
    RoomStandard varchar(45),
    ExtraAmenity varchar(45),
    PoolArea double,
    Levels int,
    
    foreign key(RentalTypeID)
		references RentalType(RentalTypeID),
	foreign key(ServiceTypeID)
		references ServiceType(ServiceTypeID)
);

create table Contract (
	ContractID int primary key,
    StartDate datetime,
    EndDate datetime,
    Deposit double,
    EmployeeID int,
    CustomerID int,
    ServiceID int,
    
    foreign key(EmployeeID)
		references Employee(EmployeeID),
	foreign key(CustomerID)
		references Customer(CustomerID),
	foreign key(ServiceID)
		references Service(ServiceID)
);

create table AccompaniedService (
	AccompaniedServiceID int primary key,
    `Name` varchar(45),
    Price double,
    Unit varchar(10),
    `Status` varchar(45)
);

create table DetailContract (
	DetailContractID int primary key,
    ContractID int,
    AccompaniedServiceID int,
    Quantity int,
    
    foreign key(ContractID)
		references Contract(ContractID),
	foreign key(AccompaniedServiceID)
		references AccompaniedService(AccompaniedServiceID)
);
