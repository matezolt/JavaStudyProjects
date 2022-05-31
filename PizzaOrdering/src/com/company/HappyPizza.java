package com.company;

public class HappyPizza extends PizzaBasic{

    public HappyPizza(String tomatosouce, double price) {
        super("Bio-souce", "buffalo mozarella", price);
        setMaxLimit(6);
    }

    @Override
    public void addItems(String additionalName, double price) {
        super.addItems(additionalName, price);
    }
}
