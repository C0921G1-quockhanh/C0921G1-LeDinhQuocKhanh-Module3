package repository;

import bean.User;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private static final String INSERT_USERS_SQL = "insert into users(name,email,country)\n" + "values(?,?,?)";
    private static final String SELECT_USER_BY_ID = "select * from users\n" + "where id = ?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users\n" + "where id = ?";
    private static final String UPDATE_USERS_SQL = "update users\n" + "set name = ?, email = ?, country = ?\n" + "where id = ?";
    private static final String FIND_USERS_COUNTRY_SQL = "select * from users\n" + "where country = ?";
    private static final String ORDER_BY_NAME_SQL = "select * from users\n" + "order by name";
    private static final String GET_USER_SP = "{call get_user_by_id(?)}";
    private static final String INSERT_USER_SP = "{call insert_user(?,?,?)}";
    private static final String SQL_INSERT = "insert into employee(name,salary,created_date)\n" + "values(?,?,?)";
    private static final String SQL_UPDATE = "update employee\n" + "set salary = ?\n" + "where name = ?";
    private static final String SQL_CREATE_TABLE = "create table employee"
            + "("
            + "id serial,"
            + "name varchar(100) not null,"
            + "salary numeric(15,2) not null,"
            + "created_date timestamp,"
            + "primary key(id)"
            + ")";
    private static final String SQL_DROP_TABLE = "drop table if exists employee";
    private static final String SELECT_ALL_USERS_SP = "{call display_all_users}";
    private static final String UPDATE_USERS_SQL_SP = "{call update_user(?,?,?,?)}";
    private static final String DELETE_USERS_SQL_SP = "{call delete_user(?)}";

    @Override
    public void insertUpdateUseTransaction() {
        try {
            Connection connection = BaseRepository.connection;
            Statement statement = connection.createStatement();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);

            statement.execute(SQL_DROP_TABLE);
            statement.execute(SQL_CREATE_TABLE);

            connection.setAutoCommit(false);

            psInsert.setString(1,"Quynh");
            psInsert.setBigDecimal(2,new BigDecimal(10));
            psInsert.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            psInsert.setString(1,"Ngan");
            psInsert.setBigDecimal(2,new BigDecimal(20));
            psInsert.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            psUpdate.setBigDecimal(1,new BigDecimal(999.99));
            psUpdate.setString(2,"Quynh");
            psUpdate.execute();

            connection.commit();

            connection.setAutoCommit(true);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void insertUpdateWithoutTransaction() {
        try {
            Connection connection = BaseRepository.connection;
            Statement statement = connection.createStatement();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT);
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);

            statement.execute(SQL_DROP_TABLE);
            statement.execute(SQL_CREATE_TABLE);

            psInsert.setString(1,"Quynh");
            psInsert.setBigDecimal(2,new BigDecimal(10));
            psInsert.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            psInsert.setString(1,"Ngan");
            psInsert.setBigDecimal(2,new BigDecimal(20));
            psInsert.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));
            psInsert.execute();

            psUpdate.setBigDecimal(2,new BigDecimal(999.99));
            psUpdate.setString(2,"Quynh");
            psUpdate.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUser(User user) throws SQLException {
        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(SELECT_ALL_USERS);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");

                users.add(new User(id,name,email,country));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return users;
    }

    @Override
    public List<User> selectAllUsersSP() {
        List<User> users = new ArrayList<>();

        try {
            Connection connection = BaseRepository.connection;
            CallableStatement callableStatement = connection.prepareCall(SELECT_ALL_USERS_SP);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");

                users.add(new User(id,name,email,country));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return users;
    }

    @Override
    public List<User> findUserByCountry(String existingCountry) {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(FIND_USERS_COUNTRY_SQL);
            preparedStatement.setString(1,existingCountry);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");

                users.add(new User(id,name,email,country));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return users;
    }

    @Override
    public List<User> orderByName() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(ORDER_BY_NAME_SQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");

                users.add(new User(id,name,email,country));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return users;
    }

    @Override
    public User getUserByID(int id) {
        User user = null;

        try {
            Connection connection = BaseRepository.connection;
            CallableStatement callableStatement = connection.prepareCall(GET_USER_SP);
            callableStatement.setInt(1,id);

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return user;
    }

    @Override
    public void insertUserStore(User user) throws SQLException {
        try {
            Connection connection = BaseRepository.connection;
            CallableStatement callableStatement = connection.prepareCall(INSERT_USER_SP);
            callableStatement.setString(1,user.getName());
            callableStatement.setString(2,user.getEmail());
            callableStatement.setString(3,user.getCountry());

            callableStatement.executeUpdate();
        }
        catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public void addUserTransaction(User user, int[] permissions) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAssignment = null;
        ResultSet resultSet = null;

        try {
            connection = BaseRepository.connection;
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(INSERT_USERS_SQL,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());

            int rowAffected = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            int userId = 0;
            if (resultSet.next())
                userId = resultSet.getInt(1);

            if (rowAffected == 1) {
                String sqlPivot = "insert into user_permission(permision_id,user_id)\n" + "values(?,?)";
                preparedStatementAssignment = connection.prepareStatement(sqlPivot);

                for (int permissionId: permissions) {
                    preparedStatementAssignment.setInt(1,permissionId);
                    preparedStatementAssignment.setInt(2,userId);
                    preparedStatementAssignment.executeUpdate();
                }

                connection.commit();
            } else {
                connection.rollback();
            }
        }
        catch (SQLException ex) {
            try {
                if (connection != null)
                    connection.rollback();
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }

            System.out.println(ex.getMessage());
        }

        finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (preparedStatementAssignment != null)
                    preparedStatementAssignment.close();
                if (connection != null)
                    connection.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted = false;

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(DELETE_USERS_SQL);
            preparedStatement.setInt(1,id);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return rowDeleted;
    }

    @Override
    public boolean deleteUserSP(int id) throws SQLException {
        boolean rowDeleted = false;

        try {
            Connection connection = BaseRepository.connection;
            CallableStatement callableStatement = connection.prepareCall(DELETE_USERS_SQL_SP);
            callableStatement.setInt(1,id);

            rowDeleted = callableStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return rowDeleted;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated = false;

        try {
            PreparedStatement preparedStatement = BaseRepository.connection.prepareStatement(UPDATE_USERS_SQL);

            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            preparedStatement.setInt(4,user.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return rowUpdated;
    }

    @Override
    public boolean updateUserSP(User user) throws SQLException {
        boolean rowUpdated = false;

        try {
            Connection connection = BaseRepository.connection;
            CallableStatement callableStatement = connection.prepareCall(UPDATE_USERS_SQL_SP);

            callableStatement.setString(1,user.getName());
            callableStatement.setString(2,user.getEmail());
            callableStatement.setString(3,user.getCountry());
            callableStatement.setInt(4,user.getId());

            rowUpdated = callableStatement.executeUpdate() > 0;
        }
        catch (SQLException e) {
            printSQLException(e);
        }

        return rowUpdated;
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
