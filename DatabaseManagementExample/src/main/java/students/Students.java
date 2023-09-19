package students;

public class Students {
    private int student_id;
    private String student_name;
    private double score;

    public Students(int student_id, String student_name, double score) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.score = score;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
