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
import com.university.Grade;
import com.dbUtil.DBConnection;

//includes all the methods that evolves courses table in db
public class CourseDBFunction {
	private static final String INSERT_COURSE = "INSERT INTO courses (idcourses, title, semester, hours, type) VALUES (?,?,?,?,?);";
	private static final String INSERT_COURSE_HAS_STUDENT = "INSERT INTO courses_has_students (courses_idcourses, students_registnumber) VALUES (?, ?);";
	private static final String INSERT_COURSE_TO_PROFESSOR = "INSERT INTO courses_has_professors (courses_idcourses, professors_registnumber) VALUES (?,?);";	

	private static final String SELECT_COURSES = "SELECT * FROM courses ORDER BY idcourses ASC;";
	private static final String SELECT_COURSE = "SELECT * FROM courses WHERE idcourses=?;";
	private static final String SELECT_GRADE_BY_STUDENT = "SELECT courses.title AS title, courses_has_students.courses_idcourses AS courses_idcourses, courses_has_students.students_registnumber AS students_registnumber, courses_has_students.exergrade AS exergrade, courses_has_students.examgrade AS examgrade, courses_has_students.finalgrade AS finalgrade	FROM courses INNER JOIN courses_has_students ON courses.idcourses = courses_has_students.courses_idcourses WHERE idcourses = ? AND students_registnumber = ?;";
	private static final String SELECT_GRADED_STUDENTS = "SELECT courses.title AS title, courses.idcourses AS idcourses, courses_has_students.students_registnumber AS registrationNumber, courses_has_students.exergrade AS exergrade, courses_has_students.examgrade AS examgrade, courses_has_students.finalgrade AS finalgrade FROM courses INNER JOIN courses_has_students ON courses.idcourses = courses_has_students.courses_idcourses WHERE courses.idcourses = ? AND courses_has_students.finalgrade IS NOT NULL AND courses_has_students.finalgrade <> 0;";
	private static final String SELECT_UNGRADED_STUDENTS = "SELECT courses.title AS title, courses.idcourses AS idcourses, courses_has_students.students_registnumber AS registrationNumber, courses_has_students.exergrade AS exergrade, courses_has_students.examgrade AS examgrade, courses_has_students.finalgrade AS finalgrade FROM courses INNER JOIN courses_has_students ON courses.idcourses = courses_has_students.courses_idcourses WHERE courses.idcourses = ? AND (courses_has_students.finalgrade IS NULL OR courses_has_students.finalgrade = 0);";
	private static final String SELECT_COURSES_BY_PROFESSOR = "SELECT idcourses, title, semester, hours, type FROM courses INNER JOIN courses_has_professors ON courses_idcourses = idcourses WHERE professors_registnumber=?;";
	private static final String SELECT_COURSES_BY_STUDENT = "SELECT * FROM courses WHERE idcourses IN (SELECT courses_idcourses FROM courses_has_students WHERE students_registnumber = ?) ORDER BY semester ASC;";
	private static final String SELECT_NOT_ASSIGNED_COURSE_BY_STUDENT = "SELECT idcourses, title FROM courses WHERE idcourses NOT IN (SELECT courses_idcourses FROM courses_has_students WHERE students_registnumber = ?) AND semester <= (SELECT semester FROM students WHERE registnumber = ?);";
	private static final String SELECT_ALL_COURSES_WITH_PROFESSORS = "SELECT professors.`name` AS name, professors.surname AS surname, courses_has_professors.professors_registnumber AS registnumber, courses_has_professors.courses_idcourses AS idcourses, courses.title AS title, courses.semester AS semester FROM professors INNER JOIN courses_has_professors ON courses_has_professors.professors_registnumber = professors.registnumber INNER JOIN courses ON courses_has_professors.courses_idcourses = courses.idcourses;";

	private static final String DELETE_COURSE = "DELETE FROM courses WHERE idcourses=?;";
	private static final String DELETE_ASSIGNMENT = "DELETE FROM courses_has_professors WHERE courses_idcourses=?;";
	private static final String DELETE_ASSIGNED_COURSE = "DELETE FROM courses_has_students WHERE courses_idcourses=?;";

	private static final String UPDATE_COURSE = "UPDATE courses SET title=?, semester=?, hours=?, type=? WHERE idcourses=?;";
	private static final String UPDATE_GRADES_BY_COURSE_BY_STUDENT = "UPDATE courses_has_students SET exergrade=?, examgrade=?, finalgrade=? WHERE courses_idcourses=? AND students_registnumber=?;";

	public CourseDBFunction() {
	}
	
