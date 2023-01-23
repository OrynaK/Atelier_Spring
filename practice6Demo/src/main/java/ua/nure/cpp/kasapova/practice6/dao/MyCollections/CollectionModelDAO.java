package ua.nure.cpp.kasapova.practice6.dao.MyCollections;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.ModelDAO;
import ua.nure.cpp.kasapova.practice6.entity.Client;
import ua.nure.cpp.kasapova.practice6.entity.Model;
import ua.nure.cpp.kasapova.practice6.entity.Receipt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CollectionModelDAO implements ModelDAO {

    private static List<Model> models = new ArrayList<>();

    static {
        models.add(new Model(1, "should dress", 1, new BigDecimal(900), new BigDecimal("309.5")));
        models.add(new Model(2, "bandeau", 2, new BigDecimal(800), new BigDecimal(410)));
        models.add(new Model(3, "parachute jumpsuit", 2, new BigDecimal(1500), new BigDecimal("1090.5")));
        models.add(new Model(4, "bodysuit", 5, new BigDecimal(850), new BigDecimal(350)));
        models.add(new Model(5, "tracksuits", 5, new BigDecimal(600), new BigDecimal(1040)));
        models.add(new Model(6, "bodycon", 1, new BigDecimal(500), new BigDecimal(500)));
        models.add(new Model(7, "midi dress", 4, new BigDecimal(550), new BigDecimal("1290.6")));
        models.add(new Model(8, "maxi dress", 3, new BigDecimal(800), new BigDecimal(1300)));
    }

    @Override
    public synchronized List<Model> loadAll() throws DBException {
        return models;
    }

    @Override
    public synchronized void add(Model model) throws DBException {
        if (model == null) throw new DBException("add() failed, model is null");
        model.setId(models.get(models.size()-1).getId()+1);
        models.add(model);
    }

    @Override
    public synchronized void update(Model model) throws DBException {
        if (model == null) throw new DBException("update() failed, model is null");
        for (Model obj : models) {
            if (obj.getId() == model.getId()) {
                obj.setName(model.getName());
                obj.setProposedFabric(model.getProposedFabric());
                obj.setConsumption(model.getConsumption());
                obj.setTailoringPrice(model.getTailoringPrice());
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
            if (id == rec.getModelId()) throw new DBException("deleteById() failed. " +
                    "To delete model, please, firstly delete receipt with this model");
        }
        for (Model obj : models) {
            if (obj.getId() == id) {
                models.remove(obj);
                break;
            }
        }
    }

    @Override
    public synchronized void updateTailoringPrice(int id, BigDecimal tailoringPrice) throws DBException {
        if (id <= 0) throw new DBException("updateTailoringPrice() failed, ID is not natural number");
        if (tailoringPrice == null) throw new DBException("updateName() failed, tailoring price is null");

        for (Model obj : models) {
            if (obj.getId() == id) {
                obj.setTailoringPrice(tailoringPrice);
                break;
            }
        }
    }

    @Override
    public synchronized Model findById(int id) throws DBException {
        if (id <= 0) throw new DBException("findById() failed, ID is not natural number");

        for (Model obj : models) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }
}
