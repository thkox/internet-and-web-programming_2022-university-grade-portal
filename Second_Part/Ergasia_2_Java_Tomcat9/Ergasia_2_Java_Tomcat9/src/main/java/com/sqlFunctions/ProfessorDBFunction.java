package com.sqlFunctions;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbUtil.DBConnection;
import com.university.Professor;

//includes all the methods that evolves professors table in db
public class ProfessorDBFunction {
	private static final String SIGN_UP = "INSERT into professors (registnumber, afmnumber, name, surname, acadyearadmission, birthdate, email, password, salt) values (?,?,?,?,?,?,?,?,?)";
	private static final String GET_PROFESSOR_DETAILS = "SELECT * FROM professors WHERE registnumber=?";
	private static final String GET_PROFESSOR_SALT = "SELECT salt FROM professors WHERE registnumber=?";
	private static final String PROFESSOR_SIGN_IN = "SELECT * FROM professors WHERE registnumber= ? AND cast(password as CHAR) = ?";
	private static final String DELETE_PROFESSOR = "DELETE FROM professors WHERE registnumber=?";
	private static final String UPDATE_PROFESSOR = "UPDATE professors SET afmnumber=?, name=?, surname=?, acadyearadmission=?, birthdate=?, email=? WHERE registnumber=?";
	private static final String DELETE_ASSIGNMENT = "DELETE FROM courses_has_professors WHERE professors_registnumber=?";
	
	private Connection connection;
	
	public ProfessorDBFunction() {
		connection = DBConnection.connectToDB();
	}
		
	public Professor GetProfessorDetails (String username) {
        Professor professor = new Professor();
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_PROFESSOR_DETAILS)) {
            System.out.println(GET_PROFESSOR_DETAILS);
            String registNumber = username;    //temp variable to keep username untouchable
            registNumber = registNumber.substring(1);
            int regNum = Integer.valueOf(registNumber);
            preparedStatement.setInt(1, regNum);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                professor.setRegistrationNumber(rs.getInt("registnumber"));
                professor.setAfmNumber(rs.getInt("afmnumber"));
                professor.setName(rs.getString("name"));
                professor.setSurname(rs.getString("surname"));
                professor.setAcadYearAdmission(rs.getInt("acadyearadmission"));
                professor.setBirthDate(rs.getString("birthdate"));
                professor.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return professor;
    }
	
	public void deleteProfessorAssign(int registNumber) {
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ASSIGNMENT);) {
            preparedStatement.setInt(1, registNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	printSQLException(e);
        }
    }
	
	public void deleteProfessor(int registNumber) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROFESSOR);) {
			preparedStatement.setInt(1, registNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	//loipoun oi stiles password kai salt
	public void updateProfessor(Professor professor) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFESSOR);) {
			preparedStatement.setInt(1, professor.getAfmNumber());
			preparedStatement.setString(2, professor.getName());
			preparedStatement.setString(3, professor.getSurname());
			preparedStatement.setInt(4, professor.getAcadYearAdmission());
			preparedStatement.setString(5, professor.getBirthDate());
			preparedStatement.setString(6, professor.getEmail());
			preparedStatement.setInt(7, professor.getRegistrationNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	public Professor getProfessorByRegistNumber(int RegistNumber) {
		Professor professor = new Professor();
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_PROFESSOR_DETAILS);) {
			preparedStatement.setInt(1, RegistNumber);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				professor.setRegistrationNumber(rs.getInt("registnumber"));
				professor.setAfmNumber(rs.getInt("afmnumber"));
				professor.setName(rs.getString("name"));
				professor.setSurname(rs.getString("surname"));
				professor.setAcadYearAdmission(rs.getInt("acadyearadmission"));
				professor.setBirthDate(rs.getString("birthdate"));
				professor.setEmail(rs.getString("email"));
			}
			
		} catch (SQLException e) {
			printSQLException(e);
        }
		return professor;
	}
	
	public List<Professor> getAllProfessors() {
		List<Professor> Professors = new ArrayList<Professor>();
		try(Connection connection = DBConnection.connectToDB();		
				Statement statement = connection.createStatement()) {
			System.out.println(statement);
			ResultSet rs = statement.executeQuery("SELECT * FROM professors");
			while (rs.next())  {
				int registNumber = rs.getInt("registnumber");
				int afmnumber = rs.getInt("afmnumber");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				int acadyearadmission = rs.getInt("acadyearadmission");
				String birthdate = rs.getString("birthdate");
				String email = rs.getString("email");
				Professors.add(new Professor(name, surname, email, birthdate, acadyearadmission, registNumber, afmnumber));
				System.out.println("Name: " + name + "Surname: " + surname + "Email: " + email + "Birthdate: " + birthdate + "AcadyearAdmission " + acadyearadmission + "Regist Number: " + registNumber + "A.F.M.: " + afmnumber);
			}
		} catch (SQLException e) {
			printSQLException(e);
        }
		return Professors;
	}
	
	public boolean checkProfessorAccount(int registNumber)
	{
		boolean rs = false;
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(GET_PROFESSOR_DETAILS)){
			System.out.println(GET_PROFESSOR_DETAILS);
			preparedStatement.setInt(1, registNumber);
			rs = preparedStatement.execute();
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return rs;
	}
	
	public String getSaltProfessorAccount(int registNumber)
	{
		String salt = null;
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(GET_PROFESSOR_SALT)){
			System.out.println(GET_PROFESSOR_SALT);
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
	
	public void signup(Professor professor)
	{
		try (Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(SIGN_UP)){
			System.out.println(SIGN_UP);
			preparedStatement.setInt(1, professor.getRegistrationNumber());
			preparedStatement.setInt(2, professor.getAfmNumber());
			preparedStatement.setString(3, professor.getName());
			preparedStatement.setString(4, professor.getSurname());
			preparedStatement.setInt(5, professor.getAcadYearAdmission());
			preparedStatement.setString(6, professor.getBirthDate());
			preparedStatement.setString(7, professor.getEmail());
			preparedStatement.setString(8, professor.getPassword());
			preparedStatement.setString(9, professor.getSalt());
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
			PreparedStatement preparedStatement = connection.prepareStatement(GET_PROFESSOR_DETAILS);
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
				System.out.println("There is already a user with the registnumber: " + registNumber);
			}
		} 
		catch (SQLException e) {
			printSQLException(e);
		}
		return answer;
	}
	
	public boolean signInProfessor(int registNumber, String password)
	{
		boolean answer = false;
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(PROFESSOR_SIGN_IN)){
			System.out.println(PROFESSOR_SIGN_IN);
			preparedStatement.setInt(1, registNumber);
			preparedStatement.setString(2, password);
			System.out.println("preparedStatement: " + preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() == false)
            {
                answer = false; 
                System.out.println("There is already a user with the registnumber: " + registNumber);
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
