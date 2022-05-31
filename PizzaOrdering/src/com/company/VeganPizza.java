package com.company;

public class VeganPizza extends Pizza{

    private String additionVeganPizza1Name;
    private double additionVeganPizza1Price;
    private String additionVeganPizza2Name;
    private double additionVeganPizza2Price;
    private String additionVeganPizza3Name;
    private double additionVeganPizza3Price;

    public VeganPizza(String tomato, double price) {
        super("VeganPizza", tomato, price, "VitaminPacked");
    }
    public void addAdditionVeganPizza1(String name, double price) {
        this.additionVeganPizza1Name = name;
        this.additionVeganPizza1Price = price;
    }
        public void addAdditionVeganPizza2(String name, double price) {
            this.additionVeganPizza2Name = name;
            this.additionVeganPizza2Price = price;
     }
        public void addAdditionVeganPizza3(String name, double price){
        this.additionVeganPizza3Name = name;
        this.additionVeganPizza3Price = price;
    }

    @Override
    public double itemizePizza() {
        double pizzaPrice = super.itemizePizza();
        if (this.additionVeganPizza1Name !=null){
            pizzaPrice +=this.additionVeganPizza1Price;
            System.out.println("Added " + this.additionVeganPizza1Name + " for an extra " + this.additionVeganPizza1Price);
        }if (this.additionVeganPizza2Name !=null){
            pizzaPrice +=this.additionVeganPizza2Price;
            System.out.println("Added " + this.additionVeganPizza2Name + " for an extra " + this.additionVeganPizza2Price);
        }if (this.additionVeganPizza3Name !=null){
            pizzaPrice +=this.additionVeganPizza3Price;
            System.out.println("Added " + this.additionVeganPizza3Name + " for an extra " + this.additionVeganPizza3Price);
        }
        return pizzaPrice;
    }
}
