package bean;

public class AccompaniedService {
    protected int accompaniedServiceID;
    protected String accompaniedServiceName;
    protected double price;
    protected String unit;
    protected String status;

    public AccompaniedService() {
    }

    public AccompaniedService(int accompaniedServiceID) {
        this.accompaniedServiceID = accompaniedServiceID;
    }

    public AccompaniedService(int accompaniedServiceID, String accompaniedServiceName, double price, String unit, String status) {
        this.accompaniedServiceID = accompaniedServiceID;
        this.accompaniedServiceName = accompaniedServiceName;
        this.price = price;
        this.unit = unit;
        this.status = status;
    }

    public int getAccompaniedServiceID() {
        return accompaniedServiceID;
    }

    public void setAccompaniedServiceID(int accompaniedServiceID) {
        this.accompaniedServiceID = accompaniedServiceID;
    }

    public String getAccompaniedServiceName() {
        return accompaniedServiceName;
    }

    public void setAccompaniedServiceName(String accompaniedServiceName) {
        this.accompaniedServiceName = accompaniedServiceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
