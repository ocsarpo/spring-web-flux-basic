drop table if exists books;
create table books
(
    id      bigint not null auto_increment,
    name    varchar(50),
    price   int,
    primary key (id)
);
