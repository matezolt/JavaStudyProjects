package com.company;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Basket {
    private final String name;
    private final Map<Stockitem, Integer> list;



    public Basket(String name) {
        this.name = name;
        this.list= new LinkedHashMap<>();
    }

    public int addToBasket(Stockitem item, int quantity){
        if((item !=null) && (quantity>0)){
            int inBasket = list.getOrDefault(item,0); //get the present quantity or 0
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }
    public int removeFromBasket(Stockitem item,int quantity){
        if((item !=null) && (quantity>0)){
          //check if item already in basket
            int inBasket = list.getOrDefault(item,0); //will show the current quantity
            int newQuantity = inBasket - quantity;

            if(newQuantity >0){
                list.put(item, newQuantity);
                return quantity;
            }else if (newQuantity ==0){
                list.remove(item);
                return quantity;
            }
        }
        return 0;
    }
    public void clearBasket(){
        this.list.clear();
    }

    public Map<Stockitem, Integer> items(){     //items in the basket stored in MAP
        return Collections.unmodifiableMap(list);
    }
    @Override
    public String toString() {
        String s= "\nShopping Basket " + name + " contains " + list.size() + ((list.size() ==1) ? " item" : " items") + "\n";
        double totalCost = 0.0;
        for(Map.Entry<Stockitem, Integer> item : list.entrySet()){
            s = s + item.getKey() + ". " + item.getValue() + " purchased\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }
}
