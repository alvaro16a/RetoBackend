package co.com.sofka.backendChallenge.application;

import co.com.sofka.backendChallenge.domain.model.ProductDTO;
import co.com.sofka.backendChallenge.domain.model.PurchaseDTO;
import co.com.sofka.backendChallenge.domain.model.PurchaseProductDTO;
import co.com.sofka.backendChallenge.domain.valueObject.CheckReport;
import co.com.sofka.backendChallenge.infrastructure.persistence.entity.Purchase;
import co.com.sofka.backendChallenge.infrastructure.persistence.mapper.Mapper;
import co.com.sofka.backendChallenge.infrastructure.persistence.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PurchaseProductUseCase implements Function<PurchaseDTO,CheckReport> {

    private final PurchaseRepository purchaseRepository;
    private final CheckPossibleSaleUseCase checkPossibleSaleUseCase;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final Mapper mapper;



    public PurchaseProductUseCase(PurchaseRepository purchaseRepository, CheckPossibleSaleUseCase checkPossibleSaleUseCase, GetProductUseCase getProductUseCase, UpdateProductUseCase updateProductUseCase, Mapper mapper) {
        this.purchaseRepository = purchaseRepository;
        this.checkPossibleSaleUseCase = checkPossibleSaleUseCase;
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.mapper = mapper;
    }

    @Override
    public CheckReport apply(PurchaseDTO purchaseDTO) {

        List<PurchaseProductDTO> products = purchaseDTO.getProducts();
        products.stream().noneMatch(product -> checkPossibleSaleUseCase.apply(product).getResult());
        CheckReport finalReport = new CheckReport(true,"");
        products.forEach(product -> {
            CheckReport checkReport=checkPossibleSaleUseCase.apply(product);
            if(!checkReport.getResult()){
                finalReport.setResult(false);
                finalReport.setDetails(finalReport.getDetails() + checkReport.getDetails() + " ||| ");
            }
        });

        if(finalReport.getResult()){
            Purchase purchase = mapper.toPurchase().apply(purchaseDTO);
            purchase.getProducts().forEach(product -> product.setPurchase(purchase));
            purchaseRepository.save(purchase);
            products.forEach(producto -> {
                Optional<ProductDTO> productoDTO =  getProductUseCase.apply(producto.getProductId());
                productoDTO.get().setInInventory(productoDTO.get().getInInventory() - producto.getQuantity());
                updateProductUseCase.apply(productoDTO.get());
            });

            finalReport.setDetails("Se guardo la compra con exito");
        }
        return finalReport;
    }
}
