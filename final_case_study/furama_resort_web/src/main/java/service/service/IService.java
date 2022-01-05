package service.service;

import bean.Service;

import java.sql.SQLException;

public interface IService {
    public void insertService(Service service) throws SQLException;
}
