package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmManager {
    private DataSource dataSource;

    public FilmManager (BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<String> allActors(String lastName) {
        List<String> actors = new ArrayList<>();
            String query = "SELECT First_Name FROM Actor WHERE Last_Name = ?";
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {
                preparedStatement.setString(1, lastName);
                ResultSet results = preparedStatement.executeQuery();
                while (results.next()) {
                    String firstName = results.getString("First_Name");
                    actors.add("Selected name - " + firstName + " " + lastName);
            }
        }catch (SQLException e){
                e.printStackTrace();
            }
            return actors;
    }

    public List<String> filmActors(String id) {
        List<String> films = new ArrayList<>();
        String query = "SELECT Film. * FROM Film JOIN Film_Actor ON Film.Film_ID = Film_Actor.Film_ID WHERE Film_Actor.Actor_ID = ?";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, id);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                String title = results.getString("title");
                films.add("Your favorite actor features in " + id + " " + title);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return films;

    }

}
