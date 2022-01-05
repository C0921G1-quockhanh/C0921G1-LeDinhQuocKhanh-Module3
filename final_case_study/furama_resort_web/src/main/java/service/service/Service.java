package service.service;

import repository.service.IServiceRepository;
import repository.service.ServiceRepository;

import java.sql.SQLException;

public class Service implements IService {

    private IServiceRepository iServiceRepository = new ServiceRepository();

    @Override
    public void insertService(bean.Service service) throws SQLException {
        this.iServiceRepository.insertService(service);
    }
}
