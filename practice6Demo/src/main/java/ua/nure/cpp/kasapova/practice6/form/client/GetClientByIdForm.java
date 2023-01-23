package ua.nure.cpp.kasapova.practice6.form.client;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetClientByIdForm {
    @NotNull
    @Min(1)
    private int id;

    public GetClientByIdForm() {
    }

    public GetClientByIdForm(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
