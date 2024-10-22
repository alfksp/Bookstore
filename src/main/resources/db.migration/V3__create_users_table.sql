use bookstore;
create table if not exists users (
    id bigint primary key auto_increment,
    name varchar(50) not null,
    surname varchar(50) not null,
    email varchar(50) unique not null,
    password varchar(50) not null
);