create table if not exists sales (
	id bigserial primary key,
	purchaser_name varchar(100) not null,
	item_description varchar(250),
	item_price decimal not null,
	purchase_count int not null,
	merchant_address varchar(300),
	merchant_name varchar(150) not null
);