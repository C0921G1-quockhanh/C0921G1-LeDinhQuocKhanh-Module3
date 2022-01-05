package bean;

public class DetailContract {
    protected int detailContractID;
    protected Contract contract;
    protected AccompaniedService accompaniedService;
    protected int quantity;

    public DetailContract() {
    }

    public DetailContract(int detailContractID) {
        this.detailContractID = detailContractID;
    }

    public DetailContract(Contract contract, AccompaniedService accompaniedService, int quantity) {
        this.contract = contract;
        this.accompaniedService = accompaniedService;
        this.quantity = quantity;
    }

    public DetailContract(int detailContractID, Contract contract, AccompaniedService accompaniedService, int quantity) {
        this.detailContractID = detailContractID;
        this.contract = contract;
        this.accompaniedService = accompaniedService;
        this.quantity = quantity;
    }

    public int getDetailContractID() {
        return detailContractID;
    }

    public void setDetailContractID(int detailContractID) {
        this.detailContractID = detailContractID;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public AccompaniedService getAccompaniedService() {
        return accompaniedService;
    }

    public void setAccompaniedService(AccompaniedService accompaniedService) {
        this.accompaniedService = accompaniedService;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

