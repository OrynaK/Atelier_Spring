package ua.nure.cpp.kasapova.practice6.dao.interfaces;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.entity.Fabric;

import java.math.BigDecimal;
import java.util.List;

public interface FabricDAO extends BaseDAO<Fabric> {
    void updateWidth(int id, BigDecimal width) throws DBException;

    Fabric findById(int id) throws DBException;
}
