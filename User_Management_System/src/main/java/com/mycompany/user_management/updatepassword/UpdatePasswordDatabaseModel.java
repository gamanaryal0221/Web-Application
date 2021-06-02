/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user_management.updatepassword;

import com.mycompany.user_management.registration.PasswordEncryptionAndDecryption;
import com.mycompany.user_management.registration.RegistrationDatabaseModel;
import java.io.UnsupportedEncodingException;
import static java.lang.Character.isUpperCase;
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
public class UpdatePasswordDatabaseModel {

    private String Checkemail;
    private String oldPassword;
    private String Updatepassword;
    private String Updateconfirmpassword;
    String CheckUsername;

    public String getCheckUsername() {
        return CheckUsername;
    }

    public void setCheckUsername(String CheckUsername) {
        this.CheckUsername = CheckUsername;
    }

    public String getUpdatepassword() {
        return Updatepassword;
    }

    public void setUpdatepassword(String Updatepassword) {
        this.Updatepassword = Updatepassword;
    }

    public void setUpdateconfirmpassword(String Updateconfirmpassword) {
        this.Updateconfirmpassword = Updateconfirmpassword;
    }

    public void setCheckemail(String Checkemail) {
        this.Checkemail = Checkemail;
    }

    public boolean boxesAreEmpty() {
        Boolean checked = true;
        if ((Checkemail.isEmpty() == false) && (Updatepassword.isEmpty() == false) && (Updateconfirmpassword.isEmpty() == false)) {
            checked = false;
        }
        return checked;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public boolean UsernameOrPasswordAsSameAsEnteredPasswordDoesExist() {
        Boolean checked = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");
            String Sql_Query = "select * from users where username = ? or password = ?";

            PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
            Pre_Stat.setString(1, Updatepassword);
            Pre_Stat.setString(1, Updatepassword);
            ResultSet r1 = Pre_Stat.executeQuery();

            if (r1.next()) {
                checked = true;
            } else {
                checked = false;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistrationDatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checked;
    }

    public boolean enteredEmailIsGenuine() {
        Boolean checked = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");
            String Sql_Query = "select * from resetpasswordrecord where email = ?";

            PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
            Pre_Stat.setString(1, Checkemail);
            ResultSet r1 = Pre_Stat.executeQuery();

            if (r1.next()) {
                checked = true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistrationDatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checked;
    }

    public static boolean OldPasswordIsCorrect(String oldPassword, String username) {
        Boolean status = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");
            String Sql_Query = "select Password from users where Username = ?";

            PreparedStatement ps = conn.prepareStatement(Sql_Query);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pass = rs.getString("Password");

                if (oldPassword.equals(pass)) {
                    status = false;
                } else {
                    status = true;
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(RegistrationDatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean passwordsAreSame() {
        Boolean checked;

        //Checks whether a password are same or not
        if (Updatepassword.equals(Updateconfirmpassword)) {
            checked = true;
        } else {
            checked = false;
        }
        return checked;
    }

    public boolean passwordIsValid() {
        String str = Updatepassword;
        Boolean checked;
        int NumberOfUpperCase = 0;
        int NumberOfSpecialCharacter = 0;
        int NumberOfnumbers = 0;
        int NumberOfdigit = 0;

        for (int i = 0; i < str.length(); i++) {

            int ascii = (int) str.charAt(i);

            //Checks whether a password contain special character or not   
            if (str.charAt(i) == '.' || str.charAt(i) == '!' || str.charAt(i) == '@' || str.charAt(i) == '#' || str.charAt(i) == '$' || str.charAt(i) == '%' || str.charAt(i) == '^' || str.charAt(i) == '&' || str.charAt(i) == '*') {
                NumberOfSpecialCharacter++;

                //Checks whether a password contain uppercase or not
            } else if (isUpperCase(str.charAt(i)) == true) {
                NumberOfUpperCase++;

                //Checks whether a password contain number or not
            } else if ((ascii >= 48 && ascii <= 57)) {
                NumberOfnumbers++;
            }
            NumberOfdigit++;
        }

        if ((NumberOfSpecialCharacter > 0) && (NumberOfUpperCase > 0) && (NumberOfnumbers > 0) && ((NumberOfdigit > 8) && (NumberOfdigit < 16))) {
            checked = true;
        } else {
            checked = false;
        }
        return checked;
    }

    public void updatePassword() {
        try {

            PasswordEncryptionAndDecryption pead = new PasswordEncryptionAndDecryption();
            setUpdatepassword(pead.encryptPassword(Updatepassword));

            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "")) {
                String Sql_Query = "update users set password = ? where email = ?";
                PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
                Pre_Stat.setString(1, Updatepassword);
                Pre_Stat.setString(2, Checkemail);
                Pre_Stat.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException | UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(UpdatePasswordDatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePassword() {

        try {

            PasswordEncryptionAndDecryption pead = new PasswordEncryptionAndDecryption();
            setUpdatepassword(pead.encryptPassword(Updatepassword));
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "")) {
                String Sql_Query = "update users set Password = ? where Username=?";
                PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
                Pre_Stat.setString(1, Updatepassword);
                Pre_Stat.setString(2, CheckUsername);
                Pre_Stat.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException | UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(UpdatePasswordDatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setBackToNormal() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");

            String Sql_Query = "update resetpasswordrecord set code=? , email=?  where serial=1";
            PreparedStatement Pre_Stat = conn.prepareStatement(Sql_Query);
            Pre_Stat.setString(1, "");
            Pre_Stat.setString(2, "");
            Pre_Stat.executeUpdate();
            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdatePasswordDatabaseModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
