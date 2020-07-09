create schema if not exists test collate utf8mb4_general_ci;

create table if not exists contact
(
    id int auto_increment
        primary key,
    first_name varchar(32) not null,
    phone varchar(32) not null,
    constraint contact_phone_uindex
        unique (phone)
);
