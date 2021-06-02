/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user_management.login;

import com.mycompany.user_management.registration.PasswordEncryptionAndDecryption;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Asus
 */
public class LoginNeeds {

    String Loginemail;
    String LoginPassword;

    public String getLoginemail() {
        return Loginemail;
    }

    public void setLoginemail(String Loginemail) {
        this.Loginemail = Loginemail;
    }

    public String getLoginPassword() {
        return LoginPassword;
    }

    public void setLoginPassword(String LoginPassword) {
        this.LoginPassword = LoginPassword;
    }

    public boolean doLogin() {
        boolean abletologin = false;
        try {

            PasswordEncryptionAndDecryption pead = new PasswordEncryptionAndDecryption();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");
            String Sql_Query = "select * from users where (username = ? and password = ?)";

            PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
            Pre_Stat.setString(1, Loginemail);
            Pre_Stat.setString(2, pead.encryptPassword(LoginPassword));
            ResultSet r1 = Pre_Stat.executeQuery();

            if (r1.next() == true) {
                abletologin = true;
            }
        } catch (SQLException | ClassNotFoundException | UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(LoginNeeds.class.getName()).log(Level.SEVERE, null, ex);
        }
        return abletologin;
    }

}
