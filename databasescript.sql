use corunetdb;

drop table if exists hibernate_sequence;
drop table if exists machinesinfo;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table machinesinfo (id bigint not null, description varchar(100), hostname varchar(15), ip varchar(12), primary key (id)) engine=InnoDB;