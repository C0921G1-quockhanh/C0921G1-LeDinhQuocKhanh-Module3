package bean;

public class Qualification {
    protected int qualificationID;
    protected String qualificationName;

    public Qualification() {
    }

    public Qualification(int qualificationID) {
        this.qualificationID = qualificationID;
    }

    public Qualification(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public Qualification(int qualificationID, String qualificationName) {
        this.qualificationID = qualificationID;
        this.qualificationName = qualificationName;
    }

    public int getQualificationID() {
        return qualificationID;
    }

    public void setQualificationID(int qualificationID) {
        this.qualificationID = qualificationID;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }
}
