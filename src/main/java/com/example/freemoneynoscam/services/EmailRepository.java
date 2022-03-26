package com.example.freemoneynoscam.services;

import java.sql.*;
import java.util.ArrayList;

public class EmailRepository {

    private final FreeMoneyDB FDB = new FreeMoneyDB();
    private final Connection CON = FDB.getCon();

    public String fetchSingleEmail() {
        try {
            ResultSet rs = getResultSet(1);
            rs.next();
            String email = rs.getString("email_addres");
            return email;

        } catch (SQLException e) {
            return "Problem in fetchSingleEmail";
        }
    }

    public ArrayList<String> fetchFourEmails() {
        ArrayList<String> emails = new ArrayList<>();
        try {
            ResultSet rs = getResultSet(4);
            while (rs.next()) {
                emails.add(rs.getString("email_addres"));
            }
            return emails;
        } catch (SQLException e) {
            System.out.println("problem in fetchAllEmails");
        }
        return null;
    }

    public ArrayList<String> fetchAllEmails() {
        ArrayList<String> emails = new ArrayList<>();
        try {
            PreparedStatement statement =getPreparedStatement();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                emails.add(rs.getString("email_addres"));
            }
            return emails;
        } catch (SQLException e) {
            System.out.println("Problem in fetchAllEmails");
        }
        return null;
    }

    private PreparedStatement getPreparedStatement() {
        try {
        String sqlString = " SELECT `email_addres` FROM `free-money`.emails";
        PreparedStatement statement = CON.prepareStatement(sqlString);
        return statement;
        } catch (SQLException e) {
            System.out.println("Problem in getPreparedStatement");
        }
        return null;
    }

    public ResultSet getResultSet(int numberOfRows) {
        try {
            PreparedStatement statement = getPreparedStatement();
            statement.setMaxRows(numberOfRows);
            ResultSet rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("problem in fetchEmails-method");
        }
        return null;
    }
}
