package co.com.sofka.backendChallenge.application;

import co.com.sofka.backendChallenge.domain.model.ProductDTO;
import co.com.sofka.backendChallenge.domain.model.PurchaseProductDTO;
import co.com.sofka.backendChallenge.domain.valueObject.CheckReport;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
public class CheckPossibleSaleUseCase implements Function<PurchaseProductDTO, CheckReport> {

   private final GetProductUseCase getProductUseCase;

    public CheckPossibleSaleUseCase(GetProductUseCase getProductUseCase) {
        this.getProductUseCase = getProductUseCase;
    }

    @Override
    public CheckReport apply(PurchaseProductDTO purchaseProductDTO) {
        Optional<ProductDTO> productDTO = getProductUseCase.apply(purchaseProductDTO.getProductId());
        if(productDTO.isPresent()){
            if(productDTO.get().getEnabled()){
                if(purchaseProductDTO.getQuantity() <= productDTO.get().getInInventory()){
                    if(purchaseProductDTO.getQuantity() >= productDTO.get().getMin()){
                        if(purchaseProductDTO.getQuantity() <= productDTO.get().getMax()){
                            return new CheckReport(true,"del producto !!"+productDTO.get().getName()+"!! Se pueden vender " + purchaseProductDTO.getQuantity() +" Unidades sin inconveniente");
                        }else{
                            return new CheckReport(false,"del producto !!"+productDTO.get().getName()+"!! Se pueden vender maximo: " + productDTO.get().getMax() +" Unidades");
                        }
                    }else{
                        return new CheckReport(false,"del producto !!"+productDTO.get().getName()+"!! Se pueden vender minimos: " + productDTO.get().getMin() +" Unidades");
                    }

                }else{
                    return new CheckReport(false,"solo hay en inventario: "+productDTO.get().getInInventory()+" unidades del producto: !!" + productDTO.get().getName() +"!! por favor solicite menos cantidad");
                }

            }else{
                return new CheckReport(false,"El producto: !!"+ productDTO.get().getName() +"!! no esta disponible en este momento");
            }

        }else {
            return new CheckReport(false,"No existe el producto con id: " + purchaseProductDTO.getProductId());
        }
    }
}
