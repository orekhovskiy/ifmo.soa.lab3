package ru.ifmo.soa.lab3.service1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.soa.lab3.service1.exceptions.NotFoundException;
import ru.ifmo.soa.lab3.service1.exceptions.OperationException;
import ru.ifmo.soa.lab3.service1.exceptions.WrongArgumentException;
import ru.ifmo.soa.lab3.service1.models.Person;
import ru.ifmo.soa.lab3.service1.models.Product;
import ru.ifmo.soa.lab3.service1.models.ProductsList;
import ru.ifmo.soa.lab3.service1.services.ProductsService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    // получение по ид: GET /products/{id}
    @GetMapping(value = "/{id}", produces = "application/xml")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Product getProduct(@PathVariable String id)
            throws NotFoundException, OperationException {
        return service.getProduct(id);
    }

    // расчет средней цены производителя : GET /products/manufacture-cost/average
    @GetMapping(value = "/manufacture-cost/average")
    @ResponseStatus(value = HttpStatus.OK)
    public double getAverageManufactureCost() {
        return service.getAverageManufactureCost();
    }

    // получение всех: GET /products
    @GetMapping(value = {"", "/"}, produces = "application/xml")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ProductsList getProducts(@RequestParam(required = false, name = "sort") List<String> sort,
                                    @RequestParam(required = false, name = "page-capacity") String pageCapacity,
                                    @RequestParam(required = false, name = "page-number") String pageNumber,
                                    @RequestParam Map<String, String> requestParams)
            throws OperationException {
        return service.getProducts(sort, pageCapacity, pageNumber, requestParams);
    }

    // добавление нового: POST /products
    @PostMapping(value = {"", "/"})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void postProduct(@RequestBody Product product)
            throws WrongArgumentException {
        service.postProduct(product);
    }

    // удаление: DELETE /products/{id}
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id)
            throws OperationException, NotFoundException {
        service.deleteProduct(id);
    }

    // удаление всех где есть овнер: DELETE /products/owner (в боди объект)
    @DeleteMapping(value = "/owner")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProductsByOwner(@RequestBody Person person)
            throws WrongArgumentException, NotFoundException {
        service.deleteProductByOwner(person);
    }

    // удалить любой где есть прайс: DELETE /products/price/{price}
    @DeleteMapping(value = "/price/{price}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProductByPrice(@PathVariable String price)
            throws OperationException, NotFoundException {
        service.deleteProductByPrice(price);
    }

    // обновление: PUT /products/{id} (в боди объект)
    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putProduct(@PathVariable String id, @RequestBody Product product)
            throws NotFoundException, WrongArgumentException {
        service.putProduct(id, product);
    }

    @ExceptionHandler({OperationException.class, WrongArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String badRequest(Exception exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFound(Exception exception) {
        return exception.getMessage();
    }
}