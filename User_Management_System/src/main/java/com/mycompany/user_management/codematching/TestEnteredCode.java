/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user_management.codematching;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class TestEnteredCode {
    
    String CodeFromUser;

    public void setCodeFromUser(String CodeFromUser) {
        this.CodeFromUser = CodeFromUser;
    }

    public boolean codeIsRight() throws ClassNotFoundException, SQLException {
        Boolean CodeTested = false;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");

        String Sql_Query = "select * from resetpasswordrecord where code = ?";
        PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);

        Pre_Stat.setString(1, CodeFromUser);
        ResultSet r1 = Pre_Stat.executeQuery();

        if (r1.next()) {
            CodeTested = true;
        }
        return CodeTested;
    }
}
