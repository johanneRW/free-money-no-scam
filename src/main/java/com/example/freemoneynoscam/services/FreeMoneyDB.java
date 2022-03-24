package com.example.freemoneynoscam.services;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class FreeMoneyDB {
    private final File FILE;
    private MysqlDataSource dataSource = new MysqlDataSource();
    private Connection con;


    //Metoden indlæser bruger, kodeord osv. fra en fil, så oplysningerne ikke ligger frit tilgængeligt på GitHub.
    public FreeMoneyDB() {
        FILE = new File("db.password");
        try {
            Scanner sc = new Scanner(FILE);
            dataSource.setUser(sc.nextLine());
            dataSource.setPassword(sc.nextLine());
            dataSource.setServerName(sc.nextLine());
            dataSource.setDatabaseName(sc.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the password-file");
        }
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("connection error");
        }
    }

    public void addEmail(String email) {
        try {

            String sqlString = "INSERT INTO emails (`email_addres`) VALUES(?)";
            PreparedStatement stmt = con.prepareStatement(sqlString);
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error in addEmail-method");
        }
    }

    //https://www.mysqltutorial.org/mysql-count/
    //https://stackoverflow.com/questions/23370243/jdbc-how-to-retrieve-the-result-of-sql-count-function-from-the-result-set
    public boolean hasEmail(String email) {
        try {

            String serchForEmail = "SELECT COUNT(*) FROM emails WHERE `email_addres` = ?";
            PreparedStatement stmt = con.prepareStatement(serchForEmail);
            stmt.setString(1, email);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            rs.next();
            int numEmail = rs.getInt(1);

            return numEmail != 0;
        } catch (SQLException e) {
            System.out.println("error in hasEmail-method");
            return false;
        }
    }
}


