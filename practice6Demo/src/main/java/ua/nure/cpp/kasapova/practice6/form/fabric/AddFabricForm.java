package ua.nure.cpp.kasapova.practice6.form.fabric;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddFabricForm {
    @NotNull
    @Size(min=2, max=50)
    private String name;
    @NotNull
    @Min((long)0.1)
    private BigDecimal width;
    @NotNull
    @Min((long)0.1)
    private BigDecimal pricePerMeter;

    public AddFabricForm() {
    }

    public AddFabricForm(String name, BigDecimal width, BigDecimal pricePerMeter) {
        this.name = name;
        this.width = width;
        this.pricePerMeter = pricePerMeter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getPricePerMeter() {
        return pricePerMeter;
    }

    public void setPricePerMeter(BigDecimal pricePerMeter) {
        this.pricePerMeter = pricePerMeter;
    }
}
