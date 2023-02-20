package co.com.sofka.backendChallenge.infrastructure.persistence.repository;

import co.com.sofka.backendChallenge.infrastructure.persistence.entity.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PurchaseRepository extends CrudRepository<Purchase,Integer> {

    List<Purchase> findAll();
}
