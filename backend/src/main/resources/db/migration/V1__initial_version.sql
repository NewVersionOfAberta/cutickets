
create table roles (
    role_id     int                                     not null    auto_increment,
    role        varchar(45)     character set 'utf8'    not null,
    primary key (role_id)
)
ENGINE = InnoDB
default character set = utf8;

alter table roles add constraint unique key ak_roles_role (role);

create table `users` (
  user_id       int                                                                 not null    auto_increment,
  user_name     varchar(50)      character set 'utf8' collate 'utf8_general_ci'     not null,
  email         varchar(256)     character set 'utf8' collate 'utf8_general_ci'     not null,
  password      varchar(256)     character set 'utf8' collate 'utf8_general_ci'     not null,
  name          varchar(200)     character set 'utf8' collate 'utf8_general_ci'     not null,
  last_name     varchar(200)     character set 'utf8' collate 'utf8_general_ci'     not null,
  active        tinyint,
  primary key (user_id)
  )
ENGINE = InnoDB
default character set = utf8;

alter table users add constraint unique key ak_user_email (email);

create table user_role (
    user_id     int    not null,
    role_id     int    not null,
    primary key (user_id, role_id)
)
ENGINE = InnoDB
default character set = utf8;

alter table user_role
add constraint foreign key `fk_user_role_user` (user_id)
references `users` (user_id);

alter table user_role
add constraint foreign key `fk_user_role_role` (role_id)
references `roles` (role_id);


