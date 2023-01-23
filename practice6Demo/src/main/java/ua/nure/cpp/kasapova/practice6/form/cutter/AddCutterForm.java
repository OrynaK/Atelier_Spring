package ua.nure.cpp.kasapova.practice6.form.cutter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddCutterForm {
    @NotNull
    @Size(min=2, max=50)
    private String name;
    @NotNull
    @Size(min=2, max=100)
    private String surname;

    public AddCutterForm() {
    }

    public AddCutterForm(String name, String surname) {
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
