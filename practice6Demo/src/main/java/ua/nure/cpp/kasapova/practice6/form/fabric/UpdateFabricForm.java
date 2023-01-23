package ua.nure.cpp.kasapova.practice6.form.fabric;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class UpdateFabricForm {
    @NotNull
    @Min(1)
    private int id;
    @NotNull
    @Min((long) 0.1)
    private BigDecimal width;

    public UpdateFabricForm() {
    }

    public UpdateFabricForm(int id, BigDecimal width) {
        this.id = id;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }
}

