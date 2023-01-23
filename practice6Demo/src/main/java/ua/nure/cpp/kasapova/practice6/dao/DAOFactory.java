package ua.nure.cpp.kasapova.practice6.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ua.nure.cpp.kasapova.practice6.dao.MyCollections.*;
import ua.nure.cpp.kasapova.practice6.dao.impl.*;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.*;

import javax.naming.ConfigurationException;

@Component
public class DAOFactory {

    public static ClientDAO getClientDAOInstance(TypeDAO type) {
        ClientDAO dao = null;
        if (type == TypeDAO.MySQL) {
            dao = MySQLClientDAO.getInstance();
        }
        if (type == TypeDAO.COLLECTION) {
            dao = new CollectionClientDAO();
        }
        return dao;
    }

    public static CutterDAO getCutterDAOInstance(TypeDAO type) {
        CutterDAO dao = null;
        if (type == TypeDAO.MySQL) {
            dao = MySQLCutterDAO.getInstance();
        }
        if (type == TypeDAO.COLLECTION) {
            dao = new CollectionCutterDAO();
        }
        return dao;
    }

    public static FabricDAO getFabricDAOInstance(TypeDAO type) {
        FabricDAO dao = null;
        if (type == TypeDAO.MySQL) {
            dao = MySQLFabricDAO.getInstance();
        }
        if (type == TypeDAO.COLLECTION) {
            dao = new CollectionFabricDAO();
        }
        return dao;
    }

    public static ModelDAO getModelDAOInstance(TypeDAO type) {
        ModelDAO dao = null;
        if (type == TypeDAO.MySQL) {
            dao = MySQLModelDAO.getInstance();        }
        if (type == TypeDAO.COLLECTION) {
            dao = new CollectionModelDAO();
        }
        return dao;
    }

    public static ReceiptDAO getReceiptDAOInstance(TypeDAO type) {
        ReceiptDAO dao = null;
        if (type == TypeDAO.MySQL) {
            dao = MySQLReceiptDAO.getInstance();        }
        if (type == TypeDAO.COLLECTION) {
            dao = new CollectionReceiptDAO();
        }
        return dao;
    }

    @Bean
    public ClientDAO getClientDAOInstance(DAOConfig config) {
        if (TypeDAO.MySQL.name().equalsIgnoreCase(config.getType())) {
            return MySQLClientDAO.getInstance();
        }
        if (TypeDAO.COLLECTION.name().equalsIgnoreCase(config.getType())) {
            return new CollectionClientDAO();
        }
        throw new RuntimeException(new ConfigurationException("Unknown DAO type: " + config));
    }

    @Bean
    public CutterDAO getCutterDAOInstance(DAOConfig config) {
        if (TypeDAO.MySQL.name().equalsIgnoreCase(config.getType())) {
            return MySQLCutterDAO.getInstance();
        }
        if (TypeDAO.COLLECTION.name().equalsIgnoreCase(config.getType())) {
            return new CollectionCutterDAO();
        }
        throw new RuntimeException(new ConfigurationException("Unknown DAO type: " + config));
    }

    @Bean
    public FabricDAO getFabricDAOInstance(DAOConfig config) {
        if (TypeDAO.MySQL.name().equalsIgnoreCase(config.getType())) {
            return MySQLFabricDAO.getInstance();
        }
        if (TypeDAO.COLLECTION.name().equalsIgnoreCase(config.getType())) {
            return new CollectionFabricDAO();
        }
        throw new RuntimeException(new ConfigurationException("Unknown DAO type: " + config));
    }

    @Bean
    public ModelDAO getModelDAOInstance(DAOConfig config) {
        if (TypeDAO.MySQL.name().equalsIgnoreCase(config.getType())) {
            return MySQLModelDAO.getInstance();
        }
        if (TypeDAO.COLLECTION.name().equalsIgnoreCase(config.getType())) {
            return new CollectionModelDAO();
        }
        throw new RuntimeException(new ConfigurationException("Unknown DAO type: " + config));
    }

    @Bean
    public ReceiptDAO getReceiptDAOInstance(DAOConfig config) {
        if (TypeDAO.MySQL.name().equalsIgnoreCase(config.getType())) {
            return MySQLReceiptDAO.getInstance();
        }
        if (TypeDAO.COLLECTION.name().equalsIgnoreCase(config.getType())) {
            return new CollectionReceiptDAO();
        }
        throw new RuntimeException(new ConfigurationException("Unknown DAO type: " + config));
    }
}
