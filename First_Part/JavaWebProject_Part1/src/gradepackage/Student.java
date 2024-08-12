//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Student extends User implements FrontUserCommands{
    public int finalGrade;
    private int semester;

    public int getSemester() { return semester; }
    public void setSemester(int semester) {
        try {
            if (semester > 0 && semester <= 8)
                this.semester = semester;
            else
                throw new InputMismatchException("Invalid semester! Accepted integer from 1 to 8");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    Student()
    {
        this.setDepartment("Department");
        this.setName("Name");
        this.setSurname("Surname");
        this.setUsername("Username");
        this.setEmail("default@default.com");
        this.setPassword("asdasfdsafd");
        this.setBirthDate("2000-01-01");
        this.setAcadYearAdmission(2018);
        this.finalGrade = 0;
        this.setSemester(1);
    }

    Student(String department, String name, String surName, String userName, String email, String password, String birthdate, int acadYearAdmission, int finalGrade, int semester)
    {
        this.setDepartment(department);
        this.setName(name);
        this.setSurname(surName);
        this.setUsername(userName);
        this.setEmail(email);
        this.setPassword(password);
        this.setBirthDate(birthdate);
        this.setAcadYearAdmission(acadYearAdmission);
        this.finalGrade = finalGrade;
        this.setSemester(semester);
    }
    @Override
    public String toString(int returnType)
    {
        switch (returnType)
        {
            case 1:
                return "Student = [Department = " + this.getDepartment() + ", Name = " + this.getName() +
                        ", Surname = " + this.getSurname() + ", Username = " + this.getUsername() +
                        ", Email = " + this.getEmail() + ", Password = " + this.getPassword() +
                        ", BirthDate = " + this.getBirthDate() + ", AcadYearAdmission = " + this.getAcadYearAdmission() +
                        ", Final Grade = " + this.finalGrade + ", Semester = " + this.getSemester() + "]";
            case 2:
                return  this.getDepartment() + "|" + this.getName() +
                        "|" + this.getSurname() + "|" + this.getUsername() +
                        "|" + this.getEmail() + "|" + this.getPassword() +
                        "|" + this.getBirthDate() + "|" + this.getAcadYearAdmission() +
                        "|" + this.finalGrade + "|" + this.getSemester();
            default:
                throw new IllegalStateException("Unexpected value: " + returnType);
        }
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        int userChoice;

        System.out.println("1. Show All Grades");
        System.out.println("2. Exit the app");
        System.out.println("\nPlease select an option: (we accept numbers only)");

        //add try catch for random error
        userChoice = input.nextInt();

        switch (userChoice) {
            case 1:
                System.out.println("These are your Grades\n");
                showGrades();
                break;
            case 2:
                System.out.println("You are going to exit the app now... See you later!");
                break;
        }
        input.close();
    }

    public void showGrades()
    {
        System.out.println("Loading your Grades... \n");
        System.out.println("You have no grades");
    }

}
