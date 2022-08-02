package com.example.abasibiangakeadeyemi_comp304sec002_lab5_group4;

public class Laptop {
    private String brand;
    private String RAM;
    private String processor;
    private double cost;

    public Laptop(String brand, String RAM, String processor, double cost) {
        this.brand = brand;
        this.RAM = RAM;
        this.processor = processor;
        this.cost = cost;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


}
