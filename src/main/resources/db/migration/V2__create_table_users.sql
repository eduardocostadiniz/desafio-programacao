create table if not exists users (
	id varchar(50) primary key,
	email varchar(100) not null unique,
	password varchar(150) not null
);