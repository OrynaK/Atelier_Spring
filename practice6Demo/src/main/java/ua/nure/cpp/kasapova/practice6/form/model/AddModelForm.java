package ua.nure.cpp.kasapova.practice6.form.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddModelForm {
    @NotNull
    @Size(min=2, max=50)
    private String name;
    @NotNull
    @Min(1)
    private int proposedFabric;
    @NotNull
    @Min((long)0.1)
    private BigDecimal tailoringPrice;
    @NotNull
    @Min((long)0.1)
    private BigDecimal consumption;

    public AddModelForm() {
    }

    public AddModelForm(String name, int proposedFabric, BigDecimal tailoringPrice, BigDecimal consumption) {
        this.name = name;
        this.proposedFabric = proposedFabric;
        this.tailoringPrice = tailoringPrice;
        this.consumption = consumption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProposedFabric() {
        return proposedFabric;
    }

    public void setProposedFabric(int proposedFabric) {
        this.proposedFabric = proposedFabric;
    }

    public BigDecimal getTailoringPrice() {
        return tailoringPrice;
    }

    public void setTailoringPrice(BigDecimal tailoringPrice) {
        this.tailoringPrice = tailoringPrice;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }
}
