package oop.bufihofa.classes;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Item {
    private String name = "?";
    private int price = 1;
    private double quantity = 1;
    private int id = 1000000000;
    private String unit = "?";
    private String dateString = "?";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

    LocalDate ldate = LocalDate.of(2000, 1, 1);
    //constructor
    public Item(){}
    public Item(String s){

    }
    public Item(String name, int id, int price, double quantity, String unit, String date) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.dateString = date;
        this.ldate = LocalDate.parse(date, formatter);
    }
    public Item(String name, int id, int price, double quantity, String unit, LocalDate date) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.ldate = date;
        this.dateString = date.format(formatter);
    }

    // getter and setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDate getDate() {
        return ldate;
    }
    public String getDateString(){
        return dateString;
    }
    public void setDate(LocalDate date) {
        this.ldate = date;
    }
}
