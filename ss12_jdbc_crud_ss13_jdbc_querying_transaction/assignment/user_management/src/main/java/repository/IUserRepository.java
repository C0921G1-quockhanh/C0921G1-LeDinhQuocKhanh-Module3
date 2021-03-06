package repository;

import bean.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
    public void insertUser(User user) throws SQLException;

    public User selectUser(int id);

    public List<User> selectAllUsers();

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    public List<User> findUserByCountry(String existingCountry);

    public List<User> orderByName();

    public User getUserByID(int id);

    public void insertUserStore(User user) throws SQLException;

    public void addUserTransaction(User user, int[] permissions);

    public void insertUpdateWithoutTransaction();

    public void insertUpdateUseTransaction();

    public List<User> selectAllUsersSP();

    public boolean updateUserSP(User user) throws SQLException;

    public boolean deleteUserSP(int id) throws SQLException;
}
