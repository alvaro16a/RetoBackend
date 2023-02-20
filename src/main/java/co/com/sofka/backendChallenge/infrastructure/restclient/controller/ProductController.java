package co.com.sofka.backendChallenge.infrastructure.restclient.controller;

import co.com.sofka.backendChallenge.application.*;
import co.com.sofka.backendChallenge.domain.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private AddProductUseCase addProductUseCase;
    @Autowired
    private DeleteProductUseCase deleteProductUseCase;
    @Autowired
    private UpdateProductUseCase updateProductUseCase;
    @Autowired
    private GetProductsUseCase getProductsUseCase;
    @Autowired
    private GetProductUseCase getProductUseCase;


    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product) {
        return new ResponseEntity<>(addProductUseCase.apply(product), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product) {
        if(updateProductUseCase.apply(product)){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int idProduct){
        if(deleteProductUseCase.apply(idProduct)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<ProductDTO>> get(@PathVariable("page") int page, @PathVariable("size") int size){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(getProductsUseCase.apply(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProducto(@PathVariable("id") int idProducto){
        return getProductUseCase.apply(idProducto).map(productDTO -> new ResponseEntity<>(productDTO,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
