
insert into tickets (   seat_id,
                        show_id,
                        ticket_state_id )
values  (2, 1, 1),  (2, 1, 1),  (3, 1, 2),  (4, 1, 2),
        (5, 1, 2),  (6, 1, 2),  (7, 1, 2),  (8, 1, 2),
        (9, 1, 2),  (10, 1, 2), (12, 1, 2), (12, 1, 2),
        (13, 1, 2), (14, 1, 2), (15, 1, 2), (16, 1, 2);

insert into sold_tickets (ticket_id,
                          user_id,
                          date_time)
values (1, 1, '2021-06-11 13:17:17'), (2, 1, '2021-06-11 13:17:17')