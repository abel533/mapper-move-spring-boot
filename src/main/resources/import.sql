drop table user if exists;

create table user (
  id bigint primary key,
  sequence int,
  name varchar(32),
  py varchar(5)
);

insert into user(id, sequence, name, py) values(1, 1,'毕淑儒','BSR');
insert into user(id, sequence, name, py) values(2, 2,'蔡兴熙','CXX');
insert into user(id, sequence, name, py) values(3, 3,'曾三杰','ZSJ');
insert into user(id, sequence, name, py) values(4, 4,'常元琴','CYQ');
insert into user(id, sequence, name, py) values(5, 5,'陈栋芬','CDF');
insert into user(id, sequence, name, py) values(6, 6,'陈宁婷','CNZ');
insert into user(id, sequence, name, py) values(7, 7,'陈瑞','CR');
insert into user(id, sequence, name, py) values(8, 8,'陈武宵','CWX');
insert into user(id, sequence, name, py) values(9, 9,'陈晓丽','CXL');
insert into user(id, sequence, name, py) values(10, 10,'陈涛','CYT');