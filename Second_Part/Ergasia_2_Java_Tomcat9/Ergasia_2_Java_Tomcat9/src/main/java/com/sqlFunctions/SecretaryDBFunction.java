package com.sqlFunctions;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbUtil.DBConnection;
import com.university.Secretary;

//includes all the methods that evolves secretaries table in db
public class SecretaryDBFunction {
	private static final String SIGN_UP = "INSERT into secretaries (registnumber, afmnumber, name, surname, acadyearadmission, birthdate, email, password, salt) values (?,?,?,?,?,?,?,?,?)";
	private static final String GET_SECRETARY_DETAILS = "SELECT * FROM secretaries WHERE registnumber=?";
	private static final String GET_SECRETARY_SALT = "SELECT salt FROM secretaries WHERE registnumber=?";
	private static final String SECRETARY_SIGN_IN = "SELECT * FROM secretaries WHERE registnumber= ? AND cast(password as CHAR) = ?";
	
	
	private Connection connection;
	
	public SecretaryDBFunction() {
		connection = DBConnection.connectToDB();
	}
	
	public Secretary GetSecretaryDetails (String username) {
		Secretary secretary = new Secretary();
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(GET_SECRETARY_DETAILS)){
			System.out.println(GET_SECRETARY_DETAILS);
			String registNumber = username;	//temp variable to keep username untouchable
			registNumber = registNumber.substring(1);
			int regNum = Integer.valueOf(registNumber);
			preparedStatement.setInt(1, regNum);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				secretary.setRegistrationNumber(rs.getInt("registnumber"));
				secretary.setAfmNumber(rs.getInt("afmnumber"));
				secretary.setName(rs.getString("name"));
				secretary.setSurname(rs.getString("surname"));
				secretary.setAcadYearAdmission(rs.getInt("acadyearadmission"));
				secretary.setBirthDate(rs.getString("birthdate"));
				secretary.setEmail(rs.getString("email"));
			}
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return secretary;
	}
	
	
	public boolean checkSecretaryAccount(int registNumber)
	{
		boolean rs = false;
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(GET_SECRETARY_DETAILS)){
			System.out.println(GET_SECRETARY_DETAILS);
			preparedStatement.setInt(1, registNumber);
			rs = preparedStatement.execute();
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return rs;
	}
	
	public String getSaltSecretaryAccount(int registNumber)
	{
		String salt = null;
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(GET_SECRETARY_SALT)){
			System.out.println(GET_SECRETARY_SALT);
			preparedStatement.setInt(1, registNumber);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
			{
				salt = rs.getString("salt");
			}
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}		
		return salt;
	}
	
	public boolean signInSecretary(int registNumber, String password)
	{
		boolean answer = false;
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(SECRETARY_SIGN_IN)){
			System.out.println(SECRETARY_SIGN_IN);
			preparedStatement.setInt(1, registNumber);
			preparedStatement.setString(2, password);
			System.out.println("preparedStatement: " + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() == false)
            {
                answer = false; 
                System.out.println("There is already a user with the registnumber: " + registNumber + ", please enter a different registration Number.");
            }
            else
            {
                answer = true; //"ok"
                System.out.println("ok");
            }
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return answer;
	}
	
	
	public void signup(Secretary secretary)
	{
		try (Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(SIGN_UP)){
			System.out.println(SIGN_UP);
			preparedStatement.setInt(1, secretary.getRegistrationNumber());
			preparedStatement.setInt(2, secretary.getAfmNumber());
			preparedStatement.setString(3, secretary.getName());
			preparedStatement.setString(4, secretary.getSurname());
			preparedStatement.setInt(5, secretary.getAcadYearAdmission());
			preparedStatement.setString(6, secretary.getBirthDate());
			preparedStatement.setString(7, secretary.getEmail());
			preparedStatement.setString(8, secretary.getPassword());
			preparedStatement.setString(9, secretary.getSalt());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) 
		{
			printSQLException(e);
		}
	}
	
	public boolean signupregistNumberCheck(int registNumber)
	{
		boolean answer = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_SECRETARY_DETAILS);
			preparedStatement.setInt(1, registNumber);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next() == false)
			{
				answer = true; //"ok";
				System.out.println("ok!");
			}
			else
			{
				answer = false;
				System.out.println("There is already a user with the registnumber: " + registNumber + ", please enter a different registration Number.");
			}
		} 
		catch (SQLException e) {
			printSQLException(e);
		}
		return answer;
	}
	
	//in case we encounter an sql exception
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
