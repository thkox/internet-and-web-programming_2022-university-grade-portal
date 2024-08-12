//Aimilianos Kourpas Danas P20100
//Apostolis Siampanis P20173
//Aggeliki Kaldiri P20069
//Theodoros Koxanoglou P20094

package gradepackage;

public class Course extends Department{
    private String classes;
    private String title;
    private int hours;
    private String type;
    private String description;
    private int finalGrade;
    private int semester;

    public String getClasses() { return classes; }
    public void setClasses(String classes) { this.classes = classes; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getHours() { return hours; }
    public void setHours(int hours) { this.hours = hours; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getFinalGrade() { return finalGrade; }
    public void setFinalGrade(int finalGrade) {	this.finalGrade = finalGrade; }

    public int getSemester() { return semester;}
    public void setSemester(int semester) { this.semester = semester;}

    public void evalGrade(int finalGrade, String studentRegNum)
    {
        System.out.println("You entered the grade: " + finalGrade);
        setFinalGrade(finalGrade);
        System.out.println("The final grade for the student: "+ studentRegNum + " is saved.");
    }
}
