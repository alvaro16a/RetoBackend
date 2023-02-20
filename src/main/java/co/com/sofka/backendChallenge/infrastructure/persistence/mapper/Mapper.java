package co.com.sofka.backendChallenge.infrastructure.persistence.mapper;

import co.com.sofka.backendChallenge.domain.model.ProductDTO;
import co.com.sofka.backendChallenge.domain.model.PurchaseDTO;
import co.com.sofka.backendChallenge.domain.model.PurchaseProductDTO;
import co.com.sofka.backendChallenge.infrastructure.persistence.entity.Product;
import co.com.sofka.backendChallenge.infrastructure.persistence.entity.Purchase;
import co.com.sofka.backendChallenge.infrastructure.persistence.entity.PurchaseProduct;
import co.com.sofka.backendChallenge.infrastructure.persistence.entity.PurchaseProductPK;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public Function<ProductDTO, Product> toProduct() {
        return productDTO -> {
            Product product = new Product();
            product.setIdProduct(productDTO.getIdProduct());
            product.setName(productDTO.getName());
            product.setInInventory(productDTO.getInInventory());
            product.setEnabled(productDTO.getEnabled());
            product.setMin(productDTO.getMin());
            product.setMax(productDTO.getMax());
            return product;
        };
    }

    public Function<PurchaseProductDTO, PurchaseProduct> toPurchaseProduct() {
        return purchaseProductDTO -> {
            PurchaseProduct purchaseProduct= new PurchaseProduct();
            PurchaseProductPK purchaseProductPK = new PurchaseProductPK();
            purchaseProductPK.setIdProduct(purchaseProductDTO.getProductId());
            purchaseProduct.setQuantity(purchaseProductDTO.getQuantity());
            purchaseProduct.setId(purchaseProductPK);
            return purchaseProduct;
        };
    }

    public Function<PurchaseProduct, PurchaseProductDTO> toPurchaseProductDTO() {
        return purchaseProduct -> {
            PurchaseProductDTO purchaseProductDTO= new PurchaseProductDTO();

            purchaseProductDTO.setProductId(purchaseProduct.getId().getIdProduct());
            purchaseProductDTO.setQuantity(purchaseProduct.getQuantity());

            return purchaseProductDTO;
        };
    }


    public Function<Product, ProductDTO> toProductDTO() {
        return product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setIdProduct(product.getIdProduct());
            productDTO.setName(product.getName());
            productDTO.setInInventory(product.getInInventory());
            productDTO.setEnabled(product.getEnabled());
            productDTO.setMin(product.getMin());
            productDTO.setMax(product.getMax());
            return productDTO;
        };
    }

    public Function<PurchaseDTO, Purchase> toPurchase() {
        return purchaseDTO -> {
            Purchase purchase  = new Purchase();
            purchase.setIdPurchase(purchaseDTO.getIdPurchase());
            purchase.setDate(purchaseDTO.getDate());
            purchase.setIdType(purchaseDTO.getIdType());
            purchase.setClientName(purchaseDTO.getClientName());
            purchase.setProducts(purchaseDTO.getProducts().stream().map(purchaseProductDTO -> toPurchaseProduct().apply(purchaseProductDTO)).collect(Collectors.toList()));
            return purchase;
        };
    }

    public Function<Purchase, PurchaseDTO> toPurchaseDTO() {
        return purchase -> {
            PurchaseDTO purchaseDTO  = new PurchaseDTO();
            purchaseDTO.setIdPurchase(purchase.getIdPurchase());
            purchaseDTO.setDate(purchase.getDate());
            purchaseDTO.setIdType(purchase.getIdType());
            purchaseDTO.setClientName(purchase.getClientName());
            purchaseDTO.setProducts(purchase.getProducts().stream().map(purchaseProduct -> toPurchaseProductDTO().apply(purchaseProduct)).collect(Collectors.toList()));
            return purchaseDTO;
        };
    }



}
