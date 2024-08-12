//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
    	    	
        Scanner input = new Scanner(System.in);
        int userChoice;

        System.out.println("Welcome to the grade system of our University.");
        System.out.println("1. Sign in as a student");
        System.out.println("2. Sign in as a professor");
        System.out.println("3. Sign in as a secretary");
        System.out.println("4. Exit");
        System.out.println("\nPlease select an option: (we accept numbers only)");
        userChoice = input.nextInt();

        switch (userChoice) {
            case 1:
                System.out.println("\nYou logged in as a student \n");
                Student student = new Student();
                student.menu();
                break;
            case 2:
                System.out.println("\nYou logged in as a professor \n");
                Professor professor = new Professor();
                professor.menu();
                break;
            case 3:
                System.out.println("\nYou logged in as a secretary \n");
                Secretary secretary = new Secretary();
                secretary.menu();
                break;
            case 4:
                System.out.println("You will exit the app. See you soon!");
                break;
        }
        input.close();

    }
}
