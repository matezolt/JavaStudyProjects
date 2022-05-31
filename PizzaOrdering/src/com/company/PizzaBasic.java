package com.company;

public class PizzaBasic {
    private  int countAdditions = 0;
    private double sum;
    private int maxLimitOfAdditions =4;

    public PizzaBasic(String tomatosouce, String mozarella, double price) {
        sum=price;
        System.out.println(tomatosouce+" with "+mozarella+" Pizza " +"=> " + price);

    }

    public void setMaxLimit(int maxLimitOfAdditions) {
        this.maxLimitOfAdditions = maxLimitOfAdditions;
    }

    public void addItems(String additionalName, double price){
        if (countAdditions >= maxLimitOfAdditions) System.out.println("Can't add more items");
        else {
            System.out.println(additionalName +" =>" + price);
            sum+=price;
            countAdditions++;
        }
    }

    public void price() {
        System.out.println("Total Price =>" + sum);
    }

}
