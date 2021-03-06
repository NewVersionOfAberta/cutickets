package com.natali.cultickets.repository;

import com.natali.cultickets.db.DataAccessConfig;
import com.natali.cultickets.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class ShowRepository {

    @Autowired
    private DataAccessConfig config;

    public List<Show> findAll() throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select sh.sh_id, sh.sh_name, sh.sh_description, ar.ar_name, t.t_name, c.c_name" +
                        " from `show` as sh" +
                        " left join age_rating as ar on ar.ar_id = sh.sh_age_rating_id" +
                        " left join theatre as t on t.t_id = sh.sh_theatre_id" +
                        " left join address as ad on ad.add_id = t.t_address_id" +
                        " left join city as c on c.c_id = ad.add_city_id");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Show> showList = new ArrayList<>();

        while (resultSet.next()) {
            Show show = new Show();
            Theatre theatre = new Theatre();
            AgeRating ageRating = new AgeRating();
            City city = new City();

            show.setId(resultSet.getInt("sh_id"));
            show.setName(resultSet.getString("sh_name"));
            show.setDescription(resultSet.getString("sh_description"));
            ageRating.setName(resultSet.getString("ar_name"));
            show.setAgeRating(ageRating);
            theatre.setName(resultSet.getString("t_name"));
            city.setName(resultSet.getString("c_name"));
            theatre.setCity(city);
            show.setTheatre(theatre);

            PreparedStatement genreStatement = connection.prepareStatement(
                    "call get_show_genres(?);");
            genreStatement.setInt(1, show.getId());
            ResultSet genreResultSet = genreStatement.executeQuery();
            Set<Genre> genres = new HashSet<>();
            while (genreResultSet.next()) {
                Genre genre = new Genre();
                genre.setId(genreResultSet.getInt("g_id"));
                genre.setName(genreResultSet.getString("g_name"));
                genres.add(genre);
            }
            genreResultSet.close();
            genreStatement.close();
            show.setGenre(genres);
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
        List<Show> showList = new ArrayList<>();

        while (resultSet.next()) {
            Show show = new Show();
            Theatre theatre = new Theatre();
            AgeRating ageRating = new AgeRating();
            show.setId(resultSet.getInt("sh_id"));
            show.setName(resultSet.getString("sh_name"));
            show.setDescription(resultSet.getString("sh_description"));
            ageRating.setName(resultSet.getString("ar_name"));
            show.setAgeRating(ageRating);
            theatre.setName(resultSet.getString("t_name"));
            show.setTheatre(theatre);

            PreparedStatement genreStatement = connection.prepareStatement(
                    "call get_show_genres(?);");
            genreStatement.setInt(1, show.getId());
            ResultSet genreResultSet = genreStatement.executeQuery();
            Set<Genre> genres = new HashSet<>();
            while (genreResultSet.next()) {
                Genre genre = new Genre();
                genre.setId(genreResultSet.getInt("g_id"));
                genre.setName(genreResultSet.getString("g_name"));
                genres.add(genre);
            }
            genreResultSet.close();
            genreStatement.close();
            show.setGenre(genres);
            showList.add(show);
        }
        resultSet.close();
        preparedStatement.close();
        return showList;
    }

    public List<Show> findByTheatre(int id) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select sh.sh_id, sh.sh_name, sh.sh_description, " +
                        "ar.ar_name, t.t_name, c.c_name from `show` as sh " +
                        "join age_rating as ar on ar.ar_id = sh.sh_age_rating_id " +
                        "join theatre as t on t.t_id = sh.sh_theatre_id " +
                        "join address as ad on ad.add_id = t.t_address_id " +
                        "join city as c on c.c_id = ad.add_city_id " +
                        "where t.t_id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Show> showList = new ArrayList<>();

        while (resultSet.next()) {
            Show show = new Show();
            Theatre theatre = new Theatre();
            AgeRating ageRating = new AgeRating();
            show.setId(resultSet.getInt("sh_id"));
            show.setName(resultSet.getString("sh_name"));
            show.setDescription(resultSet.getString("sh_description"));
            ageRating.setName(resultSet.getString("ar_name"));
            show.setAgeRating(ageRating);
            theatre.setName(resultSet.getString("t_name"));
            show.setTheatre(theatre);

            PreparedStatement genreStatement = connection.prepareStatement(
                    "call get_show_genres(?);");
            genreStatement.setInt(1, show.getId());
            ResultSet genreResultSet = genreStatement.executeQuery();
            Set<Genre> genres = new HashSet<>();
            while (genreResultSet.next()) {
                Genre genre = new Genre();
                genre.setId(genreResultSet.getInt("g_id"));
                genre.setName(genreResultSet.getString("g_name"));
                genres.add(genre);
            }
            genreResultSet.close();
            genreStatement.close();
            show.setGenre(genres);
            showList.add(show);
        }
        resultSet.close();
        preparedStatement.close();
        return showList;
    }

    public List<Show> findByGenre(int id) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select sh.sh_id, sh.sh_name, sh.sh_description, " +
                        "ar.ar_name, t.t_name, c.c_name from `show` as sh " +
                        "join age_rating as ar on ar.ar_id = sh.sh_age_rating_id " +
                        "join theatre as t on t.t_id = sh.sh_theatre_id " +
                        "join address as ad on ad.add_id = t.t_address_id " +
                        "join city as c on c.c_id = ad.add_city_id " +
                        "join mtm_show_genre as mtm on mtm.mtm_show_id = sh.sh_id " +
                        "where mtm.mtm_genre_id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Show> showList = new ArrayList<>();

        while (resultSet.next()) {
            Show show = new Show();
            Theatre theatre = new Theatre();
            AgeRating ageRating = new AgeRating();
            show.setId(resultSet.getInt("sh_id"));
            show.setName(resultSet.getString("sh_name"));
            show.setDescription(resultSet.getString("sh_description"));
            ageRating.setName(resultSet.getString("ar_name"));
            show.setAgeRating(ageRating);
            theatre.setName(resultSet.getString("t_name"));
            show.setTheatre(theatre);

            PreparedStatement genreStatement = connection.prepareStatement(
                    "call get_show_genres(?);");
            genreStatement.setInt(1, show.getId());
            ResultSet genreResultSet = genreStatement.executeQuery();
            Set<Genre> genres = new HashSet<>();
            while (genreResultSet.next()) {
                Genre genre = new Genre();
                genre.setId(genreResultSet.getInt("g_id"));
                genre.setName(genreResultSet.getString("g_name"));
                genres.add(genre);
            }
            genreResultSet.close();
            genreStatement.close();
            show.setGenre(genres);
            showList.add(show);
        }
        resultSet.close();
        preparedStatement.close();
        return showList;
    }

    public Show getShowInfo(int showId) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select sh.sh_id, sh.sh_name, sh.sh_description, " +
                        "ar.ar_name, t.t_name, c.c_name from `show` as sh " +
                        "left join age_rating as ar on ar.ar_id = sh.sh_age_rating_id " +
                        "left join theatre as t on t.t_id = sh.sh_theatre_id " +
                        "left join address as ad on ad.add_id = t.t_address_id " +
                        "left join city as c on c.c_id = ad.add_city_id " +
                        "where sh.sh_id = ?");
        preparedStatement.setInt(1, showId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Show show = new Show();
        Theatre theatre = new Theatre();
        AgeRating ageRating = new AgeRating();
        resultSet.next();

        show.setId(resultSet.getInt("sh_id"));
        show.setName(resultSet.getString("sh_name"));
        show.setDescription(resultSet.getString("sh_description"));
        ageRating.setName(resultSet.getString("ar_name"));
        show.setAgeRating(ageRating);
        theatre.setName(resultSet.getString("t_name"));
        show.setTheatre(theatre);

        PreparedStatement genreStatement = connection.prepareStatement(
                "call get_show_genres(?);");
        genreStatement.setInt(1, show.getId());
        ResultSet genreResultSet = genreStatement.executeQuery();
        Set<Genre> genres = new HashSet<>();
        while (genreResultSet.next()) {
            Genre genre = new Genre();
            genre.setId(genreResultSet.getInt("g_id"));
            genre.setName(genreResultSet.getString("g_name"));
            genres.add(genre);
        }
        genreResultSet.close();
        genreStatement.close();
        show.setGenre(genres);

        resultSet.close();
        preparedStatement.close();
        return show;
    }

    public List<Show> findScheduledShowsByShow(int showId) throws SQLException {
        Connection connection = config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select ss.ss_id, ss.ss_time from scheduled_show as ss " +
                        "left join `show` as sh on sh.sh_id = ss.ss_show_id " +
                        "where sh.sh_id = ?");
        preparedStatement.setInt(1, showId);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Show> showList = new ArrayList<>();

        while (resultSet.next()) {
            Show show = new Show();
            show.setId(resultSet.getInt("ss_id"));
            LocalDateTime datetime = resultSet.getTimestamp("ss_time").toLocalDateTime();
            String[] dts = datetime.toString().split("T");
            show.setDatetime(dts[0] + " " + dts[1]);
            showList.add(show);
        }
        resultSet.close();
        preparedStatement.close();
        return showList;
    }

}
