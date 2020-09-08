package com.javatraining.javaorders.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "payments")
public class Payment {
    //#region fields/constructors
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long paymentid;

    @Column(nullable = false)
    private  String type;

    //connecting payments to customer (already set up inside of agent)
    @ManyToMany(mappedBy = "payments")
    private Set<Agent> agents = new HashSet<>();

    public Payment(){
    }

    public Payment(String type) {
        this.type = type;
    }
    //#endregion
    //#region getters/setters
    public long getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(long paymentid) {
        this.paymentid = paymentid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Agent> getAgents() {
        return agents;
    }

    public void setAgents(Set<Agent> agents) {
        this.agents = agents;
    }

    //#endregion
}
