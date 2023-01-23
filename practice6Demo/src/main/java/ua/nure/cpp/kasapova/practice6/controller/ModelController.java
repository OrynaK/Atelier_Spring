package ua.nure.cpp.kasapova.practice6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.nure.cpp.kasapova.practice6.dao.DBException;
import ua.nure.cpp.kasapova.practice6.dao.interfaces.ModelDAO;
import ua.nure.cpp.kasapova.practice6.form.model.UpdateModelForm;
import ua.nure.cpp.kasapova.practice6.form.model.AddModelForm;
import ua.nure.cpp.kasapova.practice6.form.model.GetModelByIdForm;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ModelController {
    //private final ModelDAO dao = DAOFactory.getModelDAOInstance(TypeDAO.COLLECTION);
    //private final ModelDAO dao = DAOFactory.getModelDAOInstance(TypeDAO.MySQL);

    @Autowired
    private ModelDAO dao;

    @RequestMapping(value = { "/models"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String showAllModels(Model model) throws SQLException {
        List<ua.nure.cpp.kasapova.practice6.entity.Model> list = dao.loadAll();
        model.addAttribute("allModels", list);
        return "model/modelsPage";
    }

    @GetMapping(value = {"/addModel"})
    public String showAddModelView(Model model) {
        AddModelForm addModelForm = new AddModelForm();
        model.addAttribute("addModelForm", addModelForm);
        return "model/addModelPage";
    }

    @PostMapping(value = {"/addModel"})
    public String addModel(Model model,@Valid AddModelForm addModelForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("addModelForm", addModelForm);
            return "model/addModelPage";
        }
        ua.nure.cpp.kasapova.practice6.entity.Model clothingModel= new ua.nure.cpp.kasapova.practice6.entity.Model();
        clothingModel.setName(addModelForm.getName());
        clothingModel.setProposedFabric(addModelForm.getProposedFabric());
        clothingModel.setTailoringPrice(addModelForm.getTailoringPrice());
        clothingModel.setConsumption(addModelForm.getConsumption());
        dao.add(clothingModel);
        return "redirect:/models";
    }

    @PostMapping(value = {"/deleteModel"})
    public String deleteModel(@RequestParam int id) throws SQLException {
        dao.deleteById(id);
        return "redirect:/models";
    }
    @ExceptionHandler(DBException.class)
    public String handleDeleteModelException(DBException e, Model model) {
        return "model/modelDeleteError";
    }
    @GetMapping(value = {"/modelById"})
    public String showGetModelByIdView(Model model) {
        GetModelByIdForm getModelByIdForm = new GetModelByIdForm();
        model.addAttribute("getModelByIdForm", getModelByIdForm);
        return "model/getModelByIdPage";
    }

    @PostMapping(value = {"/modelById"})
    public String getModelById(Model model,@Valid GetModelByIdForm getModelByIdForm, BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("getModelByIdForm", getModelByIdForm);
            return "model/getModelByIdPage";
        }
        List<ua.nure.cpp.kasapova.practice6.entity.Model> models = new ArrayList<>();
        if(dao.findById(getModelByIdForm.getId())==null) return "model/modelsPage";
        models.add(dao.findById(getModelByIdForm.getId()));
        model.addAttribute("allModels", models);
        return "model/modelsPage";
    }
    @GetMapping(value = {"/updateModel"})
    public String updateModelView(Model model) {
        UpdateModelForm updateModelForm = new UpdateModelForm();
        model.addAttribute("updateModelForm", updateModelForm);
        return "model/updateModelPage";
    }

    @PostMapping(value = {"/updateModel"})
    public String updateModel(Model model, @Valid UpdateModelForm updateModelForm, BindingResult bindingResult) throws SQLException {
        if (dao.findById(updateModelForm.getId()) == null || bindingResult.hasErrors()) {
            model.addAttribute("updateModelForm", updateModelForm);
            return "model/updateModelPage";
        }
        dao.updateTailoringPrice(updateModelForm.getId(), updateModelForm.getTailoringPrice());
        return "redirect:/models";
    }
}
