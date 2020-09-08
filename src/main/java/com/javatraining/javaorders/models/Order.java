package com.javatraining.javaorders.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    //#region fields | DB modeling | constructors

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long ordernum;

    private double ordamount;
    private double advanceamount;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    private Customer customer;

    private String orderdescription;

    public Order(){

    }

    public Order(double ordamount, double advanceamount, String orderdescription) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.orderdescription = orderdescription;
    }
    //#endregion
    ///#region getters / setters
    public long getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(long ordernum) {
        this.ordernum = ordernum;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        this.advanceamount = advanceamount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    //#endregion
}
