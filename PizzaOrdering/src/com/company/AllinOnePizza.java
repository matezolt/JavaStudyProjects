package com.company;

public class AllinOnePizza extends Pizza{
    public AllinOnePizza() {
        super("AllinOnePizza", "tomatospecial", 10, "superCeed");
        super.addPizzaAddition1("Coke", 2.5);
        super.addPizzaAddition2("Cake", 3);
    }
}

//
//public class Hamburger {
//    private  int count = 0;
//    private int sum;
//    private int maxLimit =4;
//
//    public Hamburger(String breadRoll, String meat, int price) {
//        sum=price;
//        System.out.println(breadRoll+" "+meat+" Burger " +"=> " + price);
//
//    }
//
//    public void setMaxLimit(int maxLimit) {
//        this.maxLimit = maxLimit;
//    }
//
//    public void addItems(String additionalName, int price){
//        if (count >= maxLimit) System.out.println("Can't add more items");
//        else {
//            System.out.println(additionalName +" =>" + price);
//            sum+=price;
//            count++;
//        }
//    }
//
//    public void price() {
//        System.out.println("Total Price =>" + sum);
//    }
//
//}
//
//public class HealthyBurger extends Hamburger {
//
//    public HealthyBurger(String meat, int price) {
//        super("brown rye", meat, price);
//        setMaxLimit(6);
//    }
//
//    @Override
//    public void addItems(String additionalName, int price) {
//        super.addItems(additionalName, price);
//    }
//}
//
//public class DeluxeHamburger extends Hamburger {
//
//    public DeluxeHamburger(String breadRoll, String meat,int price) {
//        super(breadRoll, meat,price);
//        super.addItems("Chips",50);
//        super.addItems("Drinks",100);
//    }
//
//    @Override
//    public void addItems(String additionalName, int price) {
//        System.out.println("Sorry you can't add items to this burger");
//    }
//}