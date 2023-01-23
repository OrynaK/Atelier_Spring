package ua.nure.cpp.kasapova.practice6.form.receipt;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GetReceiptByIdForm {
    @NotNull
    @Min(1)
    private int id;

    public GetReceiptByIdForm() {
    }

    public GetReceiptByIdForm(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
