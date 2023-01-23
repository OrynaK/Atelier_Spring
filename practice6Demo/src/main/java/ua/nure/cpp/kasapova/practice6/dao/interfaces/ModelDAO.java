package ua.nure.cpp.kasapova.practice6.dao.interfaces;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.entity.Model;

import java.math.BigDecimal;
import java.util.List;

public interface ModelDAO extends BaseDAO<Model> {

    void updateTailoringPrice(int id, BigDecimal tailoringPrice) throws DBException;

    Model findById(int id) throws DBException;
}
