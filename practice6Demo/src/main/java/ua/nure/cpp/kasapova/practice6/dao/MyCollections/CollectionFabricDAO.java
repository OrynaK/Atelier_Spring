package ua.nure.cpp.kasapova.practice6.dao.MyCollections;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.FabricDAO;
import ua.nure.cpp.kasapova.practice6.entity.Client;
import ua.nure.cpp.kasapova.practice6.entity.Fabric;
import ua.nure.cpp.kasapova.practice6.entity.Receipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CollectionFabricDAO implements FabricDAO {
    private static List<Fabric> fabrics = new ArrayList<>();

    static {
        fabrics.add(new Fabric(1, "cashmere", new BigDecimal(3), new BigDecimal(240)));
        fabrics.add(new Fabric(2, "angora", new BigDecimal("2.5"), new BigDecimal(260)));
        fabrics.add(new Fabric(3, "velveteen", new BigDecimal("2.7"), new BigDecimal(80)));
        fabrics.add(new Fabric(4, "gabardine", new BigDecimal("1.2"), new BigDecimal(125)));
        fabrics.add(new Fabric(5, "eco leather", new BigDecimal("2.5"), new BigDecimal(320)));
    }

    @Override
    public synchronized List<Fabric> loadAll() throws DBException {
        return fabrics;
    }

    @Override
    public synchronized void add(Fabric fabric) throws DBException {
        if (fabric == null) throw new DBException("add() failed, fabric is null");
        fabric.setId(fabrics.get(fabrics.size()-1).getId()+1);
        fabrics.add(fabric);
    }

    @Override
    public synchronized void update(Fabric fabric) throws DBException {
        if (fabric == null) throw new DBException("update() failed, fabric is null");
        for (Fabric obj : fabrics) {
            if (obj.getId() == fabric.getId()) {
                obj.setName(fabric.getName());
                obj.setWidth(fabric.getWidth());
                obj.setPricePerMeter(fabric.getPricePerMeter());
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
            if (id == rec.getFabricId()) throw new DBException("deleteById() failed. " +
                    "To delete fabric, please, firstly delete receipt with this fabric");
        }
        for (Fabric obj : fabrics) {
            if (obj.getId() == id) {
                fabrics.remove(obj);
                break;
            }
        }
    }

    @Override
    public synchronized void updateWidth(int id, BigDecimal width) throws DBException {
        if (id <= 0) throw new DBException("updateWidth() failed, ID is not natural number");
        if (width == null) throw new DBException("updateWidth() failed, width is null");

        for (Fabric obj : fabrics) {
            if (obj.getId() == id) {
                obj.setWidth(width);
                break;
            }
        }
    }

    @Override
    public synchronized Fabric findById(int id) throws DBException {
        if (id <= 0) throw new DBException("findById() failed, ID is not natural number");

        for (Fabric obj : fabrics) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }
}
