
create table roles (
    role_id     int                 not null    auto_increment,
    role        varchar(45)         not null,
    primary key (role_id)
);

alter table roles
add constraint UC_role unique (role);

create table `users` (
  user_id       int                   not null    auto_increment,
  user_name     varchar(50)           not null,
  email         varchar(256)          not null,
  password      varchar(256)          not null,
  name          varchar(200)          not null,
  last_name     varchar(200)          not null,
  active        tinyint,
  primary key (user_id)
  );


alter table users
add constraint UC_user unique (email);

create table user_role (
    user_id     int    not null,
    role_id     int    not null,
    primary key (user_id, role_id)
);

alter table user_role
add foreign key (user_id)
references `users` (user_id);

alter table user_role
add foreign key (role_id)
references `roles` (role_id);


