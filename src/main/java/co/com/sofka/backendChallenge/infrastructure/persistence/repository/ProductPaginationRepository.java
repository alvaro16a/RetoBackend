package co.com.sofka.backendChallenge.infrastructure.persistence.repository;

import co.com.sofka.backendChallenge.infrastructure.persistence.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPaginationRepository extends PagingAndSortingRepository<Product,Integer>  {
}
