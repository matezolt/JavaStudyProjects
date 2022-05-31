package com.company;

public class PizzaGourmet extends PizzaBasic{

    public PizzaGourmet(String tomatosouce, String mozarella,double price) {
        super(tomatosouce, mozarella,price);
        super.addItems("Cake",3);
        super.addItems("Coke",1.6);
    }

    @Override
    public void addItems(String additionalName, double price) {
        System.out.println("Sorry you can't add further items to this pizza");
    }
}