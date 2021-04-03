package ru.ifmo.soa.lab3.service1.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "products", schema = "s225123", catalog = "studs")
public class ProductsEntity {
    private Long id;
    private String name;
    private Integer coordinatesx;
    private Long coordinatesy;
    private Timestamp creationdate;
    private Integer price;
    private String partnumber;
    private Double manufacturecost;
    private String unitofmeasure;
    private String ownername;
    private Integer ownerweight;
    private String ownernationality;
    private Float ownerlocationx;
    private Double ownerlocationy;
    private Double ownerlocationz;

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "coordinatesx", nullable = true)
    public Integer getCoordinatesx() {
        return coordinatesx;
    }

    public void setCoordinatesx(Integer coordinatesx) {
        this.coordinatesx = coordinatesx;
    }

    @Basic
    @Column(name = "coordinatesy", nullable = true)
    public Long getCoordinatesy() {
        return coordinatesy;
    }

    public void setCoordinatesy(Long coordinatesy) {
        this.coordinatesy = coordinatesy;
    }

    @Basic
    @Column(name = "creationdate", nullable = true)
    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "partnumber", nullable = true, length = -1)
    public String getPartnumber() {
        return partnumber;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    @Basic
    @Column(name = "manufacturecost", nullable = true, precision = 0)
    public Double getManufacturecost() {
        return manufacturecost;
    }

    public void setManufacturecost(Double manufacturecost) {
        this.manufacturecost = manufacturecost;
    }

    @Basic
    @Column(name = "unitofmeasure", nullable = true, length = -1)
    public String getUnitofmeasure() {
        return unitofmeasure;
    }

    public void setUnitofmeasure(String unitofmeasure) {
        this.unitofmeasure = unitofmeasure;
    }

    @Basic
    @Column(name = "ownername", nullable = true, length = -1)
    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    @Basic
    @Column(name = "ownerweight", nullable = true)
    public Integer getOwnerweight() {
        return ownerweight;
    }

    public void setOwnerweight(Integer ownerweight) {
        this.ownerweight = ownerweight;
    }

    @Basic
    @Column(name = "ownernationality", nullable = true, length = -1)
    public String getOwnernationality() {
        return ownernationality;
    }

    public void setOwnernationality(String ownernationality) {
        this.ownernationality = ownernationality;
    }

    @Basic
    @Column(name = "ownerlocationx", nullable = true, precision = 0)
    public Float getOwnerlocationx() {
        return ownerlocationx;
    }

    public void setOwnerlocationx(Float ownerlocationx) {
        this.ownerlocationx = ownerlocationx;
    }

    @Basic
    @Column(name = "ownerlocationy", nullable = true, precision = 0)
    public Double getOwnerlocationy() {
        return ownerlocationy;
    }

    public void setOwnerlocationy(Double ownerlocationy) {
        this.ownerlocationy = ownerlocationy;
    }

    @Basic
    @Column(name = "ownerlocationz", nullable = true, precision = 0)
    public Double getOwnerlocationz() {
        return ownerlocationz;
    }

    public void setOwnerlocationz(Double ownerlocationz) {
        this.ownerlocationz = ownerlocationz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductsEntity that = (ProductsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(coordinatesx, that.coordinatesx) && Objects.equals(coordinatesy, that.coordinatesy) && Objects.equals(creationdate, that.creationdate) && Objects.equals(price, that.price) && Objects.equals(partnumber, that.partnumber) && Objects.equals(manufacturecost, that.manufacturecost) && Objects.equals(unitofmeasure, that.unitofmeasure) && Objects.equals(ownername, that.ownername) && Objects.equals(ownerweight, that.ownerweight) && Objects.equals(ownernationality, that.ownernationality) && Objects.equals(ownerlocationx, that.ownerlocationx) && Objects.equals(ownerlocationy, that.ownerlocationy) && Objects.equals(ownerlocationz, that.ownerlocationz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinatesx, coordinatesy, creationdate, price, partnumber, manufacturecost, unitofmeasure, ownername, ownerweight, ownernationality, ownerlocationx, ownerlocationy, ownerlocationz);
    }
}
