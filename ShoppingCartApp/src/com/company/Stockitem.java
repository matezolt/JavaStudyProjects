package com.company;

public class Stockitem implements Comparable<Stockitem>{
    private final String name;
    private double price;
    private int quantityInStock =0;
    private int reserved =0;

    public Stockitem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityInStock =0;
    }

    public Stockitem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityStock;
    }

    public void setPrice(double price) {
        if(price>0.0) {
            this.price = price;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int availableQuantity() {
        return quantityInStock - reserved;
    }
    public void adjustStock(int quantity){    //increase + decrease stocklevel
        int newQuantity= this.quantityInStock + quantity;
        if(newQuantity>=0){
            this.quantityInStock = newQuantity;
        }
    }
    public int reservedStock(int quantity){
        if (quantity <= availableQuantity()) {
            reserved += quantity;
            return quantity;
        }
        return 0;
    }
    public int unreserveStock(int quantity){
        if(quantity <= reserved){
            reserved -=quantity;
            return quantity;
        }
        return 0;
    }
    public int finalizeStock(int quantity){  //checks if enough items are reserved
        if(quantity<=reserved){
            quantityInStock -=quantity;  //reduce stock level
            reserved -=quantity;        //reduce reserved level
            return quantity;
        }
        return 0;
    }


    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering Stockitem.equals");
        if(obj==this){
            return true;
        }
        if((obj==null)|| (obj.getClass() != this.getClass())){
            return false;
        }
        String objName=((Stockitem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(Stockitem object) {
        if(this==object){  //object in memory and the one passed to the method
            return 0;
        }
        if(object != null){
            return this.name.compareTo(object.getName());
        }
        throw new NullPointerException(); //do not want to compare something which is 0
    }

    @Override
    public String toString() {
        return this.name + " : price " + this.price + ". Reserved: " + this.reserved;
    }
}
