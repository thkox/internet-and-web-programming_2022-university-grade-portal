package com.sqlFunctions;

//Apostolis Siampanis P20173, Theodoros Koxanoglou P20094, Aimilianos Kourpas Danas P20100, Aggeliki Kaldiri P20069

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.university.AssignCourse;
import com.university.Course;
import com.dbUtil.DBConnection;

//includes all the methods that evolves courses table in db
public class CoursesDBFunction {
	private static final String ASSIGN_COURSE = "INSERT INTO courses_has_professors (courses_idcourses, professors_registnumber) VALUES (?,?)";
	private static final String INSERT_COURSE = "INSERT INTO courses (idcourses, title, semester, hours, type) VALUES (?,?,?,?,?)";
	private static final String SELECT_COURSE = "SELECT * FROM courses WHERE idcourses=?";
	private static final String DELETE_COURSE = "DELETE FROM courses WHERE idcourses=?";
	private static final String UPDATE_COURSE = "UPDATE courses SET title=?, semester=?, hours=?, type=? WHERE idcourses=?";
	private static final String DELETE_ASSIGNMENT = "DELETE FROM courses_has_professors WHERE courses_idcourses=?";
	
	public CoursesDBFunction() {
	}
	
	public void assignCourse(int IdCourses, int RegistNumber) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(ASSIGN_COURSE);) {
			preparedStatement.setInt(1, IdCourses);
			preparedStatement.setInt(2, RegistNumber);
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	public void addCourse(Course course) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE);) {
			preparedStatement.setInt(1, course.getIdcourses());
			preparedStatement.setString(2, course.getTitle());
			preparedStatement.setInt(3, course.getSemester());
			preparedStatement.setInt(4, course.getHours());
			preparedStatement.setString(5, course.getType());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	public void deleteCourseAssign(int IdCourses) {
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ASSIGNMENT);) {
            preparedStatement.setInt(1, IdCourses);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	printSQLException(e);
        }
    }
	
	public void deleteCourse(int IdCourses) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE);) {
			preparedStatement.setInt(1, IdCourses);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	public void updateCourse(Course course) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE);) {
			preparedStatement.setString(1, course.getTitle());
			preparedStatement.setInt(2, course.getSemester());
			preparedStatement.setInt(3, course.getHours());
			preparedStatement.setString(4, course.getType());
			preparedStatement.setInt(5, course.getIdcourses());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	public Course getCourseByIdCourse(int IdCourse) {
		Course course = new Course();
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE);) {
			preparedStatement.setInt(1, IdCourse);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				course.setIdcourses(rs.getInt("idcourses"));
				course.setTitle(rs.getString("title"));
				course.setSemester(rs.getInt("semester"));
				course.setHours(rs.getInt("hours"));
				course.setType(rs.getString("type"));
			}
			
		} catch (SQLException e) {
			printSQLException(e);
        }
		
		return course;
	}
	
	public List<AssignCourse> getAllAssignedCourses() {
		List<AssignCourse> AssignCourses = new ArrayList<AssignCourse>();
		try(Connection connection = DBConnection.connectToDB();		
				Statement statement = connection.createStatement()) {
			System.out.println(statement);
			ResultSet rs = statement.executeQuery("SELECT professors.`name` AS name, professors.surname AS surname, courses_has_professors.professors_registnumber AS registnumber, courses_has_professors.courses_idcourses AS idcourses, courses.title AS title, courses.semester AS semester FROM professors INNER JOIN courses_has_professors ON courses_has_professors.professors_registnumber = professors.registnumber INNER JOIN courses ON courses_has_professors.courses_idcourses = courses.idcourses;");
			while (rs.next()) {
				AssignCourse assigncourse = new AssignCourse();
				//System.out.println("This is the name: " + rs.getString("name"));
				assigncourse.professor.setName(rs.getString("name"));
				assigncourse.professor.setSurname(rs.getString("surname"));
				assigncourse.professor.setRegistrationNumber(rs.getInt("registnumber"));
				assigncourse.course.setIdcourses(rs.getInt("idcourses"));
				assigncourse.course.setTitle(rs.getString("title"));
				assigncourse.course.setSemester(rs.getInt("semester"));
				AssignCourses.add(assigncourse);
			}
		} catch (SQLException e) {
			printSQLException(e);
        }
		return AssignCourses;
	}
	
	public List<Course> getAllCourses() {
		List<Course> Courses = new ArrayList<Course>();
		try(Connection connection = DBConnection.connectToDB();		
				Statement statement = connection.createStatement()) {
			System.out.println(statement);
			ResultSet rs = statement.executeQuery("SELECT * FROM courses");
			while (rs.next()) {
				Course course = new Course();
				course.setIdcourses(rs.getInt("idcourses"));
				course.setTitle(rs.getString("title"));
				course.setSemester(rs.getInt("semester"));
				course.setHours(rs.getInt("hours"));
				course.setType(rs.getString("type"));
				Courses.add(course);
				System.out.println("IDcourse: " + course.getIdcourses() + " Title: " + course.getTitle() + " Semester:" + course.getHours() + "Hours: " + course.getHours() + "Type: " + course.getType());
			}
		} catch (SQLException e) {
			printSQLException(e);
        }
		return Courses;
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
