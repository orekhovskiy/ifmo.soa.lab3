/**
 * Класс продукта
 *
 * @author Anton Orekhovskiy
 * @version 1.0
 */
package ru.ifmo.soa.lab3.service1.models;

import ru.ifmo.soa.lab3.service1.adapters.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Product {
    /**
     * Поле идентификатора
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    private Long id;

    /**
     * Поле имени
     * Поле не может быть null
     * Строка не может быть пустой
     */
    private String name;

    /**
     * Поле координат
     *
     * @see Coordinates
     * Поле не может быть null
     */
    private Coordinates coordinates;

    /**
     * Поле даты создания
     * Поле не может быть null
     * Значение этого поля должно генерироваться автоматически
     */
    private LocalDateTime creationDate;

    /**
     * Поле цены
     * Поле может быть null
     * Значение поля должно быть больше 0
     */
    private Integer price;

    /**
     * Поле номера части
     * Длина строки должна быть не меньше 27
     * Значение этого поля должно быть уникальным
     * Поле может быть null
     */
    private String partNumber;

    /**
     * Поле цены производителя
     */
    private double manufactureCost;

    /**
     * Поле еденицы измерения
     *
     * @see UnitOfMeasure
     * Поле может быть null
     */
    private String unitOfMeasure;

    /**
     * Поле владельца
     *
     * @see ru.ifmo.soa.lab3.service1.models.Person
     * Поле может быть null
     */
    private ru.ifmo.soa.lab3.service1.models.Person owner;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public double getManufactureCost() {
        return manufactureCost;
    }

    public void setManufactureCost(double manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public ru.ifmo.soa.lab3.service1.models.Person getOwner() {
        return owner;
    }

    public void setOwner(ru.ifmo.soa.lab3.service1.models.Person owner) {
        this.owner = owner;
    }
}