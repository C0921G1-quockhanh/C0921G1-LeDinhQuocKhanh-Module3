package repository.contract;

import bean.*;
import repository.BaseRepository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractRepository implements IContractRepository{

    private static final String INSERT_CONTRACT_SQL = "insert into contract(start_date,end_date,deposit,employee_id,customer_id,service_id)\n" +
                                                    "values(?,?,?,?,?,?)";

    private static final String SELECT_ALL_CONTRACTS = "select contract_id,start_date,end_date,deposit,employee_id,customer_id,service_id\n" +
                                                    "from contract";

    private static final String SELECT_ALL_ACCOMPANIED_SERVICES = "select accompanied_service_id,accompanied_service_name,price,unit,`status`\n" +
                                                    "from accompanied_service";

    private static final String INSERT_DETAILED_CONTRACT_SQL = "insert into detail_contract(contract_id,accompanied_service_id,quantity)\n" +
                                                    "values(?,?,?)";

    @Override
    public void insertContract(Contract contract) throws SQLException {
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(INSERT_CONTRACT_SQL);

            preparedStatement.setDate(1,contract.getStartDate());
            preparedStatement.setDate(2,contract.getEndDate());
            preparedStatement.setDouble(3,contract.getDeposit());
            preparedStatement.setInt(4,contract.getEmployee().getEmployeeID());
            preparedStatement.setInt(5,contract.getCustomer().getCustomerID());
            preparedStatement.setInt(6,contract.getService().getServiceID());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void insertDetailContract(DetailContract detailContract) throws SQLException {
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(INSERT_DETAILED_CONTRACT_SQL);

            preparedStatement.setInt(1,detailContract.getContract().getContractID());
            preparedStatement.setInt(2,detailContract.getAccompaniedService().getAccompaniedServiceID());
            preparedStatement.setInt(3,detailContract.getQuantity());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public List<Contract> selectAllContracts() {
        List<Contract> contracts = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SELECT_ALL_CONTRACTS);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int contractID = resultSet.getInt("contract_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                double deposit = resultSet.getDouble("deposit");

                int employeeID = resultSet.getInt("employee_id");
                Employee employee = new Employee(employeeID);

                int customerID = resultSet.getInt("customer_id");
                Customer customer = new Customer(customerID);

                int serviceID = resultSet.getInt("service_id");
                Service service = new Service(serviceID);

                contracts.add(new Contract(contractID,startDate,endDate,deposit,employee,customer,service));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return contracts;
    }

    @Override
    public List<AccompaniedService> selectAllAccompaniedServices() {
        List<AccompaniedService> accompaniedServices = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SELECT_ALL_ACCOMPANIED_SERVICES);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int accompaniedServiceID = resultSet.getInt("accompanied_service_id");
                String accompaniedServiceName = resultSet.getString("accompanied_service_name");
                double price = resultSet.getDouble("price");
                String unit = resultSet.getString("unit");
                String status = resultSet.getString("status");

                accompaniedServices.add(new AccompaniedService(accompaniedServiceID,accompaniedServiceName,price,unit,status));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return accompaniedServices;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
