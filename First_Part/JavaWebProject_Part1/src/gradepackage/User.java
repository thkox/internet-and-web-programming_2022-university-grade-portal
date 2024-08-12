//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public abstract class User extends Department{
    private boolean matches;
    private String regExPattern;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String birthDate;
    private int acadYearAdmission;
    private final int registrationNumber = 0;

    public int getRegistrationNumber() { return registrationNumber; }
    public String getName() { return name; }
    public void setName(String name) {
        matches = false;
        regExPattern = "[a-zA-Z]+";
        try {
            matches = Pattern.matches(regExPattern, name);
            if (matches)
                this.name = name;
            else
                throw new InputMismatchException("Invalid name! Alphabetic characters only!");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getSurname() { return surname; }
    public void setSurname(String surname) {
        matches = false;
        regExPattern = "[a-zA-Z]+";
        try {
            matches = Pattern.matches(regExPattern, surname);
            if (matches)
                this.surname = surname;
            else
                throw new InputMismatchException("Invalid surname! Alphabetic characters only!");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getUsername() { return username; }
    public void setUsername(String username) {
        matches = false;
        regExPattern = "\\w+";
        try {
            matches = Pattern.matches(regExPattern, username);
            if (matches)
                this.username = username;
            else
                throw new InputMismatchException("Invalid username! Alphanumeric characters only!");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        matches = false;
        regExPattern = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        try {
            matches = Pattern.matches(regExPattern, email);
            if (matches)
                this.email = email;
            else
                throw new InputMismatchException("Invalid email! Email format only!");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        matches = false;
        regExPattern = ".{8,}";
        try {
            matches = Pattern.matches(regExPattern, password);
            if (matches)
                this.password = password;
            else
                throw new InputMismatchException("Invalid password! Any character is accepted but you need at least 8 characters!");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) {
        matches = false;
        regExPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        try {
            matches = Pattern.matches(regExPattern, birthDate);
            if (matches)
                this.birthDate = birthDate;
            else
                throw new InputMismatchException("Invalid birthDate! Accepted format is YYYY-MM-DD");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int getAcadYearAdmission() { return acadYearAdmission; }
    public void setAcadYearAdmission(int acadYearAdmission) {
        try {
            if (acadYearAdmission > 1990 && acadYearAdmission <= Calendar.getInstance().get(Calendar.YEAR))
                this.acadYearAdmission = acadYearAdmission;
            else
                throw new InputMismatchException("Invalid academic year admission! Accepted integer from 1990 to 2022");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    User() {}

    public void login() {}
    public void logout() {}
    public void register() {}
    public void update() {}
    public void viewProfile() {	}

    public abstract String toString(int returnType);
    public abstract void menu();
}
