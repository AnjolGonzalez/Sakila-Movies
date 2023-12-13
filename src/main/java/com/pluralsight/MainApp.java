package com.pluralsight;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static String url = "jdbc:mysql://localhost:3306/sakila";
    public static String user = "root";
    public static String myPassword = System.getenv("MY_DB_PASSWORD");
    public static String password = "!blob";
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        FilmManager filmManager = new FilmManager(dataSource);

        System.out.println("""
                1) Search by last name of actor:
                2) Search movies by Actor ID:
                """);
        String choice = scanner.nextLine();
        String lastName;


        if (choice.equalsIgnoreCase("1")) {
            System.out.println("Please enter last name of favorite actor");
            lastName = scanner.nextLine();

            List<String> actors = filmManager.allActors(lastName);

            actors.forEach(System.out::println);
            //Super String prints string and terminates line

        } else if (choice.equalsIgnoreCase("2")) {
            System.out.print("Enter favorite actor id: ");
            String id = scanner.nextLine();
            List films = filmManager.filmActors(id);
            films.forEach(System.out::println);
        }
    }
}
