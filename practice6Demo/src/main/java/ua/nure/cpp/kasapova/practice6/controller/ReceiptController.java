package ua.nure.cpp.kasapova.practice6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.ReceiptDAO;
import ua.nure.cpp.kasapova.practice6.entity.Receipt;
import ua.nure.cpp.kasapova.practice6.form.receipt.UpdateReceiptForm;
import ua.nure.cpp.kasapova.practice6.form.receipt.AddReceiptForm;
import ua.nure.cpp.kasapova.practice6.form.receipt.GetReceiptByIdForm;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReceiptController {
    //private final ReceiptDAO dao = DAOFactory.getReceiptDAOInstance(TypeDAO.COLLECTION);
    //private final ReceiptDAO dao = DAOFactory.getReceiptDAOInstance(TypeDAO.MySQL);

    @Autowired
    private ReceiptDAO dao;

    @RequestMapping(value = {"/receipts"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllReceipts(Model model) throws SQLException {
        List<Receipt> list = dao.loadAll();
        model.addAttribute("allReceipts", list);
        return "receipt/receiptsPage";
    }

    @GetMapping(value = {"/addReceipt"})
    public String showAddReceiptView(Model model) {
        AddReceiptForm addReceiptForm = new AddReceiptForm();
        model.addAttribute("addReceiptForm", addReceiptForm);
        return "receipt/addReceiptPage";
    }


    @PostMapping(value = {"/addReceipt"})
    public String addReceipt(Model model,@Valid AddReceiptForm addReceiptForm,BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addReceiptForm", addReceiptForm);
            return "receipt/addReceiptPage";
        }
        Receipt receipt = new Receipt();
        receipt.setClientId(addReceiptForm.getClientId());
        receipt.setCutterId(addReceiptForm.getCutterId());
        receipt.setModelId(addReceiptForm.getModelId());
        receipt.setFabricId(addReceiptForm.getFabricId());
        receipt.setDateAcceptance(addReceiptForm.getDateAcceptance());
        receipt.setDateFitting(addReceiptForm.getDateFitting());
        receipt.setDueDate(addReceiptForm.getDueDate());
        receipt.setStatus(addReceiptForm.getStatus());
        dao.add(receipt);
        return "redirect:/receipts";
    }

    @PostMapping(value = {"/deleteReceipt"})
    public String deleteReceipt(@RequestParam int id) throws SQLException {
        dao.deleteById(id);
        return "redirect:/receipts";
    }
    @ExceptionHandler(DBException.class)
    public String handleDeleteReceiptException(DBException e, Model model) {
        return "receipt/receiptDeleteError";
    }
    @GetMapping(value = {"/receiptById"})
    public String showGetReceiptByIdView(Model model) {
        GetReceiptByIdForm getReceiptByIdForm = new GetReceiptByIdForm();
        model.addAttribute("getReceiptByIdForm", getReceiptByIdForm);
        return "receipt/getReceiptByIdPage";
    }

    @PostMapping(value = {"/receiptById"})
    public String getReceiptById(Model model, @Valid GetReceiptByIdForm getReceiptByIdForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("getReceiptByIdForm", getReceiptByIdForm);
            return "receipt/getReceiptByIdPage";
        }
        List<Receipt> receipts = new ArrayList<>();
        if (dao.findById(getReceiptByIdForm.getId())==null) return "receipt/receiptsPage";
        receipts.add(dao.findById(getReceiptByIdForm.getId()));
        model.addAttribute("allReceipts", receipts);
        return "receipt/receiptsPage";
    }

    @GetMapping(value = {"/updateReceipt"})
    public String updateReceiptView(Model model) {
        UpdateReceiptForm updateReceiptForm = new UpdateReceiptForm();
        model.addAttribute("updateReceiptForm", updateReceiptForm);
        return "receipt/updateReceiptPage";
    }

    @PostMapping(value = {"/updateReceipt"})
    public String updateReceipt(Model model, @Valid UpdateReceiptForm updateReceiptForm, BindingResult bindingResult) throws SQLException {
        if (dao.findById(updateReceiptForm.getId()) == null || bindingResult.hasErrors()) {
            model.addAttribute("updateReceiptForm", updateReceiptForm);
            return "receipt/updateReceiptPage";
        }
        dao.updateFittingDate(updateReceiptForm.getId(), updateReceiptForm.getDateFitting());
        return "redirect:/receipts";
    }
}
