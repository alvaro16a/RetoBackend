package co.com.sofka.backendChallenge.infrastructure.restclient.controller;

import co.com.sofka.backendChallenge.application.GetAllPurchaseUseCase;
import co.com.sofka.backendChallenge.application.PurchaseProductUseCase;
import co.com.sofka.backendChallenge.domain.model.PurchaseDTO;
import co.com.sofka.backendChallenge.domain.valueObject.CheckReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseProductController {

    private final PurchaseProductUseCase purchaseProductUseCase;
    private final GetAllPurchaseUseCase getAllPurchaseUseCase;


    public PurchaseProductController(PurchaseProductUseCase purchaseProductUseCase, GetAllPurchaseUseCase getAllPurchaseUseCase) {
        this.purchaseProductUseCase = purchaseProductUseCase;
        this.getAllPurchaseUseCase = getAllPurchaseUseCase;
    }

    @PostMapping
    public ResponseEntity<CheckReport> verificar(@RequestBody PurchaseDTO purchaseDTO) {
        return new ResponseEntity<>(purchaseProductUseCase.apply(purchaseDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAll(){
        return new ResponseEntity<>(getAllPurchaseUseCase.get(), HttpStatus.OK);
    }

}
