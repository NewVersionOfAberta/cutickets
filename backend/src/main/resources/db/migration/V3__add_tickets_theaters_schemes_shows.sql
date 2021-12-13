create table show_types (
    show_type_id     int                                     not null    auto_increment,
    name             varchar(200)     character set 'utf8'   not null,
    primary key (show_type_id)
)
ENGINE = InnoDB
default character set = utf8;

alter table show_types add constraint unique key ak_show_types_name (name);

create table show_states (
    show_state_id    int                                     not null    auto_increment,
    name             varchar(200)     character set 'utf8'   not null,
    primary key (show_state_id)
)
ENGINE = InnoDB
default character set = utf8;

alter table show_states add constraint unique key ak_show_states_name (name);

create table ticket_states (
    ticket_state_id    int                                     not null    auto_increment,
    name               varchar(200)     character set 'utf8'   not null,
    primary key (ticket_state_id)
)
ENGINE = InnoDB
default character set = utf8;

alter table ticket_states add constraint unique key ak_ticket_states_name (name);

create table countries (
    country_id         int                                     not null    auto_increment,
    name               varchar(200)     character set 'utf8'   not null,
    primary key (country_id)
)
ENGINE = InnoDB
default character set = utf8;

alter table countries add constraint unique key ak_countries_name (name);

create table cities (
    city_id            int                                     not null    auto_increment,
    country_id         int                                     not null,
    name               varchar(200)     character set 'utf8'   not null,
    primary key (city_id)
)
ENGINE = InnoDB
default character set = utf8;

alter table cities add constraint unique key ak_cities_name (name);

create table theaters (
  theater_id    int                                                                 not null    auto_increment,
  name          varchar(200)      character set 'utf8' collate 'utf8_general_ci'     not null,
  description   varchar(256)     character set 'utf8' collate 'utf8_general_ci'     not null,
  address       varchar(256)     character set 'utf8' collate 'utf8_general_ci'     not null,
  city_id       int                                                                 not null,
  primary key (theater_id)
  )
ENGINE = InnoDB
default character set = utf8;

alter table theaters
add constraint foreign key fk_theaters_cities (city_id)
references cities (city_id);

create table theater_hall (
  theater_hall_id   int                                                                 not null    auto_increment,
  theater_id        int                                                                 not null,
  name              varchar(200)      character set 'utf8' collate 'utf8_general_ci'     not null,
  primary key (theater_hall_id)
  )
ENGINE = InnoDB
default character set = utf8;

alter table theater_hall
add constraint foreign key fk_theater_hall_theaters (theater_id)
references theaters (theater_id);

create table schemes (
  scheme_id         int                                                                 not null    auto_increment,
  creation_date     datetime                                                            not null,
  rows_amount       int                                                                 not null,
  max_seats_amount  int                                                                 not null,
  theater_hall_id   int                                                                 not null,
  primary key (scheme_id)
  )
ENGINE = InnoDB
default character set = utf8;

alter table schemes
add constraint foreign key fk_schemes_theater_hall (theater_hall_id)
references theater_hall (theater_hall_id);

create table shows (
  show_id       int                                                                 not null    auto_increment,
  theater_id    int                                                                 not null,
  scheme_id     int                                                                 not null,
  show_state_id int                                                                 not null,
  show_type_id  int                                                                 not null,
  name          varchar(200)     character set 'utf8' collate 'utf8_general_ci'     not null,
  description   varchar(256)     character set 'utf8' collate 'utf8_general_ci'     not null,
  date_time     datetime                                                            not null,
  primary key (show_id)
  )
ENGINE = InnoDB
default character set = utf8;

alter table shows
add constraint foreign key fk_shows_theaters (theater_id)
references theaters (theater_id);

alter table shows
add constraint foreign key fk_shows_schemes (scheme_id)
references schemes (scheme_id);

alter table shows
add constraint foreign key fk_shows_show_states (show_state_id)
references show_states (show_state_id);

alter table shows
add constraint foreign key fk_shows_show_types (show_type_id)
references show_types (show_type_id);

create table prices (
  price_id      int                                                                 not null    auto_increment,
  price         int                                                                 not null,
  color         varchar(20)      character set 'utf8' collate 'utf8_general_ci'     not null,
  primary key (price_id)
  )
ENGINE = InnoDB
default character set = utf8;

create table seats (
  seat_id       int                                                                 not null    auto_increment,
  scheme_id     int                                                                 not null,
  `row`         int                                                                 not null,
  `number`      int                                                                 not null,
  price_id      int                                                                 not null,
  primary key (seat_id)
  )
ENGINE = InnoDB
default character set = utf8;

alter table seats
add constraint foreign key fk_seats_schemes (scheme_id)
references schemes (scheme_id);

alter table seats
add constraint foreign key fk_seats_prices (price_id)
references prices (price_id);

create table tickets (
  ticket_id         int                                                                 not null    auto_increment,
  seat_id           int                                                                 not null,
  show_id           int                                                                 not null,
  ticket_state_id   int                                                                 not null,
  primary key (ticket_id)
  )
ENGINE = InnoDB
default character set = utf8;

alter table tickets
add constraint foreign key fk_tickets_seats (seat_id)
references seats (seat_id);

alter table tickets
add constraint foreign key fk_tickets_shows (show_id)
references shows (show_id);

alter table tickets
add constraint foreign key fk_tickets_ticket_states (ticket_state_id)
references ticket_states (ticket_state_id);

create table sold_tickets (
  sold_ticket_id    int                                                                 not null    auto_increment,
  ticket_id         int                                                                 not null,
  user_id           int                                                                 not null,
  date_time         datetime                                                            not null,
  primary key (sold_ticket_id)
  )
ENGINE = InnoDB
default character set = utf8;

alter table sold_tickets
add constraint foreign key fk_sold_tickets_tickets (ticket_id)
references tickets (ticket_id);

create table news (
  new_id        int                                                                 not null    auto_increment,
  name          varchar(200)     character set 'utf8' collate 'utf8_general_ci'     not null,
  date_time     datetime                                                            not null,
  description   varchar(256)     character set 'utf8' collate 'utf8_general_ci'     not null,
  primary key (new_id)
  )
ENGINE = InnoDB
default character set = utf8;