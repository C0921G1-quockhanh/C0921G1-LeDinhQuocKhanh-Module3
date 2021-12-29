package repository;

import bean.User;

import java.sql.*;
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
