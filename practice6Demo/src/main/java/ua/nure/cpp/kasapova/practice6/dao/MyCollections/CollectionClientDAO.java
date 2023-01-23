package ua.nure.cpp.kasapova.practice6.dao.MyCollections;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.BaseDAO;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.ClientDAO;
import ua.nure.cpp.kasapova.practice6.entity.Client;
import ua.nure.cpp.kasapova.practice6.entity.Receipt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionClientDAO implements ClientDAO {

    private static List<Client> clients = new ArrayList<>();

    static {
        clients.add(new Client(1, "Elizabeth", "Brown"));
        clients.add(new Client(2, "Susan", "Davies"));
        clients.add(new Client(3, "Karen", "Thomas"));
        clients.add(new Client(4, "Ashley", "Davies"));
        clients.add(new Client(5, "Joshua", "Dunn"));
        clients.add(new Client(6, "Jessica", "Evans"));
    }

    @Override
    public synchronized List<Client> loadAll() throws DBException {
        return clients;
    }

    @Override
    public synchronized void add(Client client) throws DBException {
        if (client == null) throw new DBException("add() failed, client is null");
        client.setId(clients.get(clients.size() - 1).getId() + 1);
        clients.add(client);
    }

    @Override
    public synchronized void update(Client client) throws DBException {
        if (client == null) throw new DBException("update() failed, client is null");
        for (Client obj : clients) {
            if (obj.getId() == client.getId()) {
                obj.setName(client.getName());
                obj.setSurname(client.getSurname());
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
            if (id == rec.getClientId()) throw new DBException("deleteById() failed. " +
                    "To delete client, please, firstly delete receipt with this client");
        }

        for (Client obj : clients) {
            if (obj.getId() == id) {
                clients.remove(obj);
                break;
            }
        }

    }

    @Override
    public synchronized void updateName(int id, String name) throws DBException {
        if (id <= 0) throw new DBException("updateName() failed, ID is not natural number");
        if (name == null) throw new DBException("updateName() failed, name is null");

        for (Client obj : clients) {
            if (obj.getId() == id) {
                obj.setName(name);
                break;
            }
        }
    }

    @Override
    public synchronized Client findById(int id) throws DBException {
        if (id <= 0) throw new DBException("findById() failed, ID is not natural number");

        for (Client obj : clients) {
            if (obj.getId() == id) {
                return obj;
            }
        }
        return null;
    }
}
