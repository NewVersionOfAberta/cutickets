package com.natali.cultickets.repository;

import com.natali.cultickets.db.DataAccessConfig;
import com.natali.cultickets.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TicketRepository  {

    @Autowired
    private DataAccessConfig config;

    public List<Ticket> findByScheduledShowId(int ssId) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select t.t_id, t.t_price, ts.ts_name, se.se_number, se.se_row, " +
                        "sec.sec_name, h.h_name, th.t_name from ticket as t " +
                        "left join scheduled_show as ss on ss.ss_id = t.t_scheduled_show_id " +
                        "left join ticket_status as ts on ts.ts_id = t.t_ticket_status_id " +
                        "left join seat as se on se.se_id = t.t_seat_id " +
                        "left join sector as sec on sec.sec_id = se.se_sector_id " +
                        "left join hall as h on h.h_id = sec.sec_hall_id " +
                        "left join theatre as th on th.t_id = h.h_theatre_id " +
                        "where ss.ss_id = ?");
        preparedStatement.setInt(1, ssId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Ticket> tickets = new ArrayList<>();

        while (resultSet.next()) {
            Ticket ticket = new Ticket();
            Seat seat = new Seat();
            TheaterHall hall = new TheaterHall();
            Sector sector = new Sector();
            Theatre theatre = new Theatre();
            TicketStatus ticketStatus = new TicketStatus();
            ticket.setId(resultSet.getInt("t_id"));
            ticket.setPrice(resultSet.getInt("t_price"));
            seat.setNumber(resultSet.getInt("se.se_number"));
            seat.setRow(resultSet.getInt("se.se_row"));
            sector.setName(resultSet.getString("sec.sec_name"));
            hall.setName(resultSet.getString("h.h_name"));
            theatre.setName(resultSet.getString("th.t_name"));
            ticketStatus.setName(resultSet.getString("ts.ts_name"));
            ticket.setSeat(seat);
            ticket.setTheaterHall(hall);
            ticket.setSector(sector);
            ticket.setTheatre(theatre);
            ticket.setTicketStatus(ticketStatus);
            tickets.add(ticket);
        }
        resultSet.close();
        preparedStatement.close();
        return tickets;
    }
}
