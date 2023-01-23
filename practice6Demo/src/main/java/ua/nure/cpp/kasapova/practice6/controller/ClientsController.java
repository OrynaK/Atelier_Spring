package ua.nure.cpp.kasapova.practice6.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.ClientDAO;
import ua.nure.cpp.kasapova.practice6.entity.Client;
import ua.nure.cpp.kasapova.practice6.form.client.AddClientForm;
import ua.nure.cpp.kasapova.practice6.form.client.GetClientByIdForm;
import ua.nure.cpp.kasapova.practice6.form.client.UpdateClientForm;

import javax.validation.Valid;

@Controller
public class ClientsController {
    //private final ClientDAO dao = DAOFactory.getClientDAOInstance(TypeDAO.COLLECTION);
    //private final ClientDAO dao = DAOFactory.getClientDAOInstance(TypeDAO.MySQL);

    @Autowired
    private ClientDAO dao;

    @GetMapping(value = {"/", "atelier"})
    public String showGeneral(Model model) {
        return "atelierPage";
    }

    @RequestMapping(value = {"/clients"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllClients(Model model) throws SQLException {
        List<Client> list = dao.loadAll();
        model.addAttribute("allClients", list);
        return "client/clientsPage";
    }

    @GetMapping(value = {"/addClient"})
    public String showAddClientView(Model model) {
        AddClientForm addClientForm = new AddClientForm();
        model.addAttribute("addClientForm", addClientForm);
        return "client/addClientPage";
    }

    @PostMapping(value = {"/addClient"})
    public String addClient(Model model, @Valid AddClientForm addClientForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addClientForm", addClientForm);
            return "client/addClientPage";
        }

        Client client = new Client();
        client.setName(addClientForm.getName());
        client.setSurname(addClientForm.getSurname());
        dao.add(client);
        return "redirect:/clients";
    }


    @PostMapping(value = {"/deleteClient"})
    public String deleteClient(@RequestParam int id) throws SQLException {
        dao.deleteById(id);
        return "redirect:/clients";
    }

    @ExceptionHandler(DBException.class)
    public String handleDeleteClientException() {
        return "client/clientDeleteError";
    }

    @GetMapping(value = {"/clientById"})
    public String showGetClientByIdView(Model model) {
        GetClientByIdForm getClientByIdForm = new GetClientByIdForm();
        model.addAttribute("getClientByIdForm", getClientByIdForm);
        return "client/getClientByIdPage";
    }

    @PostMapping(value = {"/clientById"})
    public String getClientById(Model model, @Valid GetClientByIdForm getClientByIdForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("getClientByIdForm", getClientByIdForm);
            return "client/getClientByIdPage";
        }
        List<Client> clients = new ArrayList<>();
        if (dao.findById(getClientByIdForm.getId()) == null) return "client/clientsPage";
        clients.add(dao.findById(getClientByIdForm.getId()));
        model.addAttribute("allClients", clients);
        return "client/clientsPage";
    }

    @GetMapping(value = {"/updateClient"})
    public String updateClientView(Model model) {
        UpdateClientForm updateClientForm = new UpdateClientForm();
        model.addAttribute("updateClientForm", updateClientForm);
        return "client/updateClientPage";
    }

    @PostMapping(value = {"/updateClient"})
    public String updateClient(Model model, @Valid UpdateClientForm updateClientForm, BindingResult bindingResult) throws SQLException {
        if (dao.findById(updateClientForm.getId()) == null || bindingResult.hasErrors()) {
            model.addAttribute("updateClientForm", updateClientForm);
            return "client/updateClientPage";
        }
        dao.updateName(updateClientForm.getId(), updateClientForm.getName());
        return "redirect:/clients";
    }
}
