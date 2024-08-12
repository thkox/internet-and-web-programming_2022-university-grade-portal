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
import com.university.Course;
import com.university.Grade;
import com.university.Student;

//includes all the methods that evolves students table in db
public class StudentDBFunction {
	private static final String INSERT_INTO_STUDENTS = "INSERT into students (registnumber, name, surname, acadyearadmission, birthdate, email, semester, password, salt) values (?,?,?,?,?,?,?,?,?);";
	private static final String INSERT_STUDENTS_SQL = "INSERT INTO students registnumber = ?, name = ?, surname = ?, acadyearadmission = ?, birthdate = ?, email = ?, password = ?, salt = ?, semester = ?;";
	
	private static final String SELECT_STUDENTS_BY_REGISTNUMBER = "SELECT * FROM students WHERE registnumber=?;";
	private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students;";
	private static final String SELECT_STUDENT_SALT = "SELECT salt FROM students WHERE registnumber=?;";
	private static final String SELECT_STUDENT_SIGN_IN = "SELECT * FROM students WHERE registnumber= ? AND cast(password as CHAR) = ?;";
	private static final String SELECT_COURSES_BY_STUDENT = "SELECT courses_idcourses AS idcourses, courses.title AS title, exergrade, examgrade, finalgrade FROM courses_has_students INNER JOIN courses ON courses_idcourses=idcourses WHERE courses_has_students.students_registnumber = ?;";
	private static final String SELECT_COURSES_AND_GRADES_BY_STUDENT = "SELECT c.semester AS semester, c.idcourses AS idcourses, c.title AS title, c.type AS type, cs.exergrade AS exergrade, cs.examgrade AS examgrade, cs.finalgrade AS finalgrade FROM (courses AS c INNER JOIN courses_has_students AS cs ON c.idcourses = cs.courses_idcourses) WHERE cs.students_registnumber = ? ORDER BY semester ASC;";
	private static final String SELECT_COURSES_BY_SEMESTER = "SELECT c.idcourses AS idcourses, c.title AS title, c.`type` AS `type`, cs.exergrade AS exergrade, cs.examgrade AS examgrade, cs.finalgrade AS finalgrade FROM (courses AS c INNER JOIN courses_has_students AS cs ON c.idcourses = cs.courses_idcourses) WHERE cs.students_registnumber = ? AND semester = ? ORDER BY idcourses ASC;";
	private static final String SELECT_AVG_GRADES_BY_STUDENT_BY_SEMESTER = "SELECT CAST(AVG(finalgrade) AS DEC(10,2)) AS finalgrade FROM (courses AS c INNER JOIN courses_has_students AS cs ON c.idcourses = cs.courses_idcourses) WHERE students_registnumber = ? AND semester = ? AND finalGrade >=5;";
	private static final String SELECT_AVG_GRADES_BY_STYDENT = "SELECT CAST(AVG(finalgrade) AS DEC(10,2)) AS finalgrade FROM courses_has_students WHERE students_registnumber = ? AND finalGrade >=5;";
	private static final String SELECT_ALL_SEMESTERS = "SELECT DISTINCT c.semester AS semester FROM (courses AS c INNER JOIN courses_has_students AS cs ON c.idcourses = cs.courses_idcourses) WHERE cs.students_registnumber = ?;"; 
		
	private static final String DELETE_STUDENT = "DELETE FROM students WHERE registnumber=?;";
	private static final String DELETE_ASSIGNED_COURSES = "DELETE FROM courses_has_students WHERE students_registnumber=?;";

	private static final String UPDATE_STUDENT = "UPDATE students SET name=?, surname=?, acadyearadmission=?, birthdate=?, email=?, semester=? WHERE registnumber=?;";
	
	private Connection connection;
	
	public StudentDBFunction() {
		connection = DBConnection.connectToDB();
	}
	
