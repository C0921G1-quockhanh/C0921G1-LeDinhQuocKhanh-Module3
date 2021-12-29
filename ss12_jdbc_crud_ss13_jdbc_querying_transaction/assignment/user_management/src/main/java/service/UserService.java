package service;

import bean.User;
import repository.IUserRepository;
import repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService {

    private IUserRepository iUserRepository = new UserRepository();

    @Override
    public void insertUser(User user) throws SQLException {
        this.iUserRepository.insertUser(user);
    }

    @Override
    public User selectUser(int id) {
        return this.iUserRepository.selectUser(id);
    }

    @Override
    public List<User> selectAllUsers() {
        return this.iUserRepository.selectAllUsers();
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return this.iUserRepository.deleteUser(id);
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return this.iUserRepository.updateUser(user);
    }

    @Override
    public List<User> findUserByCountry(String existingCountry) {
        return this.iUserRepository.findUserByCountry(existingCountry);
    }

    @Override
    public List<User> orderByName() {
        return this.iUserRepository.orderByName();
    }

    @Override
    public User getUserByID(int id) {
        return this.iUserRepository.getUserByID(id);
    }

    @Override
    public void insertUserStore(User user) throws SQLException {
        this.iUserRepository.insertUserStore(user);
    }

    @Override
    public void addUserTransaction(User user, int[] permissions) {
        this.iUserRepository.addUserTransaction(user,permissions);
    }

    @Override
    public void insertUpdateWithoutTransaction() {
        this.iUserRepository.insertUpdateWithoutTransaction();
    }

    @Override
    public void insertUpdateUseTransaction() {
        this.iUserRepository.insertUpdateUseTransaction();
    }

    @Override
    public List<User> selectAllUsersSP() {
        return this.iUserRepository.selectAllUsersSP();
    }

    @Override
    public boolean updateUserSP(User user) throws SQLException {
        return this.iUserRepository.updateUserSP(user);
    }

    @Override
    public boolean deleteUserSP(int id) throws SQLException {
        return this.iUserRepository.deleteUserSP(id);
    }
}
