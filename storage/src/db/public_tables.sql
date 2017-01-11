create table products (
    id			        bigserial primary key,
    product_name         varchar(100)  not null,
    product_size         smallint,
    product_price        real,
    product_date	     date
);

create table income (
    id			        bigserial primary key,
    product_name         varchar(100)  not null,
    product_size         smallint,
    product_price        real,
    product_date	     date
);

create table outcome (
    id			        bigserial primary key,
    product_name         varchar(100)  not null,
    product_size         smallint,
    product_price        real,
    product_date	     date
);