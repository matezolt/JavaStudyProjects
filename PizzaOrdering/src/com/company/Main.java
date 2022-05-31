package com.company;

public class Main {

    public static void main(String[] args) {

        //Pizza Order App

        Pizza pizza = new Pizza("Basic", "Tomato", 5.6, "normal");
        double price = pizza.itemizePizza();

        pizza.addPizzaAddition1("Cheese", 1);
        pizza.addPizzaAddition2("Corn", 0.75);
        pizza.addPizzaAddition3("Egg", 1.5);

        System.out.println("Total Pizza Price is : " + pizza.itemizePizza());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        VeganPizza veganPizza = new VeganPizza("BioTomato", 2.55);
        veganPizza.addPizzaAddition1("Artichoke", 2.11);
        veganPizza.addAdditionVeganPizza1("Bean", 1.8);
        veganPizza.addAdditionVeganPizza2("Olives", 2);
        veganPizza.addAdditionVeganPizza3("Paprika", 1.56);

        System.out.println("Total of Vegan Pizza is : " + veganPizza.itemizePizza());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        AllinOnePizza allinOnePizza = new AllinOnePizza();
        allinOnePizza.itemizePizza();

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        PizzaBasic pizzaBasic = new PizzaBasic("Delicious tomato", "original mozarella", 8.8);
        pizzaBasic.addItems("Egg", 2);
        pizzaBasic.addItems("Jalapeno", 2);
        pizzaBasic.addItems("Basil", 1.6);
        pizzaBasic.addItems("Cheese", 1);
        pizzaBasic.addItems("Cheese", 3);
        pizzaBasic.addItems("Cheese", 4);

        pizzaBasic.price();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        PizzaGourmet pizzaGourmet = new PizzaGourmet("HotSouce", "Double Cheese", 15);
        pizzaGourmet.price();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        HappyPizza happyPizza = new HappyPizza("Bio-Souce",3);

        happyPizza.addItems("Basil", 1);
        happyPizza.addItems("Cheddar", 1.5);
        happyPizza.addItems("Olives", 1.2);
        happyPizza.price();
    }
}
