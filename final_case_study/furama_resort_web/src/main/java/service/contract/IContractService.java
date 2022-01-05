package service.contract;

import bean.AccompaniedService;
import bean.Contract;
import bean.DetailContract;

import java.sql.SQLException;
import java.util.List;

public interface IContractService {
    public void insertContract(Contract contract) throws SQLException;

    public List<Contract> selectAllContracts();

    public List<AccompaniedService> selectAllAccompaniedServices();

    public void insertDetailContract(DetailContract detailContract) throws SQLException;
}
