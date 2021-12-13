insert into users ( user_name,
                    email,
                    password,
                    name,
                    last_name,
                    active)
values ("arnold",
        "arn@gmail.com",
        "$2a$10$pNQc9jbHgMq0ZkVsD.1CPOhWw2oc8KhkanTTdnVZXHwO6dDyREQ1W",
        "Yury",
        "Moroz",
        1),
        ("arya",
        "a@gmail.com",
        "$2a$10$pNQc9jbHgMq0ZkVsD.1CPOhWw2oc8KhkanTTdnVZXHwO6dDyREQ1W",
        "Darya",
        "Xolod",
        1),
        ("wowkiller11",
        "wowkiller2007@gmail.com",
        "$2a$10$pNQc9jbHgMq0ZkVsD.1CPOhWw2oc8KhkanTTdnVZXHwO6dDyREQ1W",
        "Egor",
        "Orcov",
        1);

insert into user_role (user_id, role_id)
values (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 1);

insert into countries (name)
values  ("Italy"),
        ("Sweden"),
        ("Belarus");

insert into cities ( country_id,
                     name )
values  (1, "Venice"),
        (2, "Stockholm"),
        (3, "Vitebsk"),
        (3, "Brest"),
        (3, "Minsk");

insert into theaters (  name,
                        description,
                        address,
                        city_id )
values ("Teatro La Fenice",
        "Appropriately named La Fenice (the Phoenix), this Venetian theatre has burned down and risen again from the flames three times since it first opened in 1792. ",
        "Campo S. Fantin, 1965, 30124 Venezia VE",
        1),
        ("National Academic Drama Theatre in the Name of Yakub Kolas",
        "The modern theater building (architects A. Maksimov and I. Ryskina) was built in 1958. The main facade faces the square of the 1000th anniversary of Vitebsk",
        "Zamkovaya 2, Vitebsk 210026",
        3);

insert into theater_hall (theater_id, name)
values  (1, "Main"),
        (2, "Main");

insert into schemes (   creation_date,
                        rows_amount,
                        max_seats_amount,
                        theater_hall_id )
values ("2021-05-27 13:17:17",
        3,
        4,
        1),
       ("2021-05-28 13:17:17",
        3,
        4,
        2);

insert into prices (price,
                    color)
values  (10, "blue"),
        (20, "green"),
        (30, "yellow");

insert into seats ( scheme_id,
                    `row`,
                    `number`,
                    price_id)
values  (1, 1, 1, 1),
        (1, 1, 2, 1),
        (1, 1, 3, 1),
        (1, 1, 4, 1),

        (1, 2, 1, 2),
        (1, 2, 2, 2),
        (1, 2, 3, 2),
        (1, 2, 4, 2),

        (1, 3, 1, 3),
        (1, 3, 2, 3),
        (1, 3, 3, 3),
        (1, 3, 4, 3),

        (2, 1, 1, 1),
        (2, 1, 2, 1),
        (2, 1, 3, 1),
        (2, 1, 4, 1),

        (2, 2, 1, 2),
        (2, 2, 2, 2),
        (2, 2, 3, 2),
        (2, 2, 4, 2),

        (2, 3, 1, 3),
        (2, 3, 2, 3),
        (2, 3, 3, 3),
        (2, 3, 4, 3);
