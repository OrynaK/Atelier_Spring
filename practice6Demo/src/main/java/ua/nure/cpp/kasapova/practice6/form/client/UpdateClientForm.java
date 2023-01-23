package ua.nure.cpp.kasapova.practice6.form.client;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateClientForm {
    @NotNull
    @Min(1)
    private int id;
    @NotBlank
    private String name;


    public UpdateClientForm() {
    }

    public UpdateClientForm(int id, String name) {
        this.id=id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
