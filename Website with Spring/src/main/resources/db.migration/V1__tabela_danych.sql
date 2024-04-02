drop table if exists Dane;
create table Dane(
        ID        int primary key auto_increment,
        nazwa varchar(100) not null,
        kontakt varchar(100) not null,
        wiadomosc varchar(300) not null,
)