	public Student getStudentDetails (String username) {
        Student student = new Student();
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_REGISTNUMBER)) {
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

	public List<Integer> getSemesters(int registNumber) {
        List<Integer> semesters = new ArrayList<Integer>();
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SEMESTERS)) {
            preparedStatement.setInt(1, registNumber);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                semesters.add(rs.getInt("semester"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return semesters;
    }
	
	public List<Course> getCoursesBySemester(int registNumber, int semester){
		List<Course> courses = new ArrayList<Course>();
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_SEMESTER)) {
            preparedStatement.setInt(1, registNumber);
            preparedStatement.setInt(2, semester);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	Course course = new Course();
				course.setTitle(rs.getString("title"));
				course.setIdcourse(rs.getInt("idcourses"));
				course.setExerGrade(rs.getInt("exergrade"));
				course.setExamGrade(rs.getInt("examgrade"));
				course.setFinalGrade(rs.getInt("finalgrade"));
				courses.add(course);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return courses;
	}
	
	public double getFinalAVGGrade(int registNumber) {
		double avgNumber = 0;
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVG_GRADES_BY_STYDENT);) {
			preparedStatement.setInt(1, registNumber);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {	
				avgNumber = rs.getDouble("finalgrade");
			}
		}catch (SQLException e) {
            printSQLException(e);
        }
		return avgNumber;
	}
	
	public double getFinalAVGGradeSemester(int registNumber, int semester){
		double avgNumber = 0;
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVG_GRADES_BY_STUDENT_BY_SEMESTER);) {
			preparedStatement.setInt(1, registNumber);
			preparedStatement.setInt(2, semester);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {	
				avgNumber = rs.getDouble("finalgrade");
			}
		}catch (SQLException e) {
            printSQLException(e);
        }
		return avgNumber;
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
		
	
	public void deleteStudentAssignedCourses(int registNumber) {
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ASSIGNED_COURSES);) {
            preparedStatement.setInt(1, registNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
	
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
			preparedStatement.setInt(1, student.getRegistrationNumber());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getSurname());
			preparedStatement.setInt(4, student.getAcadYearAdmission());
			preparedStatement.setString(5, student.getBirthDate());
			preparedStatement.setString(6, student.getEmail());
			preparedStatement.setString(7, student.getPassword());
			preparedStatement.setString(8, student.getSalt());
			preparedStatement.setInt(9, student.getSemester());
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Student selectStudent(int registnumber) throws SQLException {
		Student student = null;
		try (Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_REGISTNUMBER);) {
			preparedStatement.setInt(1, registnumber);
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
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_SALT)){
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
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_SIGN_IN)){
			preparedStatement.setInt(1, registNumber);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() == false)
            {
                answer = false; 
            }
            else
            {
                answer = true;
            }
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return answer;
	}
	
	
	public List<Student> getAllStudents() {

		List<Student> students = new ArrayList<Student>();
		try (Connection connection = DBConnection.connectToDB();
				Statement statement = connection.createStatement();) {
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
				}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
	}
		
	public void signup(Student student)
	{
		try (Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_STUDENTS)) {
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
				answer = true;
			}
			else
			{
				answer = false;
			}
		} 
		catch (SQLException e) {
			printSQLException(e);
		}
		return answer;
	}
	
	public List<Grade> getCoursesByStudent(int registNumber){
		List<Grade> Grades = new ArrayList<Grade>();
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_STUDENT);) {
			preparedStatement.setInt(1, registNumber);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Grade grade = new Grade();
				grade.course.setTitle(rs.getString("title"));
				grade.course.setIdcourse(rs.getInt("courses_idcourses"));
				grade.course.setExerGrade(rs.getInt("exergrade"));
				grade.course.setExamGrade(rs.getInt("examgrade"));
				grade.course.setFinalGrade(rs.getInt("finalgrade"));
				Grades.add(grade);
			}

		}catch (SQLException e) {
            printSQLException(e);
        }
		return Grades;
	}
	
	public List<Course> getCoursesWithGrades(int registNumber){
		List<Course> Courses = new ArrayList<Course>();
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSES_AND_GRADES_BY_STUDENT);) {
			preparedStatement.setInt(1, registNumber);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setSemester(rs.getInt("semester"));
				course.setIdcourse(rs.getInt("idcourses"));
				course.setTitle(rs.getString("title"));
				course.setType(rs.getString("type"));
				course.setExerGrade(rs.getInt("exergrade"));
				course.setExamGrade(rs.getInt("examgrade"));
				course.setFinalGrade(rs.getInt("finalgrade"));
				Courses.add(course);
			}
		}catch (SQLException e) {
            printSQLException(e);
        }
		return Courses;
	}
	
	//encounter an sql exception
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					t = t.getCause();
				}
			}
		}
	}
}

