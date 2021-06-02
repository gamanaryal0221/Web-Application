package jspcrud;

import com.mycompany.user_management.registration.PasswordEncryptionAndDecryption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    public static int save(User u) {
        int status = 0;

        try {

            PasswordEncryptionAndDecryption pead = new PasswordEncryptionAndDecryption();
            u.setPassword(pead.encryptPassword(u.getPassword()));

            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into users(Admin,Firstname,Lastname,Gender,Phonenumber,Username,Email,Password,Created_date,Blocked_Status) values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, u.getAdmin());
            ps.setString(2, u.getFirstName());
            ps.setString(3, u.getLastName());
            ps.setString(4, u.getGender());
            ps.setString(5, u.getPhonenumber());
            ps.setString(6, u.getUsername());
            ps.setString(7, u.getEmail());
            ps.setString(8, u.getPassword());
            ps.setString(9, java.time.LocalDate.now().toString());
            ps.setString(10, "No");

            status = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int update(User u) {
        int status = 0;
        try {
            PasswordEncryptionAndDecryption pead = new PasswordEncryptionAndDecryption();
            u.setPassword(pead.encryptPassword(u.getPassword()));
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("update users set Admin = ?,Firstname = ?,Lastname = ?,Gender = ?,Phonenumber = ?,Email = ?,Password = ? where Username = ?");
            ps.setString(1, u.getAdmin());
            ps.setString(2, u.getFirstName());
            ps.setString(3, u.getLastName());
            ps.setString(4, u.getGender());
            ps.setString(5, u.getPhonenumber());
            ps.setString(6, u.getEmail());
            ps.setString(7, u.getPassword());
            ps.setString(8, u.getUsername());

            status = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static List<User> getAllRecords() {
        List<User> list = new ArrayList<User>();

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setAdmin(rs.getString("Admin"));
                u.setFirstname(rs.getString("Firstname"));
                u.setLastname(rs.getString("Lastname"));
                u.setPassword(rs.getString("Password"));
                u.setEmail(rs.getString("Email"));
                u.setGender(rs.getString("Gender"));
                u.setUsername(rs.getString("Username"));
                u.setPhonenumber(rs.getString("phonenumber"));
                u.setBlocked_status(rs.getString("Blocked_Status"));
                u.setCreatedDate(rs.getString("Created_date"));
                list.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static User getRecordByUsername(String Username) {
        User u = null;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users where Username=?");
            ps.setString(1, Username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new User();
                u.setAdmin(rs.getString("Admin"));
                u.setFirstname(rs.getString("Firstname"));
                u.setLastname(rs.getString("Lastname"));
                u.setPassword(rs.getString("Password"));
                u.setEmail(rs.getString("Email"));
                u.setGender(rs.getString("Gender"));
                u.setUsername(rs.getString("Username"));
                u.setPhonenumber(rs.getString("Phonenumber"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return u;
    }

    public static int delete(String Username) {
        int status = 0;
        try {
            Connection con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from users where Username=?");
            ps.setString(1, Username);
            status = ps.executeUpdate();

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static String getAdmin(String Username) {
        String result = "";
        try {
            Connection con = UserDao.getConnection();
            String query = "select Admin from users where Username=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Username);

            ResultSet r1 = ps.executeQuery();
            if (r1.next()) {
                String res = r1.getString(1);
                if (res.equals("Yes")) {
                    result = "Yes";
                } else {
                    result = "No";
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }
        return result;
    }

    public static String getUsername(String Username) {
        try {
            Connection con = UserDao.getConnection();
            String query = "select Username from users where Username=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Username);
            ResultSet r1 = ps.executeQuery();
            if (r1.next()) {
                String Uname = r1.getString(1);
                return Uname;
            }
            String Uname = r1.getString(1);
            return Uname;

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static User getProfile(String Username) {

        User u;
        try {
            Connection con = UserDao.getConnection();
            String query = "select Firstname,Lastname,Gender,Phonenumber,Username,Email from users where Username=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(rs.getString("Firstname"), rs.getString("Lastname"), rs.getString("Gender"), rs.getString("Phonenumber"), rs.getString("Username"), rs.getString("Email"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;

    }

    public static void updateUser(User u) {
        try {
            Connection con = UserDao.getConnection();
            String query = "update users set Firstname=?,Lastname=?,Gender=?,Phonenumber=?,Username=?,Email=? where Username=?";//Sql query for updatng user in database

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getGender());
            ps.setString(4, u.getPhonenumber());
            ps.setString(5, u.getUsername());
            ps.setString(6, u.getEmail());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String get_blocked_status(String Username) {
        String result = "No";
        try {
            Connection con = UserDao.getConnection();
            String query = "select Blocked_Status from users where Username=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, Username);

            ResultSet r1 = ps.executeQuery();
            if (r1.next()) {
                String res = r1.getString(1);
                if (res.equals("Yes")) {
                    result = "Yes";
                } else {
                    result = "No";
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return result;
    }

}
