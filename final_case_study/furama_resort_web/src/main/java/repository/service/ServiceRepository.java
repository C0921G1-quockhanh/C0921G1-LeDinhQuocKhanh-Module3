package repository.service;

import bean.Service;
import repository.BaseRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceRepository implements IServiceRepository {

    private static final String INSERT_SERVICE_SQL = "insert into service(service_name,service_area,rental_cost,max_people,rental_type_id,service_type_id,room_standard,extra_amenity,pool_area,levels)\n" +
                                                    "values(?,?,?,?,?,?,?,?,?,?)";

    @Override
    public void insertService(Service service) throws SQLException {
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(INSERT_SERVICE_SQL);

            preparedStatement.setString(1,service.getServiceName());
            preparedStatement.setInt(2,service.getServiceArea());
            preparedStatement.setDouble(3,service.getRentalCost());
            preparedStatement.setInt(4,service.getMaxPeople());
            preparedStatement.setInt(5,service.getRentalType().getRentalTypeID());
            preparedStatement.setInt(6,service.getServiceType().getServiceTypeID());
            preparedStatement.setString(7,service.getRoomStandard());
            preparedStatement.setString(8,service.getExtraAmenity());
            preparedStatement.setDouble(9,service.getPoolArea());
            preparedStatement.setInt(10,service.getLevels());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            printSQLException(e);
        }
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
