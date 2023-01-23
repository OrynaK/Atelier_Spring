package ua.nure.cpp.kasapova.practice6.dao.MyCollections;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.CutterDAO;
import ua.nure.cpp.kasapova.practice6.entity.Cutter;
import ua.nure.cpp.kasapova.practice6.entity.Receipt;

import java.util.ArrayList;
import java.util.List;

public class CollectionCutterDAO implements CutterDAO {

    private static List<Cutter> cutters = new ArrayList<>();

    static {
        cutters.add(new Cutter(1, "Mary", "Smith"));
        cutters.add(new Cutter(2, "Patricia", "Jones"));
        cutters.add(new Cutter(3, "David", "Taylor"));
    }

    @Override
    public synchronized List<Cutter> loadAll() throws DBException {
        return cutters;
    }

    @Override
    public synchronized void add(Cutter cutter) throws DBException {
        if (cutter == null) throw new DBException("add() failed, cutter is null");
        cutter.setId(cutters.get(cutters.size()-1).getId()+1);
        cutters.add(cutter);
    }

    @Override
    public synchronized void update(Cutter cutter) throws DBException {
        if (cutter == null) throw new DBException("update() failed, cutter is null");
        for (Cutter obj : cutters) {
            if (obj.getId() == cutter.getId()) {
                obj.setName(cutter.getName());
                obj.setSurname(cutter.getSurname());
                break;
            }
        }
    }

    @Override
    public synchronized void deleteById(int id) throws DBException {
        if (id <= 0) throw new DBException("deleteById() failed, ID is not natural number");
        CollectionReceiptDAO dao = new CollectionReceiptDAO();
        List<Receipt> receipts = dao.loadAll();
        for (Receipt rec : receipts) {
            if (id == rec.getCutterId()) throw new DBException("deleteById() failed. " +
                    "To delete cutter, please, firstly delete receipt with this cutter");
        }
        for (Cutter obj : cutters) {
            if (obj.getId() == id) {
                cutters.remove(obj);
                break;
            }
        }
    }

    @Override
    public synchronized void updateSurname(int id, String surname) throws DBException {
        if (id <= 0) throw new DBException("updateSurname() failed, ID is not natural number");
        if (surname == null) throw new DBException("updateSurname() failed, surname is null");

        for (Cutter obj : cutters) {
            if (obj.getId() == id) {
                obj.setSurname(surname);
                break;
            }
        }
    }

    @Override
    public synchronized Cutter findById(int id) throws DBException {
        if (id <= 0) throw new DBException("findById() failed, ID is not natural number");

        for (Cutter obj : cutters) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }
}
