package ua.nure.cpp.kasapova.practice6.form.cutter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetCutterByIdForm {
    @NotNull
    @Min(1)
    private int id;

    public GetCutterByIdForm() {
    }

    public GetCutterByIdForm(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
