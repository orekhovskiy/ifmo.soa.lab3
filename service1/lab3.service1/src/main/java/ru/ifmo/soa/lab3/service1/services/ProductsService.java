package ru.ifmo.soa.lab3.service1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ifmo.soa.lab3.service1.DAO.DAOImpl;
import ru.ifmo.soa.lab3.service1.entities.ProductsEntity;
import ru.ifmo.soa.lab3.service1.exceptions.NotFoundException;
import ru.ifmo.soa.lab3.service1.exceptions.OperationException;
import ru.ifmo.soa.lab3.service1.exceptions.WrongArgumentException;
import ru.ifmo.soa.lab3.service1.models.Person;
import ru.ifmo.soa.lab3.service1.models.Product;
import ru.ifmo.soa.lab3.service1.models.ProductsList;
import ru.ifmo.soa.lab3.service1.util.Converter;
import ru.ifmo.soa.lab3.service1.util.ExceptionsUtil;
import ru.ifmo.soa.lab3.service1.util.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductsService {

    @Autowired
    private DAOImpl dao;

    public Product getProduct(String id)
            throws NotFoundException, OperationException {
        try {
            long idLong = Long.parseLong(id);
            ProductsEntity entity = dao.getProductById(idLong);
            return Converter.entityToModel(entity);
        } catch (NumberFormatException numberFormatException) {
            throw new OperationException(ExceptionsUtil.getWrongTypeException("Id", "int"));
        }
    }

    public double getAverageManufactureCost() {
        return dao.getAverageManufactureCost();
    }

    public ProductsList getProducts(List<String> sort, String pageCapacityStr, String pageNumberStr, Map<String, String> requestParams)
            throws OperationException {
        String[] sortBy = sort == null
                ? new String[]{}
                : sort.toArray(new String[0]);
        Integer pageNumber = null, pageCapacity = null;
        try {
            if (pageNumberStr != null) {
                pageNumber = Integer.valueOf(pageNumberStr);
            }
        } catch (NumberFormatException e) {
            throw new OperationException(ExceptionsUtil.getWrongTypeException("page-number", "Integer"));
        }
        try {
            if (pageCapacityStr != null) {
                pageCapacity = Integer.valueOf(pageCapacityStr);
            }
        } catch (NumberFormatException e) {
            throw new OperationException(ExceptionsUtil.getWrongTypeException("page-capacity", "Integer"));
        }
        if (pageNumber != null && pageNumber < 1)
            throw new OperationException(ExceptionsUtil.getShouldBeGreaterException("page-number", "0"));
        if (pageCapacity != null && pageCapacity < 1)
            throw new OperationException(ExceptionsUtil.getShouldBeGreaterException("page-capacity", "0"));
        List<ProductsEntity> entities = dao.getProducts(requestParams, pageNumber, pageCapacity, sortBy);
        ProductsList productsList = new ProductsList();
        List<Product> list = new ArrayList<Product>();
        for (ProductsEntity entity : entities) {
            list.add(Converter.entityToModel(entity));
        }
        productsList.setProducts(list);
        return productsList;
    }

    public void postProduct(Product product)
            throws WrongArgumentException {
        product.setCreationDate(LocalDateTime.now());
        Validator.validateProduct(product, null);
        ProductsEntity productsEntity = Converter.modelToEntity(product);
        dao.addProduct(productsEntity);
    }

    public void deleteProduct(String idStr)
            throws OperationException, NotFoundException {
        try {
            long id = Long.parseLong(idStr);
            dao.deleteProductById(id);
        } catch (NumberFormatException numberFormatException) {
            throw new OperationException(ExceptionsUtil.getWrongTypeException("Id", "int"));
        }
    }

    public void deleteProductByOwner(Person person)
            throws WrongArgumentException, NotFoundException {
        Validator.validatePerson(person);
        dao.deleteAllProductWithPerson(person);
    }

    public void deleteProductByPrice(String priceStr)
            throws OperationException, NotFoundException {
        try {
            Integer price = priceStr.equals("")
                    ? null
                    : Integer.valueOf(priceStr);
            dao.deleteProductWithPrice(price);
        } catch (NumberFormatException numberFormatException) {
            throw new OperationException(ExceptionsUtil.getWrongTypeException("Price", "Integer"));
        }
    }

    public void putProduct(String idStr, Product product)
            throws WrongArgumentException, NotFoundException {
        long id;
        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            throw new WrongArgumentException(ExceptionsUtil.getWrongTypeException("Id", "Long"));
        }
        product.setId(id);
        product.setCreationDate(LocalDateTime.now());
        Validator.validateProduct(product, id);
        DAOImpl dao = new DAOImpl();
        dao.getProductById(id);
        ProductsEntity productsEntity = Converter.modelToEntity(product);
        dao.updateProduct(productsEntity);
    }
}
