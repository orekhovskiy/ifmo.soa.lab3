/**
 * Класс местонохождения
 *
 * @author Anton Orekhovskiy
 * @version 1.0
 */
package models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Location {
    /**
     * Поле x
     */
    private float x;

    /**
     * Поле y
     * Поле не может быть null
     */
    private Double y;

    /**
     * Поле z
     */
    private double z;

    public Location() {
    }

    public Location(float x, Double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
