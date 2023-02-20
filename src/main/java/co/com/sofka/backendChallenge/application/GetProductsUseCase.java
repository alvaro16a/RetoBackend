package co.com.sofka.backendChallenge.application;

import co.com.sofka.backendChallenge.domain.model.ProductDTO;
import co.com.sofka.backendChallenge.infrastructure.persistence.mapper.Mapper;
import co.com.sofka.backendChallenge.infrastructure.persistence.repository.ProductPaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class GetProductsUseCase implements Function<Pageable, Page<ProductDTO>> {
    @Autowired
    private ProductPaginationRepository productPaginationRepository;
    @Autowired
    private Mapper mapper;

    @Override
    public Page<ProductDTO> apply(Pageable pageable) {
        return productPaginationRepository.findAll(pageable).map(product ->
                mapper.toProductDTO().apply(product));
    }
}
