package com.company;

import java.util.Map;

public class Main {
    private static Stocklist stocklist = new Stocklist();

    public static void main(String[] args) {
        Stockitem tempStock = new Stockitem("roll", 0.5, 15);
        stocklist.addStock(tempStock);
        tempStock = new Stockitem("bread", 1.5, 20);
        stocklist.addStock(tempStock);
        tempStock = new Stockitem("bun", 1.2, 14);
        stocklist.addStock(tempStock);
        tempStock = new Stockitem("bun", 1.3, 7);
        stocklist.addStock(tempStock);
        tempStock = new Stockitem("loafbread", 1.8, 11);
        stocklist.addStock(tempStock);
        tempStock = new Stockitem("cake", 1.3, 9);
        stocklist.addStock(tempStock);
        tempStock = new Stockitem("fruitjuice", 2, 5);
        stocklist.addStock(tempStock);
        System.out.println(stocklist);

        Basket testBasket = new Basket("Test");
        sellItem(testBasket, "bread", 4);
        System.out.println(testBasket);
        sellItem(testBasket, "cake", 3);
        System.out.println(testBasket);

        sellItem(testBasket, "fireworks", 35); //should fail as not part of the list
        sellItem(testBasket, "bread", 40); // should fail as not enough stock

//        System.out.println(stocklist);

        Basket basket = new Basket("customer");
        sellItem(basket, "roll", 6);
        sellItem(basket, "bun", 8);
        sellItem(basket, "fruitjuice", 6);
        removeItem(basket, "bun", 2);
        System.out.println(basket);

        removeItem(testBasket, "bread", 4);
        removeItem(testBasket, "cake", 2);
        removeItem(testBasket, "bread", 1);
        System.out.println("bread removed " + removeItem(testBasket, "bread", 30));

        System.out.println(testBasket);

        System.out.println("\nDisplay stock list before and after checkout");
        System.out.println(basket);
        System.out.println(stocklist);
        checkOut(basket);
        System.out.println(basket);
        System.out.println(stocklist);

        checkOut(testBasket);
        System.out.println(testBasket);

    }
    public static int sellItem(Basket basket, String item, int quantity){
        //read the item from the stock list
        Stockitem stockitem = stocklist.get(item);
        if(stockitem ==null){
            System.out.println("We do not sell " + item);
            return 0;
        }
        if(stocklist.reserveStock(item, quantity) !=0){
            return basket.addToBasket(stockitem, quantity);  //how much is sold
        }
        return 0;
    }
    public static int removeItem(Basket basket, String item, int quantity){
        //read the item from the stock list
        Stockitem stockitem = stocklist.get(item);
        if(stockitem ==null){
            System.out.println("We do not sell " + item);
            return 0;
        }
        if(basket.removeFromBasket(stockitem, quantity) ==quantity){
            return stocklist.unreserveStock(item, quantity);
        }
        return 0;
    }
    public static void checkOut(Basket basket){
        for(Map.Entry<Stockitem, Integer> item: basket.items().entrySet()){
            stocklist.sellStock(item.getKey().getName(), item.getValue());
        }
        basket.clearBasket();
    }
}
