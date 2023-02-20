package co.com.sofka.backendChallenge.application;

import co.com.sofka.backendChallenge.domain.model.ProductDTO;
import co.com.sofka.backendChallenge.infrastructure.persistence.entity.Product;
import co.com.sofka.backendChallenge.infrastructure.persistence.mapper.Mapper;
import co.com.sofka.backendChallenge.infrastructure.persistence.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    private final  Mapper mapper = new Mapper();

    AddProductUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new AddProductUseCase(productRepository,mapper);
    }

    @Test
    void AddProductHappyPass() {

        ProductDTO productEsperado = new ProductDTO();
        productEsperado.setIdProduct(123);
        productEsperado.setEnabled(false);
        productEsperado.setName("prueba");
        productEsperado.setInInventory(50);
        productEsperado.setMin(1);
        productEsperado.setMax(5);

        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(mapper.toProduct().apply(productEsperado));

        var resultado =useCase.apply(productEsperado);

        Assertions.assertEquals(productEsperado.getIdProduct(),resultado.getIdProduct());
        Assertions.assertEquals(productEsperado.getInInventory(),resultado.getInInventory());
        Assertions.assertEquals(productEsperado.getEnabled(),resultado.getEnabled());
        Assertions.assertEquals(productEsperado.getName(),resultado.getName());
        Assertions.assertEquals(productEsperado.getMin(),resultado.getMin());
        Assertions.assertEquals(productEsperado.getMax(),resultado.getMax());
    }
}