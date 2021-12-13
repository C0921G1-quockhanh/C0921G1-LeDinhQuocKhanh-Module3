use furama_resort_management;

-- `position` table
insert into `position`
values
(001, 'Manager'),
(002, 'Service Staff');

select * from `position`;

-- qualification table
insert into qualification
values
(001, 'Intermediate'),
(002, 'College'),
(003, 'University'),
(004, 'Postgraduate');

select * from qualification;

-- department table
insert into department
values
(001, 'Sales-Marketing'),
(002, 'Administration'),
(003, 'Service'),
(004, 'Management');

select * from department;

-- employee table
insert into employee
values
(001,'Nguyễn Văn An','1970-11-07','456231786',10000000,'0901234121','annguyen@gmail.com','295 Nguyễn Tất Thành, Đà Nẵng',1,3,1),
(002,'Lê Văn Bình','1997-04-09','654231234',7000000,'0934212314','binhlv@gmail.com','22 Yên Bái, Đà Nẵng',1,2,2),
(003,'Hồ Thị Yến','1995-12-12','999231723',14000000,'0412352315','thiyen@gmail.com','K234/11 Điện Biên Phủ, Gia Lai',1,3,2),
(004,'Võ Công Toản','1980-04-04','123231365',17000000,'0374443232','toan0404@gmail.com','277 Hoàng Diệu, Quảng Trị',1,4,4),
(005,'Nguyễn Bỉnh Phát','1999-12-09','454363232',6000000,'0902341231','phatphat@gmail.com','243 Yên Bái, Đà Nẵng',2,1,1),
(006,'Khúc Nguyễn An Nghi','2000-11-08','964542311',7000000,'0978653213','annghi20@gmail.com','294 Nguyễn Tất Thành, Đà Nẵng',2,2,3),
(007,'Nguyễn Hữu Hà','1993-01-01','534323231',8000000,'0941234553','nhh0101@gmail.com','4 Nguyễn Chí Thanh, Huế',2,3,2),
(008,'Nguyễn Hà Đông','1989-09-03','234414123',9000000,'0642123111','donghanguyen@gmail.com','111 Hùng Vương, Hà Nội',2,4,4),
(009,'Tòng Hoang','1982-09-03','256781231',6000000,'0245144444','hoangtong@gmail.com','213 Hàm Nghi, Đà Nẵng',2,4,4),
(0010,'NNguyễn Công Đạo','1994-01-08','755434343',8000000,'0988767111','nguyencongdao12@gmail.com','6 Hoà Khánh, Đồng Nai',2,3,2);

select *
from employee;

-- customer_type table
insert into customer_type
values
(001,'Diamond'),
(002,'Platinium'),
(003,'Gold'),
(004,'Silver'),
(005,'Member');

select *
from customer_type;

-- customer table
insert into customer
values
(001,5,'Nguyễn Thị Hào','1970-11-07',0,'643431213','0945423362','thihao07@gmail.com','23 Nguyễn Hoàng, Đà Nẵng'),
(002,3,'Phạm Xuân Diệu','1992-08-08',1,'865342123','0954333333','xuandieu92@gmail.com','K77/22 Thái Phiên, Quảng Trị'),
(003,1,'Trương Đình Nghệ','1990-02-27',1,'488645199','0373213122','nghenhan2702@gmail.com','K323/12 Ông Ích Khiêm, Vinh'),
(004,1,'Dương Văn Quan','1981-07-08',1,'543432111','0490039241','duongquan@gmail.com','K453/12 Lê Lợi, Đà Nẵng'),
(005,4,'Hoàng Trần Nhi Nhi','1995-12-09',0,'795453345','0312345678','nhinhi123@gmail.com','224 Lý Thái Tổ, Gia Lai'),
(006,4,'Tôn Nữ Mộc Châu','2005-12-06',0,'732434215','0988888844','tonnuchau@gmail.com','37 Yên Thế, Đà Nẵng'),
(007,1,'Nguyễn Mỹ Kim','1984-04-08',0,'856453123','0912345698','kimcuong84@gmail.com','K123/45 Lê Lợi, Hồ Chí Minh'),
(008,3,'Nguyễn Thị Hào','1999-04-08',0,'965656433','0763212345','haohao99@gmail.com','55 Nguyễn Văn Linh, Kon Tum'),
(009,1,'Trần Đại Danh','1994-07-01',1,'432341235','0643343433','danhhai99@gmail.com','24 Lý Thường Kiệt, Quảng Ngãi'),
(0010,2,'Nguyễn Tâm Đắc','1989-07-01',1,'344343432','0987654321','dactam@gmail.com','22 Ngô Quyền, Đà Nẵng');

select *
from customer;

-- rental_type table
insert into rental_type
values
(001,'year'),
(002,'month'),
(003,'day'),
(004,'hour');

select *
from rental_type;

-- service_type table
insert into service_type
values
(001,'Villa'),
(002,'House'),
(003,'Room');

select *
from service_type;

-- service table
insert into service
values
(001,'Villa Beach Front',25000,10000000,10,3,1,'vip','Có hồ bơi',500,4),
(002,'House Princess 01',14000,5000000,7,2,2,'vip','Có thêm bếp nướng',null,3),
(003,'Room Twin 01',5000,1000000,2,4,3,'normal','Có tivi',null,null),
(004,'Villa No Beach Front',22000,9000000,8,3,1,'normal','Có hồ bơi',300,3),
(005,'House Princess 02',10000,4000000,5,3,2,'normal','Có thêm bếp nướng',null,2),
(006,'Room Twin 02',3000,900000,2,4,3,'normal','Có tivi',null,null);

select *
from service;

-- accompanied_service table
insert into accompanied_service
values
(001,'Karaoke',10000,'giờ','tiện nghi, hiện tại'),
(002,'Thuê xe máy',10000,'chiếc','hỏng 1 xe'),
(003,'Thuê xe đạp',20000,'chiếc','tốt'),
(004,'Buffet buổi sáng',15000,'suất','đầy đủ đồ ăn, tráng miệng'),
(005,'Buffet buổi trưa',90000,'suất','đầy đủ đồ ăn, tráng miệng'),
(006,'Buffet buổi tối',16000,'suất','đầy đủ đồ ăn, tráng miệng');

select *
from accompanied_service;

-- contract table
insert into contract
values
(001,'2020-12-08','2020-12-08',0,3,1,3),
(002,'2020-07-14','2020-07-21',200000,7,3,1),
(003,'2021-03-15','2021-03-17',50000,3,4,2),
(004,'2021-01-14','2021-01-18',100000,7,5,5),
(005,'2021-07-14','2021-07-15',0,7,2,6),
(006,'2021-06-01','2021-06-03',0,7,7,6),
(007,'2021-09-02','2021-09-05',100000,7,4,4),
(008,'2021-06-17','2021-06-18',150000,3,4,1),
(009,'2020-11-19','2020-11-19',0,3,4,3),
(0010,'2021-04-12','2021-04-14',0,10,3,5),
(0011,'2021-04-25','2021-04-25',0,2,2,1),
(0012,'2021-05-25','2021-05-27',0,7,10,1);

select *
from contract;

-- detail_contract table
insert into detail_contract
values
(001,2,4,5),
(002,2,5,8),
(003,2,6,15),
(004,3,1,1),
(005,3,2,11),
(006,1,3,1),
(007,1,2,2),
(008,12,2,2);

select *
from detail_contract;