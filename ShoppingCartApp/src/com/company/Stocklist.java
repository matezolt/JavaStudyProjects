package com.company;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Stocklist {
    private final Map<String, Stockitem> list;

    public Stocklist() {
        this.list = new LinkedHashMap<>();
    }
    public int addStock(Stockitem item){
        if(item != null){
            //check if already have quantity of this item
            Stockitem inStock = list.get(item.getName());
            //if there are already stocks on this item, adjust the quantity
            if(inStock != null){
                item.adjustStock(inStock.availableQuantity());//
            }
            list.put(item.getName(), item);  //get primary key and actual item
            return item.availableQuantity();
        }
        return 0;  //no stock movement
    }
    public int sellStock(String item, int quantity){
        Stockitem inStock = list.get(item);
        if((inStock !=null) && (quantity>0)){
            return inStock.finalizeStock(quantity);
        }
        return 0;
//        Stockitem inStock = list.getOrDefault(item, null);
//        if((inStock !=null) && (inStock.availableQuantity() >=quantity) && (quantity >0)){
//            inStock.adjustStock(-quantity);
//            return quantity; //how many items taken from the stock
//        }
//        return 0;
    }
    public int reserveStock(String item,int quantity){
        Stockitem inStock= list.get(item);
        if((inStock != null) &&(quantity >0)){
            return inStock.reservedStock(quantity);
        }
        return 0;
    }
    public int unreserveStock(String item, int quantity){
        Stockitem inStock= list.get(item);
        if((inStock != null) &&(quantity >0)){
            return inStock.unreserveStock(quantity);
        }
        return 0;
    }

    public Stockitem get(String key){
        return list.get(key);
    }
    public Map<String, Stockitem> Items() {
        return Collections.unmodifiableMap(list);  //wrapper
    }

    @Override
    public String toString() {
        String s= "\nStock List\n";
        double totalCost = 0.0;
        for(Map.Entry<String, Stockitem> item : list.entrySet()){
            Stockitem stockitem = item.getValue();
            double itemValue = stockitem.getPrice() * stockitem.availableQuantity();

            s = s + stockitem + ". There are " + stockitem.availableQuantity() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost +=itemValue;
        }
        return s + "Total stock value " + totalCost;
    }
}
