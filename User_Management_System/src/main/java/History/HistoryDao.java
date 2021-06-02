/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package History;

import History.History;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jspcrud.User;
import static jspcrud.UserDao.getConnection;

/**
 *
 * @author pguragain3
 */
public class HistoryDao {

    public HistoryDao() {
    }
    
    public static Connection getConnection(){
	Connection con=null;
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC","root","");
	}catch(ClassNotFoundException | SQLException e){System.out.println(e);}
	return con;
}


    public static void addHistory(History h) throws SQLException {
         Connection conn = getConnection();
         String username= h.getUsername();
        String time = h.getTime();
        String action = h.getAction();

        String query = "insert into history (Username,Time,Action) values (?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, time);
        ps.setString(3, action);

        ps.executeUpdate();

    }

    public static ArrayList<History> getUserHistory(String Username) throws SQLException {
       Connection con = getConnection();
        String username = jspcrud.UserDao.getUsername(Username);

        String query = "select * from history where Username=?"; //get all values for matching username

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();//put the results in the result set

        ArrayList<History> list = new ArrayList<>();//new arraylist which will store all history

         while (rs.next()) {
            History h = new History(rs.getString("username"),rs.getString("time"),rs.getString("action"));//iterate through every row of result set and add it to arraylist
            list.add(h);
        }
        return list;
    }
    public static History getHistoryByUsername(String Username){
	History h=null;
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from history where Username=?");
		ps.setString(1,Username);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			h=new History();
			h.setUsername(rs.getString("Username"));
			h.setTime(rs.getString("Time"));
                        h.setAction(rs.getString("Action"));;
		}
	}catch(Exception e){System.out.println(e);}
	return h;
}
    public static List<History> getAllHistory(){
	List<History> list=new ArrayList<>();
	
	try{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from history");
                ResultSet rs = ps.executeQuery();
		while(rs.next()){
			History h=new History();
                        h.setUsername(rs.getString("Username"));
                        h.setTime(rs.getString("Time"));
			h.setAction(rs.getString("Action"));
                        
			list.add(h);
		}
	}catch(Exception e){System.out.println(e);}
	return list;

//    public static ArrayList<History> getAdminHistory() throws SQLException {
//
//        String query = "select * from history";//get all values from userid
//
//        PreparedStatement ps = conn.prepareStatement(query);
//
//        ResultSet rs = ps.executeQuery();//put the results in the result set
//
//        ArrayList<History> list = new ArrayList<>();//new arraylist which will store all history
//
//        while (rs.next()) {
//            History h = new History(rs.getInt("history_id"), rs.getInt("user_id"), rs.getString("time"), rs.getString("action_performed"));//iterate through every row of result set and add it to arraylist
//            list.add(h);
//        }
//        return list;
//    }
    }
}
