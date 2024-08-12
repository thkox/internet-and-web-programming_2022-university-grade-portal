//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Secretary extends User{
    int afmNumber;

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
    Secretary()
    {
        this.setDepartment("Department");
        this.setName("Name");
        this.setSurname("Surname");
        this.setUsername("Username");
        this.setEmail("default@gmail.com");
        this.setPassword("Password");
        this.setBirthDate("2000-12-12");
        this.setAcadYearAdmission(1999);
    }

    @Override
    public String toString(int returnType) {
        return null;
    }

    public void menu()
    {
        Scanner input = new Scanner(System.in);
        int userChoice;

        System.out.println("1. Create a Student");
        System.out.println("2. Read all Students");
        System.out.println("3. Create a Professor");
        System.out.println("4. Read all professors");
        System.out.println("5. Create a Course");
        System.out.println("6. Exit the app");
        System.out.println("\nPlease select an option: (we accept numbers only)");

        //add try catch for random error
        userChoice = input.nextInt();

        switch (userChoice) {
            case 1:
                System.out.println("You are going to create a student profile");
                makeAUser(1);
                break;
            case 2:
                System.out.println("You are going to read all students profile");
                readAllUsers(1);
                break;
            case 3:
                System.out.println("You are going to create a professor profile");
                makeAUser(2);
                break;
            case 4:
                System.out.println("You are going to create a student profile");
                readAllUsers(2);
                break;
            case 5:
                System.out.println("You are going to create a new course");
                createCourse();
                break;
            case 6:
                System.out.println("You are going to exit the app now... See you later!");
                break;
        }
        input.close();
    }


    public void makeAUser(int choice)
    {
        CreateUser makeAUser = new CreateUser(choice); //this only work for a student.
        makeAUser.createUser();
    }

    public void readAllUsers(int choice)
    {
        CreateUser createUser = new CreateUser(choice);
        ArrayList<User> usersFromFile;
        String fileName = null;
        //based on the user choice we read the respective file
        if(choice == 1)
        {
            fileName = "students.txt";
        }
        else if(choice == 2)
        {
            fileName = "professors.txt";
        }

        try
        {
            usersFromFile = createUser.readUsersFromFile(fileName);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        //prints the students however we like
        for(int i = 0; i < usersFromFile.size(); i++)
        {
            System.out.println(usersFromFile.get(i).toString(1) + "\n");
        }
    }
    public void createCourse()
    {
        CreateCourse course = new CreateCourse();
        course.courseCreation();
    }
    public void courseAssign()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What Course do you want to assign?\n");
        String course = scanner.nextLine();
        System.out.println("Which teacher do you want to assign to?");
        String professor = scanner.nextLine();

        System.out.println("The " + course + " has been successfully assigned to the " + professor + ".");
        scanner.close();
    }
    public void showStudentsToGrade()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("In what course do you want to assign grades?");
        String course = scanner.nextLine();

        System.out.println("These are the students: \n\n");
        System.out.println("Please complete the grades...");
        System.out.println("Saved!!!");
        
        scanner.close();
    }
}
