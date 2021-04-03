/**
 * Перечисление единиц измерения
 *
 * @author Anton Orekhovskiy
 * @version 1.0
 */
package models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum UnitOfMeasure {
    CENTIMETERS,
    GRAMS,
    MILLIGRAMS;
}