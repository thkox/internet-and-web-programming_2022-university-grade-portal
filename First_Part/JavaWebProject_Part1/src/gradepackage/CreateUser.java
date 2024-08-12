//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class CreateUser {
    private long usersCounter = 0;
    private static int userTypeCreation = 0;

    CreateUser(int userChoice)
    {
        //change the value based on how many users exists in the database
        //count the users that already exists in the "database" file.
        Path path1 = Paths.get("students.txt");
        Path path2 = Paths.get("professors.txt");
        try {
            usersCounter = usersCounter + (Files.lines(path1).count() + Files.lines(path2).count());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        usersCounter++;
        //System.out.println("You will created the user: " + usersCounter + ".");

        //this if-else statement defines the user that the secretary in going to create
        if(userChoice == 1)
        {
            userTypeCreation = userChoice;
        }
        else if(userChoice == 2)
        {
            userTypeCreation = userChoice;
        }
    }

    public void createUser() {
        long registerNumber = usersCounter;

        boolean matches = false;
        String regExPattern = "[a-zA-Z]+";
        Scanner newUser = new Scanner(System.in);

        System.out.println("Department: ");
        String department = null;
        while (!matches)
        {
            department = newUser.nextLine();
            matches = Pattern.matches(regExPattern, department);
            if (!matches)
            {
                System.out.println("Invalid department! Please try again...");
            }
        }

        System.out.println("Name: ");
        matches = false;
        String name = null;
        while (!matches)
        {
            name = newUser.nextLine();
            matches = Pattern.matches(regExPattern, name);
            if (!matches)
                System.out.println("Invalid name! Please try again...");
        }

        System.out.println("Surname: ");
        matches = false;
        String surname = null;
        while (!matches)
        {
            surname = newUser.nextLine();
            matches = Pattern.matches(regExPattern, surname);
            if (!matches)
                System.out.println("Invalid surname! Please try again...");
        }

        System.out.println("Username: ");
        matches = false;
        regExPattern = "\\w+";
        String username = null;
        while (!matches)
        {
            username = newUser.nextLine();
            matches = Pattern.matches(regExPattern, username);
            if (!matches)
                System.out.println("Invalid username! Please try again...");
        }

        System.out.println("Email: ");
        matches = false;
        regExPattern = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        String email = null;
        while (!matches)
        {
            email = newUser.nextLine();
            matches = Pattern.matches(regExPattern, email);
            if (!matches)
                System.out.println("Invalid email! Please try again...");
        }

        System.out.println("Password: ");
        matches = false;
        regExPattern = ".{8,}";
        String password = null;
        while (!matches)
        {
            password = newUser.nextLine();
            matches = Pattern.matches(regExPattern, password);
            if (!matches)
                System.out.println("Invalid password! Please try again...");
        }

        System.out.println("Birth Date: ");
        matches = false;
        regExPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        String birthdate = null;
        while (!matches)
        {
            birthdate = newUser.nextLine();
            matches = Pattern.matches(regExPattern, birthdate);
            if (!matches)
                 System.out.println("Invalid birthdate! Please try again...\nValid input YYYY-MM-DD");
        }

        System.out.println("Year of Admission: ");
        matches = false;
        int acadYearAdm = 0;
        while (!matches)
        {
            acadYearAdm = newUser.nextInt();
            matches = acadYearAdm > 1990 && acadYearAdm <= Calendar.getInstance().get(Calendar.YEAR);
            if (!matches)
                System.out.println("Invalid Academic Year Admission! Please try again...");
        }

        if(userTypeCreation == 1) //we are going to save a student
        {
            int finalGrade = 0;

            System.out.println("Semester: ");
            matches = false;
            int semester = 0;
            while (!matches)
            {
                semester = newUser.nextInt();
                matches = semester > 0 && semester <= 8;
                if (!matches)
                    System.out.println("Invalid semester! Please try again...");
            }

            Student student = new Student(department, name, surname, username, email, password, birthdate, acadYearAdm, finalGrade, semester);

            System.out.println(student.toString(1));
            saveFile(student.toString(2));
        }
        else
        {
            System.out.println("AFM: ");
            matches = false;
            int afm = 0;
            while (!matches)
            {
                afm = newUser.nextInt();
                matches = afm >= 100000000 && afm <= 999999999; //if-else statement
                if (!matches)
                      System.out.println("Invalid AFM! Please try again...");
            }

            System.out.println("Course:");
            matches = false;
            regExPattern = "[a-zA-Z]+";
            String courses = null;
            while (!matches)
            {
                courses = newUser.next();
                matches = Pattern.matches(regExPattern, courses);
                if (!matches)
                {
                    System.out.println("Invalid courses! Please try again...");
                }
            }
            Professor professor = new Professor(department, name, surname, username, email, password, birthdate, acadYearAdm, afm, courses);
            System.out.println(professor.toString(1));
            saveFile(professor.toString(2));
        }
        newUser.close();
    }

    public static void saveFile(String string)
    {
        String fileToSave = null;
        if(userTypeCreation == 1)
        {
            fileToSave = "students.txt";
        }
        else if(userTypeCreation == 2)
        {
            fileToSave = "professors.txt";
        }
        //create the file in txt format
        assert fileToSave != null;
        File saveUser = new File(fileToSave);
        try {
            if(saveUser.createNewFile())
            {
                System.out.println("File created!");
            }
            else
            {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating/accessing the file.");
            e.printStackTrace();
        }
        //write in file
        FileWriter secretaryWriter;
        try {
            secretaryWriter = new FileWriter(fileToSave, true);
            secretaryWriter.write(string +"\n");
            secretaryWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred saving the user.");
            e.printStackTrace();
        }
        System.out.println("User Saved Successfully!");
    }

    public static ArrayList<User> readUsersFromFile(String filename) throws FileNotFoundException {
        //open the file
        File file = new File(filename);
        //create a scanner for the opened file
        Scanner s = new Scanner(file);
        //create a list of students
        ArrayList<User> users = new ArrayList<>();

        while(s.hasNext())
        {
            //capture a line
            String line = s.nextLine();
            //save the items in a string list
            String[] fieldsUser = line.split("\\|");
            String department = fieldsUser[0];
            String name = fieldsUser[1];
            String surName = fieldsUser[2];
            String userName = fieldsUser[3];
            String email = fieldsUser[4];
            String password = fieldsUser[5];
            String birthdate = fieldsUser[6];
            int acaYearAdmission = Integer.parseInt(fieldsUser[7]);
            if(userTypeCreation == 1) {
                int finalGrade = Integer.parseInt(fieldsUser[8]);
                int semester = Integer.parseInt(fieldsUser[9]);
                User user = new Student(department, name, surName, userName, email, password, birthdate, acaYearAdmission, finalGrade, semester);
                users.add(user);
            }
            else if(userTypeCreation == 2)
            {
                int afm = Integer.parseInt(fieldsUser[8]);
                String coursesStr = fieldsUser[9];
                User user = new Professor(department, name, surName, userName, email, password, birthdate, acaYearAdmission, afm, coursesStr);
                users.add(user);
            }
        }
        s.close();
        return users;
    }
}