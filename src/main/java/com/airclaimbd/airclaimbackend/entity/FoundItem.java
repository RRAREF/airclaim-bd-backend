package com.airclaimbd.airclaimbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "found_items")
public class FoundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airportName;
    private String itemName;

    @Column(length = 1000)
    private String itemDescription;

    private String bagTagNumber;
    private String ticketNumber;
    private String dateFound;
    private String status = "Pending";

    public FoundItem() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getAirportName() { return airportName; }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getItemName() { return itemName; }

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

    public String getDateFound() {
        return dateFound;
    }

    public void setDateFound(String dateFound) {
        this.dateFound = dateFound;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}