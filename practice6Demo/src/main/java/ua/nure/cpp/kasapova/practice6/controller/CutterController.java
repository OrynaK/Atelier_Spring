package ua.nure.cpp.kasapova.practice6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.CutterDAO;
import ua.nure.cpp.kasapova.practice6.entity.Cutter;
import ua.nure.cpp.kasapova.practice6.form.cutter.UpdateCutterForm;
import ua.nure.cpp.kasapova.practice6.form.cutter.AddCutterForm;
import ua.nure.cpp.kasapova.practice6.form.cutter.GetCutterByIdForm;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CutterController {
    //private final CutterDAO dao = DAOFactory.getCutterDAOInstance(TypeDAO.COLLECTION);
    //private final CutterDAO dao = DAOFactory.getCutterDAOInstance(TypeDAO.MySQL);

    @Autowired
    private CutterDAO dao;

    @RequestMapping(value = { "/cutters"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllCutters(Model model) throws SQLException {
        List<Cutter> list = dao.loadAll();
        model.addAttribute("allCutters", list);
        return "cutter/cuttersPage";
    }

    @GetMapping(value = {"/addCutter"})
    public String showAddCutterView(Model model) {
        AddCutterForm addCutterForm = new AddCutterForm();
        model.addAttribute("addCutterForm", addCutterForm);
        return "cutter/addCutterPage";
    }

    @PostMapping(value = {"/addCutter"})
    public String addCutter(Model model,@Valid AddCutterForm addCutterForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addCutterForm", addCutterForm);
            return "cutter/addCutterPage";
        }
        Cutter cutter=new Cutter();
        cutter.setName(addCutterForm.getName());
        cutter.setSurname(addCutterForm.getSurname());
        dao.add(cutter);
        return "redirect:/cutters";
    }

    @PostMapping(value = {"/deleteCutter"})
    public String deleteCutter(@RequestParam int id) throws SQLException {
        dao.deleteById(id);
        return "redirect:/cutters";
    }
    @ExceptionHandler(DBException.class)
    public String handleDeleteCutterException(DBException e, Model model) {
        return "cutter/cutterDeleteError";
    }
    @GetMapping(value = {"/cutterById"})
    public String showGetCutterByIdView(Model model) {
        GetCutterByIdForm getCutterByIdForm = new GetCutterByIdForm();
        model.addAttribute("getCutterByIdForm", getCutterByIdForm);
        return "cutter/getCutterByIdPage";
    }

    @PostMapping(value = {"/cutterById"})
    public String getCutterById(Model model,@Valid GetCutterByIdForm getCutterByIdForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("getCutterByIdForm", getCutterByIdForm);
            return "cutter/getCutterByIdPage";
        }
        List<Cutter> cutters = new ArrayList<>();
        if(dao.findById(getCutterByIdForm.getId())==null) return "cutter/cuttersPage";
        cutters.add(dao.findById(getCutterByIdForm.getId()));
        model.addAttribute("allCutters", cutters);
        return "cutter/cuttersPage";
    }

    @GetMapping(value = {"/updateCutter"})
    public String updateCutterView(Model model) {
        UpdateCutterForm updateCutterForm = new UpdateCutterForm();
        model.addAttribute("updateCutterForm", updateCutterForm);
        return "cutter/updateCutterPage";
    }

    @PostMapping(value = {"/updateCutter"})
    public String updateCutter(Model model, @Valid UpdateCutterForm updateCutterForm, BindingResult bindingResult) throws SQLException {
        if (dao.findById(updateCutterForm.getId()) == null || bindingResult.hasErrors()) {
            model.addAttribute("updateCutterForm", updateCutterForm);
            return "cutter/updateCutterPage";
        }
        dao.updateSurname(updateCutterForm.getId(), updateCutterForm.getSurname());
        return "redirect:/cutters";
    }
}
