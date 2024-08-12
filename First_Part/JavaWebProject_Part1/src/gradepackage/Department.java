//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Department {
    private boolean matches;
    private String regExPattern;
    private String department;

    public String getDepartment() { return department; }
    public void setDepartment(String department) {
        matches = false;
        regExPattern = "[a-zA-Z]+";
        try {
            matches = Pattern.matches(regExPattern, department);
            if (matches)
                this.department = department;
            else
                throw new InputMismatchException("Invalid department! Alphabetic characters only!");
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
