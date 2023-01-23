package ua.nure.cpp.kasapova.practice6.form.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetModelByIdForm {
    @NotNull
    @Min(1)
    private int id;

    public GetModelByIdForm() {
    }

    public GetModelByIdForm(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
