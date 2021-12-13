//package com.natali.cultickets.repository;
//
//import com.natali.cultickets.model.Ticket;
//import java.util.List;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface TicketRepository  {
//    @Query(value = "from Ticket as t " +
//            "where t not in (" +
//            "   select ticket from SoldTicket" +
//            ") " +
//            "and show.id = :showId")
//    List<Ticket> findByShowId(@Param("showId") int showId);
//
//    @Query(value = "from Ticket as t " +
//            "where t in (" +
//            "           select ticket from SoldTicket as s where s.user.id = :userId " +
//            "       )")
//    List<Ticket> findByUserId(@Param("userId") int userId);
//}
