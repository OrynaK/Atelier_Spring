package ua.nure.cpp.kasapova.practice6.form.fabric;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetFabricByIdForm {
    @NotNull
    @Min(1)
    private int id;

    public GetFabricByIdForm() {
    }

    public GetFabricByIdForm(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
