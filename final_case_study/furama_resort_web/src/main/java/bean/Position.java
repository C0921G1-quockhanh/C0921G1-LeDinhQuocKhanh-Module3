package bean;

public class Position {
    protected int positionID;
    protected String positionName;

    public Position() {
    }

    public Position(int positionID) {
        this.positionID = positionID;
    }

    public Position(String positionName) {
        this.positionName = positionName;
    }

    public Position(int positionID, String positionName) {
        this.positionID = positionID;
        this.positionName = positionName;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
