package co.com.sofka.backendChallenge.domain.model;

import co.com.sofka.backendChallenge.infrastructure.persistence.entity.PurchaseProduct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseDTO {

    private Integer idPurchase;
    private LocalDateTime date;
    private String idType;
    private String clientName;
    private List<PurchaseProductDTO> products;

    public Integer getIdPurchase() {
        return idPurchase;
    }
    public void setIdPurchase(Integer idPurchase) {
        this.idPurchase = idPurchase;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<PurchaseProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<PurchaseProductDTO> products) {
        this.products = products;
    }
}
