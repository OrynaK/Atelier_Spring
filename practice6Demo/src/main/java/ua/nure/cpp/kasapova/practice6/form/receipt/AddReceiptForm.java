package ua.nure.cpp.kasapova.practice6.form.receipt;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Date;

public class AddReceiptForm {
    @NotNull
    @Min(1)
    private int clientId;
    @NotNull
    @Min(value = 1)
    private int modelId;
    @NotNull
    @Min(1)
    private int fabricId;
    @NotNull
    @Min(1)
    private int cutterId;
    @NotNull
    private Date dateAcceptance;
    @NotNull
    private Date dateFitting;
    @NotNull
    private Date dueDate;
    @NotNull
    private boolean status;

    public AddReceiptForm() {
    }

    public AddReceiptForm(int clientId, int modelId, int fabricId, int cutterId, Date dateAcceptance, Date dateFitting, Date dueDate, boolean status) {
        this.clientId = clientId;
        this.modelId = modelId;
        this.fabricId = fabricId;
        this.cutterId = cutterId;
        this.dateAcceptance = dateAcceptance;
        this.dateFitting = dateFitting;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getFabricId() {
        return fabricId;
    }

    public void setFabricId(int fabricId) {
        this.fabricId = fabricId;
    }

    public int getCutterId() {
        return cutterId;
    }

    public void setCutterId(int cutterId) {
        this.cutterId = cutterId;
    }

    public Date getDateAcceptance() {
        return dateAcceptance;
    }

    public void setDateAcceptance(Date dateAcceptance) {
        this.dateAcceptance = dateAcceptance;
    }

    public Date getDateFitting() {
        return dateFitting;
    }

    public void setDateFitting(Date dateFitting) {
        this.dateFitting = dateFitting;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
