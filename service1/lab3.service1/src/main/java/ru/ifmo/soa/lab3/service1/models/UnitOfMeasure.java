/**
 * Перечисление единиц измерения
 *
 * @author Anton Orekhovskiy
 * @version 1.0
 */
package ru.ifmo.soa.lab3.service1.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum UnitOfMeasure {
    CENTIMETERS,
    GRAMS,
    MILLIGRAMS;
}