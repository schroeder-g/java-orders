package com.javatraining.javaorders.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "agents")
public class Agent
{

    //#region fields/constructors
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long agentcode;

    private String agentname;
    private String workingarea;
    private double commission;
    private String phone;
    private String country;

    //One to many join with customers
    @OneToMany(mappedBy = "agent",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Customer> customers = new ArrayList<>();

    //connect agents with payments
    @ManyToMany
            @JoinTable(name = "orderspayments",
                    joinColumns = @JoinColumn(name="agentid"),
                    inverseJoinColumns = @JoinColumn( name = "paymentid"))
    private Set<Payment> payments = new HashSet<>();

    public Agent(){

    }

    public Agent(String agentname, String workingarea, double commission, String phone, String country) {
        this.agentname = agentname;
        this.workingarea = workingarea;
        this.commission = commission;
        this.phone = phone;
        this.country = country;
    }
    //#endregion

    //#region getters / setters

    public long getAgentcode() {
        return agentcode;
    }

    public void setAgentcode(long agentid) {
        this.agentcode = agentid;
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public String getWorkingarea() {
        return workingarea;
    }

    public void setWorkingarea(String workingarea) {
        this.workingarea = workingarea;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    //#endregion

}
