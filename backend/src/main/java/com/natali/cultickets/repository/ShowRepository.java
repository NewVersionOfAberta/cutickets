//package com.natali.cultickets.repository;
//
//import com.natali.cultickets.model.Show;
//import com.natali.cultickets.model.ShowType;
//import com.natali.cultickets.model.Theater;
//import java.util.List;
//import javax.transaction.Transactional;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface ShowRepository extends CrudRepository<Show, Integer> {
//    List<Show> findAll();
//
//    List<Show> findByTheater(Theater theater);
//
//    List<Show> findByShowType(ShowType showType);
//
//    List<Show> findByTheaterAndShowType(Theater theater, ShowType type);
//
//    @Modifying
//    @Transactional
//    @Query(value = "update Show as s set s.name = :name, s.description = :description\n" +
//            "where s.id = :show_id")
//    int updateShow(@Param("show_id") int showId,
//                   @Param("name") String name,
//                   @Param("description") String description);
//
//}
