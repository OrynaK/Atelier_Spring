package ua.nure.cpp.kasapova.practice6.dao.interfaces;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.entity.Client;

import java.util.List;

public interface ClientDAO extends BaseDAO<Client> {

    void updateName(int id, String name) throws DBException;

    Client findById(int id) throws DBException;

}
