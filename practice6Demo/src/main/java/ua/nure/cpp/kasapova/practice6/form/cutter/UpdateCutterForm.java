package ua.nure.cpp.kasapova.practice6.form.cutter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateCutterForm {
    @NotNull
    @Min(1)
    private int id;
    @NotBlank
    private String surname;

    public UpdateCutterForm() {
    }

    public UpdateCutterForm(int id, String surname) {
        this.id = id;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
