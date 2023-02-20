package co.com.sofka.backendChallenge.application;

import co.com.sofka.backendChallenge.domain.model.ProductDTO;
import co.com.sofka.backendChallenge.infrastructure.persistence.mapper.Mapper;
import co.com.sofka.backendChallenge.infrastructure.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AddProductUseCase implements Function<ProductDTO, ProductDTO> {


    private final   ProductRepository productRepository;
    private final Mapper mapper;

    public AddProductUseCase(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }


    @Override
    public ProductDTO apply(ProductDTO productDTO) {
        return mapper.toProductDTO().apply(
                productRepository.save(mapper.toProduct().apply(productDTO)));
    }
}
