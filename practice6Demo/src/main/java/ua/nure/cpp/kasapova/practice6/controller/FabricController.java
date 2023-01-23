package ua.nure.cpp.kasapova.practice6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.FabricDAO;
import ua.nure.cpp.kasapova.practice6.entity.Fabric;
import ua.nure.cpp.kasapova.practice6.entity.Fabric;
import ua.nure.cpp.kasapova.practice6.form.fabric.UpdateFabricForm;
import ua.nure.cpp.kasapova.practice6.form.fabric.AddFabricForm;
import ua.nure.cpp.kasapova.practice6.form.fabric.GetFabricByIdForm;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FabricController {
    //private final FabricDAO dao = DAOFactory.getFabricDAOInstance(TypeDAO.COLLECTION);
    //private final FabricDAO dao = DAOFactory.getFabricDAOInstance(TypeDAO.MySQL);

    @Autowired
    private FabricDAO dao;

    @RequestMapping(value = { "/fabrics"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllFabrics(Model model) throws SQLException {
        List<Fabric> list = dao.loadAll();
        model.addAttribute("allFabrics", list);
        return "fabric/fabricsPage";
    }

    @GetMapping(value = {"/addFabric"})
    public String showAddFabricView(Model model) {
        AddFabricForm addFabricForm = new AddFabricForm();
        model.addAttribute("addFabricForm", addFabricForm);
        return "fabric/addFabricPage";
    }

    @PostMapping(value = {"/addFabric"})
    public String addFabric(Model model, @Valid AddFabricForm addFabricForm,BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addFabricForm", addFabricForm);
            return "fabric/addFabricPage";
        }
        Fabric fabric=new Fabric();
        fabric.setName(addFabricForm.getName());
        fabric.setWidth(addFabricForm.getWidth());
        fabric.setPricePerMeter(addFabricForm.getPricePerMeter());
        dao.add(fabric);
        return "redirect:/fabrics";
    }

    @PostMapping(value = {"/deleteFabric"})
    public String deleteFabric(@RequestParam int id) throws SQLException {
        dao.deleteById(id);
        return "redirect:/fabrics";
    }
    @ExceptionHandler(DBException.class)
    public String handleDeleteFabricException(DBException e, Model model) {
        return "fabric/fabricDeleteError";
    }
    @GetMapping(value = {"/fabricById"})
    public String showGetFabricByIdView(Model model) {
        GetFabricByIdForm getFabricByIdForm = new GetFabricByIdForm();
        model.addAttribute("getFabricByIdForm", getFabricByIdForm);
        return "fabric/getFabricByIdPage";
    }

    @PostMapping(value = {"/fabricById"})
    public String getFabricById(Model model, @Valid GetFabricByIdForm getFabricByIdForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("getFabricByIdForm", getFabricByIdForm);
            return "fabric/getFabricByIdPage";
        }
        List<Fabric> fabrics = new ArrayList<>();
        if(dao.findById(getFabricByIdForm.getId())==null) return "fabric/fabricsPage";
        fabrics.add(dao.findById(getFabricByIdForm.getId()));
        model.addAttribute("allFabrics", fabrics);
        return "fabric/fabricsPage";
    }

    @GetMapping(value = {"/updateFabric"})
    public String updateFabricView(Model model) {
        UpdateFabricForm updateFabricForm = new UpdateFabricForm();
        model.addAttribute("updateFabricForm", updateFabricForm);
        return "fabric/updateFabricPage";
    }

    @PostMapping(value = {"/updateFabric"})
    public String updateFabric(Model model, @Valid UpdateFabricForm updateFabricForm, BindingResult bindingResult) throws SQLException {
        if (dao.findById(updateFabricForm.getId()) == null || bindingResult.hasErrors()) {
            model.addAttribute("updateFabricForm", updateFabricForm);
            return "fabric/updateFabricPage";
        }
        dao.updateWidth(updateFabricForm.getId(), updateFabricForm.getWidth());
        return "redirect:/fabrics";
    }
}
