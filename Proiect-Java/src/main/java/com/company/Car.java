package com.company;

public class Car {
    private String make;
    private String carModel;
    private int year;
    private double price;

    public Car(String make, String carModel, int year, double price) {
        this.make = make;
        this.carModel = carModel;
        this.year = year;
        this.price = price;
    }
    public Car(){}

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "make='" + make + '\'' +
                ", carModel='" + carModel + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
