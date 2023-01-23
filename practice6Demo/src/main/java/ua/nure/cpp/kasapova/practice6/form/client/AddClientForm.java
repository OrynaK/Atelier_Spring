package ua.nure.cpp.kasapova.practice6.form.client;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddClientForm {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    public AddClientForm(){}

    public AddClientForm(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
