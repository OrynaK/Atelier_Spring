package ua.nure.cpp.kasapova.practice6.dao.MyCollections;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.ReceiptDAO;
import ua.nure.cpp.kasapova.practice6.entity.Receipt;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CollectionReceiptDAO implements ReceiptDAO {

    private static List<Receipt> receipts = new ArrayList<>();

    static {
        receipts.add(new Receipt(1, 1, 8, 1, 3, new Date(121, 12, 15), new Date(121, 12, 2), new Date(121, 12, 10), false));
        receipts.add(new Receipt(2, 2, 3, 4, 2, new Date(121, 11, 10), new Date(121, 12, 5), new Date(121, 12, 12), false));
        receipts.add(new Receipt(3, 3, 4, 3, 2, new Date(121, 10, 30), new Date(121, 11, 15), new Date(121, 11, 25), true));
        receipts.add(new Receipt(4, 4, 3, 4, 3, new Date(121, 11, 1), new Date(121, 11, 17), new Date(121, 11, 30), false));
        receipts.add(new Receipt(5, 5, 1, 2, 3, new Date(121, 12, 1), new Date(121, 12, 13), new Date(121, 12, 18), false));
        receipts.add(new Receipt(6, 6, 6, 1, 2, new Date(121, 11, 26), new Date(121, 12, 4), new Date(121, 12, 9), false));
    }

    @Override
    public synchronized List<Receipt> loadAll() throws DBException {
        return receipts;
    }

    @Override
    public synchronized void add(Receipt receipt) throws DBException {
        if (receipt == null) throw new DBException("add() failed, receipt is null");
        receipt.setId(receipts.get(receipts.size()-1).getId()+1);
        receipts.add(receipt);
    }

    @Override
    public synchronized void update(Receipt receipt) throws DBException {
        if (receipt == null) throw new DBException("update() failed, receipt is null");
        for (Receipt obj : receipts) {
            if (obj.getId() == receipt.getId()) {
                obj.setClientId(receipt.getClientId());
                obj.setCutterId(receipt.getCutterId());
                obj.setFabricId(receipt.getFabricId());
                obj.setModelId(receipt.getModelId());
                obj.setDateAcceptance(receipt.getDateAcceptance());
                obj.setDateFitting(receipt.getDateFitting());
                obj.setDueDate(receipt.getDueDate());
                obj.setStatus(receipt.getStatus());
                break;
            }
        }
    }

    @Override
    public synchronized void deleteById(int id) throws DBException {
        if (id <= 0) throw new DBException("deleteById() failed, ID is not natural number");

        for (Receipt obj : receipts) {
            if (obj.getId() == id) {
                receipts.remove(obj);
                break;
            }
        }
    }

    @Override
    public synchronized void updateFittingDate(int id, Date date) throws DBException {
        if (id <= 0) throw new DBException("updateFittingDate() failed, ID is not natural number");
        if (date == null) throw new DBException("updateFittingDate() failed, fitting date is null");

        for (Receipt obj : receipts) {
            if (obj.getId() == id) {
                obj.setDateFitting(date);
                break;
            }
        }
    }

    @Override
    public synchronized Receipt findById(int id) throws DBException {
        if (id <= 0) throw new DBException("findById() failed, ID is not natural number");

        for (Receipt obj : receipts) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(receipts);
    }
}
