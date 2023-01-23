package ua.nure.cpp.kasapova.practice6.dao.interfaces;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.entity.Receipt;

import java.sql.Date;
import java.util.List;

public interface ReceiptDAO extends BaseDAO<Receipt> {

    void updateFittingDate(int id, Date date) throws DBException;

    Receipt findById(int id) throws DBException;
}
