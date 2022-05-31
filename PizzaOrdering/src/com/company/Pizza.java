package com.company;

public class Pizza {

    private String name;
    private String tomato;
    private double price;
    private String dough;

    private String addition1Name;
    private double addition1Price;
    private String addition2Name;
    private double addition2Price;
    private String addition3Name;
    private double addition3Price;
    private String addition4Name;
    private double addition4Price;

    public Pizza(String name, String tomato, double price, String dough) {
        this.name = name;
        this.tomato = tomato;
        this.price = price;
        this.dough = dough;
    }

    public void addPizzaAddition1(String name, double price){
        this.addition1Name=name;
        this.addition1Price=price;
    }
    public void addPizzaAddition2(String name, double price){
        this.addition2Name=name;
        this.addition2Price=price;
    }
    public void addPizzaAddition3(String name, double price){
        this.addition3Name=name;
        this.addition3Price=price;
    }
    public void addPizzaAddition4(String name, double price){
        this.addition4Name=name;
        this.addition4Price=price;
    }

    public double itemizePizza(){
        double pizzaPrice = this.price;
        System.out.println(this.name + " pizza made of " + this.dough + " dough" + " with " + this.tomato + ", price is: " + this.price);
        if(this.addition1Name!=null){
            pizzaPrice +=this.addition1Price;
            System.out.println("Added " + this.addition1Name + " for an extra " + this.addition1Price);
        }  if(this.addition2Name!=null){
            pizzaPrice +=this.addition2Price;
            System.out.println("Added " + this.addition2Name + " for an extra " + this.addition2Price);
        }  if(this.addition3Name!=null){
            pizzaPrice +=this.addition3Price;
            System.out.println("Added " + this.addition3Name + " for an extra " + this.addition3Price);
        }  if(this.addition4Name!=null){
            pizzaPrice +=this.addition4Price;
            System.out.println("Added " + this.addition4Name + " for an extra " + this.addition4Price);
        }
        return pizzaPrice;
    }
}



















