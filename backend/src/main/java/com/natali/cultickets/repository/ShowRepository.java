package com.natali.cultickets.repository;

import com.natali.cultickets.db.DataAccessConfig;
import com.natali.cultickets.model.AgeRating;
import com.natali.cultickets.model.Genre;
import com.natali.cultickets.model.Show;
import com.natali.cultickets.model.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class ShowRepository {

    @Autowired
    private DataAccessConfig config;

    public List<Show> findAll() throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select ss.ss_id, ss.ss_name, ss.ss_description, ss_time, ar.ar_name, t.t_name, t.t_description" +
                        "from scheduled_show as ss" +
                        "left join age_rating as ar on ar.ar_id = ss.ss_age_rating_id" +
                        "left join theatre as t on t.t_id = ss.ss_theatre_id");
        ResultSet resultSet = preparedStatement.executeQuery();
        Show show = new Show();
        Theatre theatre = new Theatre();
        AgeRating ageRating = new AgeRating();
        List<Show> showList = new ArrayList<>();

        while (resultSet.next()) {
            show.setId(resultSet.getInt("t_id"));
            show.setName(resultSet.getString("t_name"));
            show.setDescription(resultSet.getString("t_description"));
            ageRating.setName(resultSet.getString("ar_name"));
            show.setAgeRating(ageRating);
            theatre.setName(resultSet.getString("t_name"));
            theatre.setDescription(resultSet.getString("t_description"));
            show.setTheatre(theatre);
            showList.add(show);
        }
        resultSet.close();
        return showList;
    }

    public List<Show> findSuitableForUser(int id) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "call find_suitable_user_shows(?);");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Show show = new Show();
        Theatre theatre = new Theatre();
        AgeRating ageRating = new AgeRating();
        Genre genre = new Genre();
        List<Show> showList = new ArrayList<>();

        while (resultSet.next()) {
            show.setId(resultSet.getInt("sh_id"));
            show.setName(resultSet.getString("sh_name"));
            show.setDescription(resultSet.getString("sh_description"));
            ageRating.setName(resultSet.getString("ar_name"));
            show.setAgeRating(ageRating);
            theatre.setName(resultSet.getString("t_name"));
            show.setTheatre(theatre);
            showList.add(show);

            PreparedStatement genreStatement = connection.prepareStatement(
                    "call get_show_genres(?);");
            genreStatement.setInt(1, show.getId());
            ResultSet genreResultSet = genreStatement.executeQuery();
            Set<Genre> genres = new HashSet<>();
            while(genreResultSet.next()) {
                genre.setName(genreResultSet.getString("g_name"));
                genres.add(genre);
            }
            show.setGenre(genres);
        }
        resultSet.close();
        return showList;
    }

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

}
