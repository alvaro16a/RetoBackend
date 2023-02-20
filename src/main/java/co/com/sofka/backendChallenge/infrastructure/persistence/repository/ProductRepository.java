package co.com.sofka.backendChallenge.infrastructure.persistence.repository;

import co.com.sofka.backendChallenge.infrastructure.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    List<Product> findByEnabledOrderByNameAsc(Boolean enabled);
}
