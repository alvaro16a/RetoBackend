package co.com.sofka.backendChallenge.application;

import co.com.sofka.backendChallenge.domain.model.ProductDTO;
import co.com.sofka.backendChallenge.infrastructure.persistence.mapper.Mapper;
import co.com.sofka.backendChallenge.infrastructure.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
@Service
public class GetProductUseCase implements Function<Integer, Optional<ProductDTO>> {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Mapper mapper;

    @Override
    public Optional<ProductDTO> apply(Integer id) {
        return productRepository.findById(id).map(product -> mapper.toProductDTO().apply(product));
    }
}
