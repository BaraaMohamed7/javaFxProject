package com.javafxproject;

public class Ticket {
    private String name;
    private String nationality;
    private String email;
    private String traveldist;
    private String traveldate;
    private int passportnumber;
    private int ticketid;

    public Ticket(String name, String nationality, String email, String traveldist, String traveldate, int passportnumber, int ticketid) {
        this.name = name;
        this.nationality = nationality;
        this.email = email;
        this.traveldist = traveldist;
        this.traveldate = traveldate;
        this.passportnumber = passportnumber;
        this.ticketid = ticketid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTraveldist() {
        return traveldist;
    }

    public void setTraveldist(String traveldist) {
        this.traveldist = traveldist;
    }

    public String getTraveldate() {
        return traveldate;
    }

    public void setTraveldate(String traveldate) {
        this.traveldate = traveldate;
    }

    public int getPassportnumber() {
        return passportnumber;
    }

    public void setPassportnumber(int passportnumber) {
        this.passportnumber = passportnumber;
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }
}
