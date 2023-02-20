package co.com.sofka.backendChallenge.application;

import co.com.sofka.backendChallenge.domain.model.ProductDTO;
import co.com.sofka.backendChallenge.infrastructure.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UpdateProductUseCase implements Function<ProductDTO, Boolean> {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AddProductUseCase addProductUseCase;

    @Override
    public Boolean apply(ProductDTO productDTO) {
        return productRepository.findById(productDTO.getIdProduct()).map(product -> {
                addProductUseCase.apply(productDTO);
                return true;
        }).orElse( false);
    }
}
