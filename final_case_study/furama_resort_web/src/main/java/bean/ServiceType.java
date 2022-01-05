package bean;

public class ServiceType {
    protected int serviceTypeID;
    protected String serviceTypeName;

    public ServiceType() {
    }

    public ServiceType(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public ServiceType(int serviceTypeID, String serviceTypeName) {
        this.serviceTypeID = serviceTypeID;
        this.serviceTypeName = serviceTypeName;
    }

    public int getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }
}
