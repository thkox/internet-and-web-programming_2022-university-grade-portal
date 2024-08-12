//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Professor extends User implements FrontUserCommands{
    private boolean matches;
    private String regExPattern;
    int afmNumber;
    private String courses;

    public int getAfmNumber() { return afmNumber; }
    public void setAfmNumber(int afmNumber) {
        try {
            if (afmNumber >= 100000000 && afmNumber <= 999999999)
                this.afmNumber = afmNumber;
            else
                throw new InputMismatchException("Invalid afm! Accepted number from 100000000 to 999999999");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getCourses() { return courses; }
    public void setCourses(String courses) {
        matches = false;
        regExPattern = "[a-zA-Z]+";
        try {
            matches = Pattern.matches(regExPattern, courses);
            if (matches)
                this.courses = courses;
            else
                throw new InputMismatchException("Invalid name! Alphanumeric characters and comma only!");
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }

    Professor()
    {
        this.setDepartment("Department");
        this.setName("Name");
        this.setSurname("Surname");
        this.setUsername("Username");
        this.setEmail("default@default.com");
        this.setPassword("Password");
        this.setBirthDate("1980-01-02");
        this.setAcadYearAdmission(1999);
        this.setAfmNumber(111111111);
        this.setCourses("Course");
    }

    public String toString(int returnType)
    {
        switch (returnType)
        {
            case 1:
                return "Professor = [Department = " + this.getDepartment() + ", Name = " + this.getName() +
                        ", Surname = " + this.getSurname() + ", Username = " + this.getUsername() +
                        ", Email = " + this.getEmail() + ", Password = " + this.getPassword() +
                        ", BirthDate = " + this.getBirthDate() + ", AcadYearAdmission = " + this.getAcadYearAdmission() +
                        ", AFM = " + this.getAfmNumber() + ", Courses = " + this.getCourses() + "]";
            case 2:
                return  this.getDepartment() + "|" + this.getName() +
                        "|" + this.getSurname() + "|" + this.getUsername() +
                        "|" + this.getEmail() + "|" + this.getPassword() +
                        "|" + this.getBirthDate() + "|" + this.getAcadYearAdmission() +
                        "|" + this.getAfmNumber() + "|" + this.getCourses();
            default:
                throw new IllegalStateException("Unexpected value: " + returnType);
        }
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        int userChoice;

        System.out.println("1. Add Grades to your students based on the course");
        System.out.println("2. Show all Grades");
        System.out.println("3. Exit the app");
        System.out.println("\nPlease select an option: (we accept numbers only)");

        //add throw an error in the app
        userChoice = input.nextInt();

        switch (userChoice) {
            case 1:
                addGrades();
                break;
            case 2:
                viewAllGrades();
                break;
            case 3:
                System.out.println("You are going to exit the app now... See you later!");
                break;
        }
        input.close();
    }

    Professor(String department, String name, String surName, String userName, String email, String password, String birthdate, int acadYearAdmission, int afm, String courses)
    {
        this.setDepartment(department);
        this.setName(name);
        this.setSurname(surName);
        this.setUsername(userName);
        this.setEmail(email);
        this.setPassword(password);
        this.setBirthDate(birthdate);
        this.setAcadYearAdmission(acadYearAdmission);
        this.setAfmNumber(afm);
        this.setCourses(courses);
    }




    public void addGrades()
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter a course that you have: ");
        String course = input.nextLine();
        System.out.println("This is the list of all the students that need grading in the " + course +": ");
        System.out.println("Grades Saved!!!");
        input.close();
    }

    public void viewAllGrades()
    {
        System.out.println("This are all the students that have a grade on one or all of your courses: \n\n\n");
    }

}
