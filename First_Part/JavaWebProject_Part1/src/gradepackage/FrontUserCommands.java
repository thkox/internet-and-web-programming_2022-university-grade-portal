//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;
import java.util.ArrayList;

public interface FrontUserCommands
{
    //this will be both on student and professor class.
    static ArrayList<Grade> viewGradesPerStudentPerCourse(Course course, Student student)
    {
        ArrayList<Grade> gradesList = new ArrayList<Grade>();
        //find the student --> make sure student exists
        System.out.println("The student exist in the database!");
        //return the request with the proper
        System.out.println("The request from the database is accepted");
        //save the result of the request to an arraylist and return it
        System.out.println("This is your results:\n" + "Course1: Grade1\n");
        return gradesList;
    }
    //show the courses that the professor has enrolled to
    static  ArrayList<Course> ViewCourses(Professor professor)
    {
        ArrayList<Course> courseList = new ArrayList<Course>();
        return courseList;
    }

    //show which courses the students has up until this point (based on the semester that he is in)
    static  ArrayList<Course> ViewCourses(Student student)
    {
        ArrayList<Course> courseList = new ArrayList<Course>();
        return courseList;
    }

    //this is for the professor
    static ArrayList<Course> setGradesToCourses()
    {
        ArrayList<Course> courseList = new ArrayList<Course>();
        return courseList;
    }
    //only for the professors
    static ArrayList<Student> viewGradesOfAllStudentsOnTheirCourses()
    {
        ArrayList<Student> student = new ArrayList<Student>();
        return student;
    }
}
