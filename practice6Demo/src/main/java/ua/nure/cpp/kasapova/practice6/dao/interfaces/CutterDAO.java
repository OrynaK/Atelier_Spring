package ua.nure.cpp.kasapova.practice6.dao.interfaces;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.entity.Cutter;

import java.util.List;

public interface CutterDAO extends BaseDAO<Cutter>{

    void updateSurname(int id, String surname) throws DBException;

    Cutter findById(int id) throws DBException;
}
