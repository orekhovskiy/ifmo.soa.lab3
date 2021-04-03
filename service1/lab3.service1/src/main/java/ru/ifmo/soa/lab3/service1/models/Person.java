/**
 * Класс личности
 *
 * @author Anton Orekhovskiy
 * @version 1.0
 */
package ru.ifmo.soa.lab3.service1.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "owner")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Person {
    /**
     * Поле имени
     * Поле не может быть null
     * Строка не может быть пустой
     */
    private String name;

    /**
     * Поле веса
     * Поле может быть null
     * Значение поля должно быть больше 0
     */
    private Integer weight;

    /**
     * Поле национальности
     * @see Country
     * Поле не может быть null
     */
    private Country nationality;

    /**
     * Поле местоположения
     * @see Location
     * Поле не может быть null
     */
    private Location location;

    public Person() {
    }

    public Person(String name,
                  Integer weight,
                  Country nationality,
                  Location location) {
        this.name = name;
        this.weight = weight;
        this.nationality = nationality;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
