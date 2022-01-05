package repository.service;

import bean.Service;

import java.sql.SQLException;

public interface IServiceRepository {
    public void insertService(Service service) throws SQLException;
}
