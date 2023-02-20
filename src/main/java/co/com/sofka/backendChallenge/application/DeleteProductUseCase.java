package co.com.sofka.backendChallenge.application;

import co.com.sofka.backendChallenge.infrastructure.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DeleteProductUseCase implements Function<Integer, Boolean> {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Boolean apply(Integer idProduct) {
        return productRepository.findById(idProduct).map(product -> {
            productRepository.deleteById(idProduct);
            return true;
        }).orElse(false);
    }
}
