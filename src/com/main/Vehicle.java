package com.main;

import java.util.Vector;

public class Vehicle implements Comparable<Vehicle>
{
    private String brand;
    private String model;
    private ItemCondition condition;
    private double price;
    private int yearOfProduction;
    private double mileage;
    private double engineCapacity;
    private int amount;

    // brand

//    void setBrand(String newBrand) { brand = newBrand;}
//    String getBrand() { return brand;}
//
//    // model
//
//    void setModel(String newModel) { model = newModel; }
//    String getModel() { return model;}
//
//    // itemCondition
//
//    void setItemCondition(ItemCondition newItemCondition) { itemCondition = newItemCondition;}
//    ItemCondition getItemCondition() { return itemCondition;}
//
//    // price
//
//    void setPrice(double newPrice) { price = newPrice; }
//    double getPrice(){ return price; }
//
//    // yearOfProduction
//
//    void setYearOfProduction(int newYearOfProduction) { yearOfProduction = newYearOfProduction; }
//    int getYearOfProduction() { return yearOfProduction; }
//
//    // mileage
//
//    void setMileage(double newMileage) { mileage = newMileage;}
//    double getMileage() { return mileage;}
//
//    // engineCapacity
//
//    void setEngineCapacity(double newEngineCapacity) { engineCapacity = newEngineCapacity;}
//    double getEngineCapacity() { return engineCapacity;}
//
//    // amountOfCars
//
//    void setAmountOfCars(int newAmountOfCars) { amountOfCars = newAmountOfCars;}
//    int getAmountOfCars() {return amountOfCars;}

    public Vehicle(String brand, String model, ItemCondition condition, double price, int yearOfProduction, double mileage, double engineCapacity, int amount) {
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.mileage = mileage;
        this.engineCapacity = engineCapacity;
        this.amount = amount;
    }

    public void print(){
        System.out.println("Brand: "+brand+" Model: "+model+" Condition: "+condition+" Price: "+price+" Year of production: "+
                yearOfProduction+" Mileage: "+mileage+" Engine capacity: "+engineCapacity+" Amount: "+amount);
    }

    @Override
    public int compareTo(Vehicle o) {
        if (this.brand.equals(o.brand)){
            return this.model.compareTo(o.model);
        }
        return this.brand.compareTo(o.brand);
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", condition=" + condition +
                ", price=" + price +
                ", yearOfProduction=" + yearOfProduction +
                ", mileage=" + mileage +
                ", engineCapacity=" + engineCapacity +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Vehicle vehicle = (Vehicle) object;
            if (this.brand.equals(vehicle.getBrand()) && this.model.equals(vehicle.getModel())   && this.engineCapacity == vehicle.getEngineCapacity()
             && this.mileage==vehicle.getMileage() && this.yearOfProduction== vehicle.getYearOfProduction() && this.price==vehicle.getPrice()
             && this.condition.equals(vehicle.getCondition())) {
                result = true;
            }
        }
        return result;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public double getMileage() {
        return mileage;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public ItemCondition getItemCondition() {
        return condition;
    }

    public int getAmountOfCars() {
        return amount;
    }

    public int adjustAmountOfCars(int amount){
        return this.amount+=amount;
    }

    public String getBrand() {
        return brand;
    }

    public ItemCondition getCondition() {
        return condition;
    }
}
