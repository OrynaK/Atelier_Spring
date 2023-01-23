package ua.nure.cpp.kasapova.practice6.form.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class UpdateModelForm {
    @NotNull
    @Min(1)
    private int id;
    @NotNull
    @Min((long) 0.1)
    private BigDecimal tailoringPrice;

    public UpdateModelForm() {
    }

    public UpdateModelForm(int id, BigDecimal tailoringPrice) {
        this.id = id;
        this.tailoringPrice = tailoringPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTailoringPrice() {
        return tailoringPrice;
    }

    public void setTailoringPrice(BigDecimal tailoringPrice) {
        this.tailoringPrice = tailoringPrice;
    }
}
