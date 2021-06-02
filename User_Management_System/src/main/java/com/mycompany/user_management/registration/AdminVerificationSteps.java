/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user_management.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class AdminVerificationSteps {

    String EnteredEmailAccount;
    String EnteredAdminCode;
    String CodeReceiverAdminEmail;

    public void setEnteredEmailAccount(String EnteredEmailAccount) {
        this.EnteredEmailAccount = EnteredEmailAccount;
    }

    public void setEnteredAdminCode(String EnteredAdminCode) {
        this.EnteredAdminCode = EnteredAdminCode;
    }


    public boolean enteredEmailIsGenuine() throws SQLException {
        Boolean checked = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");
            String Sql_Query = "select * from adminverificationcode where email = ?";

            PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
            Pre_Stat.setString(1, EnteredEmailAccount);
            ResultSet r1 = Pre_Stat.executeQuery();

            if (r1.next()) {
                checked = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdminVerificationSteps.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checked;
    }

    public boolean codeIsRight() throws ClassNotFoundException, SQLException {
        Boolean CodeTested = false;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");

        String Sql_Query = "select * from adminverificationcode where code = ?";
        PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);

        Pre_Stat.setString(1, EnteredAdminCode);
        ResultSet r1 = Pre_Stat.executeQuery();

        if (r1.next()) {
            CodeTested = true;
        }
        return CodeTested;
    }

    public void updateAdminFilled() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "")) {
                String Sql_Query = "update users set admin = ? where email=?";
                PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
                Pre_Stat.setString(1, "Yes");
                Pre_Stat.setString(2, EnteredEmailAccount);
                Pre_Stat.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdminVerificationSteps.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setBackToNormal() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "")) {
                String Sql_Query = "update adminverificationcode set code=?  where serial=1";
                PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
                Pre_Stat.setString(1, "");
                Pre_Stat.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdminVerificationSteps.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
