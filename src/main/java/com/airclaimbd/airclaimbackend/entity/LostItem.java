package com.airclaimbd.airclaimbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lost_items")
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airportName;
    private String passengerName;
    private String email;
    private String phone;
    private String itemName;

    @Column(length = 1000)
    private String itemDescription;

    private String bagTagNumber;
    private String ticketNumber;
    private String dateLost;
    private String status = "Pending";

    public LostItem() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getAirportName() { return airportName; }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getBagTagNumber() {
        return bagTagNumber;
    }

    public void setBagTagNumber(String bagTagNumber) {
        this.bagTagNumber = bagTagNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getDateLost() {
        return dateLost;
    }

    public void setDateLost(String dateLost) {
        this.dateLost = dateLost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}