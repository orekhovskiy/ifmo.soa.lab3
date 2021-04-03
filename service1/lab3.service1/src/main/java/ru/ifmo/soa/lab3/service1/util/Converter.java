package ru.ifmo.soa.lab3.service1.util;

import ru.ifmo.soa.lab3.service1.entities.ProductsEntity;
import ru.ifmo.soa.lab3.service1.exceptions.OperationException;
import ru.ifmo.soa.lab3.service1.exceptions.WrongArgumentException;
import ru.ifmo.soa.lab3.service1.models.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Converter {
    public static ProductsEntity modelToEntity(Product model) {
        ProductsEntity entity = new ProductsEntity();

        if (model.getId() != null) entity.setId(model.getId());
        entity.setName(model.getName());

        entity.setCoordinatesx(model.getCoordinates().getX());
        entity.setCoordinatesy(model.getCoordinates().getY());

        entity.setCreationdate(Timestamp.valueOf(model.getCreationDate()));
        if (model.getPrice() != null) entity.setPrice(model.getPrice());
        if (model.getPartNumber() != null) entity.setPartnumber(model.getPartNumber());
        entity.setManufacturecost(model.getManufactureCost());
        if (model.getUnitOfMeasure() != null) entity.setUnitofmeasure(model.getUnitOfMeasure());

        if (model.getOwner() != null) {
            entity.setOwnername(model.getOwner().getName());
            if (model.getOwner().getWeight() != null) entity.setOwnerweight(model.getOwner().getWeight());

            entity.setOwnernationality(model.getOwner().getNationality().name());
            entity.setOwnerlocationx(model.getOwner().getLocation().getX());
            entity.setOwnerlocationy(model.getOwner().getLocation().getY());
            entity.setOwnerlocationz(model.getOwner().getLocation().getZ());
        }
        return entity;
    }

    public static Product entityToModel(ProductsEntity entity) {
        Product model = new Product();
        Coordinates coordinates = new Coordinates();
        Person person = new Person();
        Location location = new Location();
        int personNullFieldsAmount = 0;

        if (entity.getOwnerlocationx() != null) {
            location.setX(entity.getOwnerlocationx());
        } else {
            personNullFieldsAmount++;
        }
        if (entity.getOwnerlocationy() != null) {
            location.setY(entity.getOwnerlocationy());
        } else {
            personNullFieldsAmount++;
        }
        if (entity.getOwnerlocationz() != null) {
            location.setZ(entity.getOwnerlocationz());
        } else {
            personNullFieldsAmount++;
        }
        if (entity.getOwnername() != null) {
            person.setName(entity.getOwnername());
        } else {
            personNullFieldsAmount++;
        }
        if (entity.getOwnerweight() != null) {
            person.setWeight(entity.getOwnerweight());
        } else {
            personNullFieldsAmount++;
        }
        if (entity.getOwnernationality() != null) {
            person.setNationality(Country.valueOf(entity.getOwnernationality()));
        } else {
            personNullFieldsAmount++;
        }
        person.setLocation(location);
        if (entity.getCoordinatesx() != null) coordinates.setX(entity.getCoordinatesx());
        if (entity.getCoordinatesy() != null) coordinates.setY(entity.getCoordinatesy());
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCoordinates(coordinates);
        model.setCreationDate(entity.getCreationdate().toLocalDateTime());
        if (entity.getPrice() != null) model.setPrice(entity.getPrice());
        if (entity.getPartnumber() != null) model.setPartNumber(entity.getPartnumber());
        model.setManufactureCost(entity.getManufacturecost());
        if (entity.getUnitofmeasure() != null) model.setUnitOfMeasure(entity.getUnitofmeasure());
        if (personNullFieldsAmount < 6) model.setOwner(person);

        return model;
    }

    public static <ModelType> ModelType xmlReaderToModel(BufferedReader reader, Class<ModelType> clazz) throws JAXBException {
        JAXBContext context;
        Unmarshaller unmarshaller;

        context = JAXBContext.newInstance(clazz);
        unmarshaller = context.createUnmarshaller();

        ModelType model = (ModelType) unmarshaller.unmarshal(reader);
        return model;
    }

    public static <ModelType> void modelToXmlWriter(ModelType model, Writer writer, Class<ModelType> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(model, writer);
    }

    public static List<Predicate> queryStringToPredicates(Map<String, String> filterMap, CriteriaBuilder cb, Root<ProductsEntity> root)
            throws OperationException {
        if (filterMap == null || filterMap.isEmpty()) return new ArrayList<Predicate>();
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (Map.Entry<String, String> entry : filterMap.entrySet()) {
            String lhs = null;
            try {
                lhs = entry.getKey().toLowerCase(Locale.ROOT).replace("-", "");
                String rhs = java.net.URLDecoder.decode(entry.getValue(), StandardCharsets.UTF_8.name());
                if (!lhs.equals("pagecapacity") && !lhs.equals("pagenumber") && !lhs.equals("sort") && !lhs.equals("creationdate")) {
                    if (rhs.equals("$null")) {
                        predicates.add(cb.isNull(root.get(lhs)));
                    } else {
                        Validator.validateEnum(lhs, rhs);
                        predicates.add(cb.equal(root.get(lhs), rhs));
                    }
                }
            } catch (WrongArgumentException e) {
                throw new OperationException(e.getMessage());
            } catch (UnsupportedEncodingException e) {
                throw new OperationException(ExceptionsUtil.getDecodeException());
            } catch (Exception e) {
                throw new OperationException(ExceptionsUtil.getInvalidFilterArgumentException(lhs));
            }
        }
        return predicates;
    }
}
