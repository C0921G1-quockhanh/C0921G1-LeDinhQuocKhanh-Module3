package bean;

public class RentalType {
    protected int rentalTypeID;
    protected String rentalTypeName;

    public RentalType() {
    }

    public RentalType(int rentalTypeID) {
        this.rentalTypeID = rentalTypeID;
    }

    public RentalType(int rentalTypeID, String rentalTypeName) {
        this.rentalTypeID = rentalTypeID;
        this.rentalTypeName = rentalTypeName;
    }

    public int getRentalTypeID() {
        return rentalTypeID;
    }

    public void setRentalTypeID(int rentalTypeID) {
        this.rentalTypeID = rentalTypeID;
    }

    public String getRentalTypeName() {
        return rentalTypeName;
    }

    public void setRentalTypeName(String rentalTypeName) {
        this.rentalTypeName = rentalTypeName;
    }
}
