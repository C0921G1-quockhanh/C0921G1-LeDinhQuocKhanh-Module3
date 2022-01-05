package bean;

public class Service {
    protected int serviceID;
    protected String serviceName;
    protected int serviceArea;
    protected double rentalCost;
    protected int maxPeople;
    protected RentalType rentalType;
    protected ServiceType serviceType;
    protected String roomStandard;
    protected String extraAmenity;
    protected double poolArea;
    protected int levels;

    public Service() {
    }

    public Service(int serviceID) {
        this.serviceID = serviceID;
    }

    public Service(String serviceName, int serviceArea, double rentalCost, int maxPeople, RentalType rentalType, ServiceType serviceType, String roomStandard, String extraAmenity, double poolArea, int levels) {
        this.serviceName = serviceName;
        this.serviceArea = serviceArea;
        this.rentalCost = rentalCost;
        this.maxPeople = maxPeople;
        this.rentalType = rentalType;
        this.serviceType = serviceType;
        this.roomStandard = roomStandard;
        this.extraAmenity = extraAmenity;
        this.poolArea = poolArea;
        this.levels = levels;
    }

    public Service(int serviceID, String serviceName, int serviceArea, double rentalCost, int maxPeople, RentalType rentalType, ServiceType serviceType, String roomStandard, String extraAmenity, double poolArea, int levels) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceArea = serviceArea;
        this.rentalCost = rentalCost;
        this.maxPeople = maxPeople;
        this.rentalType = rentalType;
        this.serviceType = serviceType;
        this.roomStandard = roomStandard;
        this.extraAmenity = extraAmenity;
        this.poolArea = poolArea;
        this.levels = levels;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(int serviceArea) {
        this.serviceArea = serviceArea;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public String getExtraAmenity() {
        return extraAmenity;
    }

    public void setExtraAmenity(String extraAmenity) {
        this.extraAmenity = extraAmenity;
    }

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }
}
