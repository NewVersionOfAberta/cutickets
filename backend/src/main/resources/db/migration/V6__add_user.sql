insert into users ( user_name,
                    email,
                    password,
                    name,
                    last_name,
                    active)
values ("katya",
        "m@mail.ru",
        "$2a$10$pNQc9jbHgMq0ZkVsD.1CPOhWw2oc8KhkanTTdnVZXHwO6dDyREQ1W",
        "Natalia",
        "Test",
        1);

insert into user_role (user_id, role_id)
values (4, 2);