	public void assignCourse(int idCourse, int registNumber) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE_TO_PROFESSOR);) {
			preparedStatement.setInt(1, idCourse);
			preparedStatement.setInt(2, registNumber);
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	public void assignCourseToStudent(int idCourse, int registNumber) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE_HAS_STUDENT);) {
			preparedStatement.setInt(1, idCourse);
			preparedStatement.setInt(2, registNumber);
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	public void addCourse(Course course) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE);) {
			preparedStatement.setInt(1, course.getIdcourse());
			preparedStatement.setString(2, course.getTitle());
			preparedStatement.setInt(3, course.getSemester());
			preparedStatement.setInt(4, course.getHours());
			preparedStatement.setString(5, course.getType());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	public void deleteCourseAssign(int IdCourse) {
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ASSIGNMENT);) {
            preparedStatement.setInt(1, IdCourse);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	printSQLException(e);
        }
    }
	
	public void deleteCourse(int IdCourse) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE);) {
			preparedStatement.setInt(1, IdCourse);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			printSQLException(e);
        }
	}
	
	public void deleteCourseStudentAssigned(int IdCourse) {
        try(Connection connection = DBConnection.connectToDB();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ASSIGNED_COURSE);) {
            preparedStatement.setInt(1, IdCourse);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
			preparedStatement.setInt(5, course.getIdcourse());
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
				course.setIdcourse(rs.getInt("idcourses"));
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
			ResultSet rs = statement.executeQuery(SELECT_ALL_COURSES_WITH_PROFESSORS);
			while (rs.next()) {
				AssignCourse assigncourse = new AssignCourse();
				assigncourse.professor.setName(rs.getString("name"));
				assigncourse.professor.setSurname(rs.getString("surname"));
				assigncourse.professor.setRegistrationNumber(rs.getInt("registnumber"));
				assigncourse.course.setIdcourse(rs.getInt("idcourses"));
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
			ResultSet rs = statement.executeQuery(SELECT_COURSES);
			while (rs.next()) {
				Course course = new Course();
				course.setIdcourse(rs.getInt("idcourses"));
				course.setTitle(rs.getString("title"));
				course.setSemester(rs.getInt("semester"));
				course.setHours(rs.getInt("hours"));
				course.setType(rs.getString("type"));
				Courses.add(course);
			}
		} catch (SQLException e) {
			printSQLException(e);
        }
		return Courses;
	}
	
	public List<Course> getAllCoursesByProfessor(int registNumber) {
		List<Course> Courses = new ArrayList<Course>();
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_PROFESSOR);) {
			preparedStatement.setInt(1, registNumber);
            ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setIdcourse(rs.getInt("idcourses"));
				course.setTitle(rs.getString("title"));
				course.setSemester(rs.getInt("semester"));
				course.setHours(rs.getInt("hours"));
				course.setType(rs.getString("type"));
				Courses.add(course);
			}
		} catch (SQLException e) {
			printSQLException(e);
        }
		return Courses;
	}
	
	public List<Course> getAllCoursesByStudent(int registNumber) {
		List<Course> Courses = new ArrayList<Course>();
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSES_BY_STUDENT);) {
			preparedStatement.setInt(1, registNumber);
            ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setIdcourse(rs.getInt("idcourses"));
				course.setTitle(rs.getString("title"));
				course.setSemester(rs.getInt("semester"));
				course.setHours(rs.getInt("hours"));
				course.setType(rs.getString("type"));
				Courses.add(course);
			}
		} catch (SQLException e) {
			printSQLException(e);
        }
		return Courses;
	}
	
	public List<Course> getAllUnsignedCoursesByStudent(int registNumber) {
		List<Course> Courses = new ArrayList<Course>();
		try(Connection connection = DBConnection.connectToDB();		
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOT_ASSIGNED_COURSE_BY_STUDENT);) {
			preparedStatement.setInt(1, registNumber);
			preparedStatement.setInt(2, registNumber);
            ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setIdcourse(rs.getInt("idcourses"));
				course.setTitle(rs.getString("title"));
				Courses.add(course);
			}
		} catch (SQLException e) {
			printSQLException(e);
        }
		return Courses;
	}
	
	
	public List<Grade> getUnsignedGradesByCourse(int idCourse){
		List<Grade> Grades = new ArrayList<Grade>();
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_UNGRADED_STUDENTS);) {
			preparedStatement.setInt(1, idCourse);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Grade grade = new Grade();
				grade.student.setRegistrationNumber(rs.getInt("registrationNumber"));
				grade.course.setTitle(rs.getString("title"));
				grade.course.setIdcourse(rs.getInt("idcourses"));
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
	
	public List<Grade> getAssignedGradesByCourse(int idCourse){
		List<Grade> Grades = new ArrayList<Grade>();
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRADED_STUDENTS);) {
			preparedStatement.setInt(1, idCourse);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Grade grade = new Grade();
				grade.student.setRegistrationNumber(rs.getInt("registrationNumber"));
				grade.course.setTitle(rs.getString("title"));
				grade.course.setIdcourse(rs.getInt("idcourses"));
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
	
	
	public Grade getGradesByCourseAndStudent(int idCourse, int registNumber){
		Grade grade = new Grade();
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRADE_BY_STUDENT);) {
			preparedStatement.setInt(1, idCourse);
			preparedStatement.setInt(2, registNumber);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				grade.student.setRegistrationNumber(rs.getInt("students_registNumber"));
				grade.course.setTitle(rs.getString("title"));
				grade.course.setIdcourse(rs.getInt("courses_idcourses"));
				grade.course.setExerGrade(rs.getInt("exergrade"));
				grade.course.setExamGrade(rs.getInt("examgrade"));
				grade.course.setFinalGrade(rs.getInt("finalgrade"));
			}
		}catch (SQLException e) {
            printSQLException(e);
        }
		return grade;
	}
	
	public void updateGradesByCourseAndStudent(Grade grade) {
		try(Connection connection = DBConnection.connectToDB();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GRADES_BY_COURSE_BY_STUDENT);) {
			preparedStatement.setInt(1, grade.course.getExerGrade());
			preparedStatement.setInt(2, grade.course.getExamGrade());
			preparedStatement.setInt(3, grade.course.getFinalGrade());
			preparedStatement.setInt(4, grade.course.getIdcourse());
			preparedStatement.setInt(5, grade.student.getRegistrationNumber());
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
            printSQLException(e);
        }
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
