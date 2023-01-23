package ua.nure.cpp.kasapova.practice6.dao.interfaces;

import ua.nure.cpp.kasapova.practice6.dao.DBException;

import java.util.List;

public interface BaseDAO<T> {

    List<T> loadAll() throws DBException;

    void add(T obj) throws DBException;

    void update(T t) throws DBException;

    void deleteById(int id) throws DBException;

}