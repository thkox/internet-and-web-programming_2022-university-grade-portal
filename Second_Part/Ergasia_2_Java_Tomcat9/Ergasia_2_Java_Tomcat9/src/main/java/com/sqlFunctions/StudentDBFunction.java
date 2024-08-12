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
import com.university.Student;


//includes all the methods that evolves students table in db
public class StudentDBFunction {
	private static final String SIGN_UP = "INSERT into students (registnumber, name, surname, acadyearadmission, birthdate, email, semester, password, salt) values (?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_STUDENTS_SQL = "INSERT INTO students registnumber = ?, name = ?, surname = ?, acadyearadmission = ?, birthdate = ?, email = ?, password = ?, salt = ?, semester = ?";
	private static final String SELECT_STUDENTS_BY_REGISTNUMBER = "SELECT * FROM students WHERE registnumber=?";
	private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";
	private static final String GET_STUDENT_SALT = "SELECT salt FROM students WHERE registnumber=?";
	private static final String STUDENT_SIGN_IN = "SELECT * FROM students WHERE registnumber= ? AND cast(password as CHAR) = ?";
	private static final String DELETE_STUDENT = "DELETE FROM students WHERE registnumber=?";
	private static final String UPDATE_STUDENT = "UPDATE students SET name=?, surname=?, acadyearadmission=?, birthdate=?, email=?, semester=? WHERE registnumber=?";
	
	private Connection connection;
	
	public StudentDBFunction() {
		connection = DBConnection.connectToDB();
	}
	
	public Student GetStudentDetails (String username) {
        Student student = new Student();
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_REGISTNUMBER)) {
            System.out.println("SELECT * FROM students WHERE registnumber=?");
            String registNumber = username;    //temp variable to keep username untouchable
            registNumber = registNumber.substring(1);
            int regNum = Integer.valueOf(registNumber);
            preparedStatement.setInt(1, regNum);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                student.setRegistrationNumber(rs.getInt("registnumber"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setAcadYearAdmission(rs.getInt("acadyearadmission"));
                student.setBirthDate(rs.getString("birthdate"));
                student.setEmail(rs.getString("email"));
                student.setSemester(rs.getInt("semester"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }
	
	public void deleteStudent(int registNumber) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT);) {
			preparedStatement.setInt(1, registNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
        }
	}
		
	//loipoun oi stiles password kai salt
	public void updateStudent(Student student) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT);) {
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getSurname());
			preparedStatement.setInt(3, student.getAcadYearAdmission());
			preparedStatement.setString(4, student.getBirthDate());
			preparedStatement.setString(5, student.getEmail());
			preparedStatement.setInt(6, student.getSemester());
			preparedStatement.setInt(7, student.getRegistrationNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public Student getStudentByRegistNumber(int RegistNumber) {
		Student student = new Student();
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_REGISTNUMBER);) {
			preparedStatement.setInt(1, RegistNumber);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				student.setRegistrationNumber(rs.getInt("registnumber"));
				student.setName(rs.getString("name"));
				student.setSurname(rs.getString("surname"));
				student.setAcadYearAdmission(rs.getInt("acadyearadmission"));
				student.setBirthDate(rs.getString("birthdate"));
				student.setEmail(rs.getString("email"));
				student.setSemester(rs.getInt("semester"));
			}
			
		} catch (SQLException e) {
			printSQLException(e);
        }
		return student;
	}
	
	public void registerStudent(Student student) throws SQLException {
		try (Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)){
			System.out.println(INSERT_STUDENTS_SQL);
			preparedStatement.setInt(1, student.getRegistrationNumber());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getSurname());
			preparedStatement.setInt(4, student.getAcadYearAdmission());
			preparedStatement.setString(5, student.getBirthDate());
			preparedStatement.setString(6, student.getEmail());
			preparedStatement.setString(7, student.getPassword());
			preparedStatement.setString(8, student.getSalt());
			preparedStatement.setInt(9, student.getSemester());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Student selectStudent(int registnumber) throws SQLException {
		System.out.println(SELECT_STUDENTS_BY_REGISTNUMBER);
		Student student = null;
		// Step 1: Establishing a Connection
		try (Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_REGISTNUMBER);) {
			preparedStatement.setInt(1, registnumber);
			System.out.println(preparedStatement);
			//return the results from the db
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int registNumber = rs.getInt("registnumber");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				int acadYearAdmission = rs.getInt("acadyearadmission");
				String birthDate = rs.getString("birthdate");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String salt = rs.getString("salt");
				int semester = rs.getInt("semester");
				student = new Student(name, surname, email, password, salt, birthDate, acadYearAdmission, registNumber, semester);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}
	
	public boolean checkStudentAccount(int registNumber)
	{
		boolean rs = false;
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_REGISTNUMBER)){
			System.out.println(SELECT_STUDENTS_BY_REGISTNUMBER);
			preparedStatement.setInt(1, registNumber);
			rs = preparedStatement.execute();
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return rs;
	}
	
	public String getSaltStudentAccount(int registNumber)
	{
		String salt = null;
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_SALT)){
			System.out.println(GET_STUDENT_SALT);
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
	
	public boolean signInStudent(int registNumber, String password)
	{
		boolean answer = false;
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(STUDENT_SIGN_IN)){
			System.out.println(STUDENT_SIGN_IN);
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
	
	
	public List<Student> getAllStudents() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Student> students = new ArrayList<Student>();
		// Step 1: Establishing a Connection
		try (Connection connection = DBConnection.connectToDB();
				Statement statement = connection.createStatement();) {
				System.out.println(statement);
				ResultSet rs = statement.executeQuery(SELECT_ALL_STUDENTS);
				while (rs.next()) {
					int registNumber = rs.getInt("registnumber");
					String name = rs.getString("name");
					String surname = rs.getString("surname");
					int acadYearAdmission = rs.getInt("acadyearadmission");
					String birthDate = rs.getString("birthdate");
					String email = rs.getString("email");
					int semester = rs.getInt("semester");
					students.add( new Student(name, surname, email, birthDate, acadYearAdmission, registNumber, semester));
					System.out.println("Name: " + name + "Surname: " + surname + "BirthDate: " + birthDate + "AcadYearAdmission: " + acadYearAdmission + "RegistNumber: " + registNumber + "Semester: " + semester);
				}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
	}
		
	public void signup(Student student)
	{
		try (Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(SIGN_UP)) {
			System.out.println(SIGN_UP);
			preparedStatement.setInt(1, student.getRegistrationNumber());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getSurname());
			preparedStatement.setInt(4, student.getAcadYearAdmission());
			preparedStatement.setString(5, student.getBirthDate());
			preparedStatement.setString(6, student.getEmail());
			preparedStatement.setInt(7, student.getSemester());
			preparedStatement.setString(8, student.getPassword());
			preparedStatement.setString(9, student.getSalt());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) 
		{
			printSQLException(e);
		}
	}
	
	public boolean signUpRegistNumberCheck(int registNumber)
	{
		boolean answer = false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE registnumber=?");
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

