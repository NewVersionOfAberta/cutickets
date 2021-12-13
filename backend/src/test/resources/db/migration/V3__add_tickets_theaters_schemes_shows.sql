create table show_types (
    show_type_id     int                 not null    auto_increment,
    name             varchar(200)        not null,
    primary key (show_type_id)
);

alter table show_types
add constraint UC_show_types unique (name);

create table show_states (
    show_state_id    int                 not null    auto_increment,
    name             varchar(200)        not null,
    primary key (show_state_id)
);

alter table show_types
add constraint UC_show_states_name unique (name);

create table ticket_states (
    ticket_state_id    int                 not null    auto_increment,
    name               varchar(200)        not null,
    primary key (ticket_state_id)
);

alter table ticket_states
add constraint UC_ticket_states_name unique (name);

create table countries (
    country_id         int                 not null    auto_increment,
    name               varchar(200)        not null,
    primary key (country_id)
);

alter table countries
add constraint UC_countries_name unique (name);

create table cities (
    city_id            int                 not null    auto_increment,
    country_id         int                 not null,
    name               varchar(200)        not null,
    primary key (city_id)
);

alter table cities
add constraint UC_cities_name unique (name);

create table theaters (
  theater_id    int                    not null            auto_increment,
  name          varchar(200)           not null,
  description   varchar(256)           not null,
  address       varchar(256)           not null,
  city_id       int                    not null,
  primary key (theater_id)
  );

alter table theaters
add foreign key (city_id)
references cities (city_id);

create table theater_hall (
  theater_hall_id   int                    not null    auto_increment,
  theater_id        int                    not null,
  name              varchar(200)           not null,
  primary key (theater_hall_id)
  );

alter table theater_hall
add foreign key (theater_id)
references theaters (theater_id);

create table schemes (
  scheme_id         int                    not null    auto_increment,
  creation_date     datetime               not null,
  rows_amount       int                    not null,
  max_seats_amount  int                    not null,
  theater_hall_id   int                    not null,
  primary key (scheme_id)
  );

alter table schemes
add foreign key (theater_hall_id)
references theater_hall (theater_hall_id);

create table shows (
  show_id       int                    not null    auto_increment,
  theater_id    int                    not null,
  scheme_id     int                    not null,
  show_state_id int                    not null,
  show_type_id  int                    not null,
  name          varchar(200)           not null,
  description   varchar(256)           not null,
  date_time     datetime               not null,
  primary key (show_id)
  );

alter table shows
add foreign key (theater_id)
references theaters (theater_id);

alter table shows
add foreign key (scheme_id)
references schemes (scheme_id);

alter table shows
add foreign key (show_state_id)
references show_states (show_state_id);

alter table shows
add foreign key (show_type_id)
references show_types (show_type_id);

create table prices (
  price_id      int                    not null    auto_increment,
  price         int                    not null,
  color         varchar(20)            not null,
  primary key (price_id)
  );

create table seats (
  seat_id       int                    not null    auto_increment,
  scheme_id     int                    not null,
  `row`         int                    not null,
  `number`      int                    not null,
  price_id      int                    not null,
  primary key (seat_id)
  );

alter table seats
add foreign key (scheme_id)
references schemes (scheme_id);

alter table seats
add foreign key (price_id)
references prices (price_id);

create table tickets (
  ticket_id         int                    not null    auto_increment,
  seat_id           int                    not null,
  show_id           int                    not null,
  ticket_state_id   int                    not null,
  primary key (ticket_id)
  );

alter table tickets
add foreign key (seat_id)
references seats (seat_id);

alter table tickets
add foreign key (show_id)
references shows (show_id);

alter table tickets
add foreign key (ticket_state_id)
references ticket_states (ticket_state_id);

create table sold_tickets (
  sold_ticket_id    int                    not null    auto_increment,
  ticket_id         int                    not null,
  user_id           int                    not null,
  date_time         datetime               not null,
  primary key (sold_ticket_id)
  );

alter table sold_tickets
add foreign key (ticket_id)
references tickets (ticket_id);

create table news (
  new_id        int                    not null    auto_increment,
  name          varchar(200)           not null,
  date_time     datetime               not null,
  description   varchar(256)           not null,
  primary key (new_id)
  );