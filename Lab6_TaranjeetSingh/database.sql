create database hospital_db;
use hospital_db;

create table patients (
    patient_id int auto_increment primary key,
    name varchar(100) not null,
    age int not null,
    gender varchar(30) not null,
    phone varchar(20) not null,
    diagnosis varchar(255) not null,
    admission_date date not null,
    created_at timestamp default current_timestamp
);
