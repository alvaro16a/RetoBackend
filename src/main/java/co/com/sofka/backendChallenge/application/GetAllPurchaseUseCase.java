package co.com.sofka.backendChallenge.application;

import co.com.sofka.backendChallenge.domain.model.PurchaseDTO;
import co.com.sofka.backendChallenge.infrastructure.persistence.mapper.Mapper;
import co.com.sofka.backendChallenge.infrastructure.persistence.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class GetAllPurchaseUseCase implements Supplier<List<PurchaseDTO>> {

    private final PurchaseRepository purchaseRepository;
    private final Mapper mapper;

    public GetAllPurchaseUseCase(PurchaseRepository purchaseRepository, Mapper mapper) {
        this.purchaseRepository = purchaseRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PurchaseDTO> get() {
        return purchaseRepository.findAll().stream().map(purchase -> mapper.toPurchaseDTO().apply(purchase)).collect(Collectors.toList());
    }
}
