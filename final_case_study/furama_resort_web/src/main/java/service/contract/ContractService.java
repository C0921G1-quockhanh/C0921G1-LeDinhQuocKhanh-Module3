package service.contract;

import bean.AccompaniedService;
import bean.Contract;
import bean.DetailContract;
import repository.contract.ContractRepository;
import repository.contract.IContractRepository;

import java.sql.SQLException;
import java.util.List;

public class ContractService implements IContractService {

    private IContractRepository iContractRepository = new ContractRepository();

    @Override
    public void insertContract(Contract contract) throws SQLException {
        this.iContractRepository.insertContract(contract);
    }

    @Override
    public List<Contract> selectAllContracts() {
        return this.iContractRepository.selectAllContracts();
    }

    @Override
    public List<AccompaniedService> selectAllAccompaniedServices() {
        return this.iContractRepository.selectAllAccompaniedServices();
    }

    @Override
    public void insertDetailContract(DetailContract detailContract) throws SQLException {
        this.iContractRepository.insertDetailContract(detailContract);
    }
}
