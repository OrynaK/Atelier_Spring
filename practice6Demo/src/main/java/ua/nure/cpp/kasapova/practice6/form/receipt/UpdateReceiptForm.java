package ua.nure.cpp.kasapova.practice6.form.receipt;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class UpdateReceiptForm {
    @NotNull
    @Min(1)
    private int id;

    @NotNull
    private Date dateFitting;


    public UpdateReceiptForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateFitting() {
        return dateFitting;
    }

    public void setDateFitting(Date dateFitting) {
        this.dateFitting = dateFitting;
    }

    public UpdateReceiptForm(int id, Date dateFitting) {
        this.id = id;
        this.dateFitting = dateFitting;
    }
}