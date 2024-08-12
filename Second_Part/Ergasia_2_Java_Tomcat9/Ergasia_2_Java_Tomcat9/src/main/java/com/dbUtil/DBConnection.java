package com.dbUtil;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static String dbURL = "jdbc:mysql://localhost/gradedb?useSSL=false";
	private static String dbUsername = "root";
	private static String dbPassword = ""; //do not forget to type the correct password
	private static String dbDriver = "com.mysql.jdbc.Driver";
		
	public static Connection connectToDB()	//method to complete the connection with the database
	{
		Connection connect = null;
		try {
        	Class.forName(dbDriver);
        	connect = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        }catch(SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
        	e.printStackTrace();
		}
        return connect;
	}
}
