//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;
import java.util.Scanner;

public class CreateCourse {

    Course course = new Course();

    public void courseCreation()
    {
        Scanner newCourse = new Scanner(System.in);

        System.out.println("Course class: ");
        String classes = newCourse.nextLine();

        System.out.println("Course Title: ");
        String title = newCourse.nextLine();

        System.out.println("Course hours: ");
        int hours = newCourse.nextInt();

        System.out.println("Course Type: ");
        String type = newCourse.nextLine();

        System.out.println("Course Description: ");
        String description = newCourse.nextLine();

        System.out.println("Course Final Grade: ");
        int finalGrade = newCourse.nextInt();

        System.out.println("Course Sememster: ");
        int semester = newCourse.nextInt();

        courseAttributes(classes,title,hours,type,description,finalGrade,semester);
        System.out.println("\nCourse is Saved! \n ");
        
        newCourse.close();
    }

    private void courseAttributes(String classes, String title, int hours, String type, String description, int finalGrade, int semester)
    {
        course.setClasses(classes);
        course.setTitle(title);
        course.setHours(hours);
        course.setType(type);
        course.setDescription(description);
        course.setFinalGrade(finalGrade);
        course.setSemester(semester);
    }
}
