/**
 * Класс координат
 *
 * @author Anton Orekhovskiy
 * @version 1.0
 */
package ru.ifmo.soa.lab3.service1.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Coordinates {
    /**
     * Поле x
     * Значение поля должно быть больше -965
     */
    private int x;

    /**
     * Поле y
     */
    private long y;

    public Coordinates() {
    }

    public Coordinates(int id, int x, long y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }


}
