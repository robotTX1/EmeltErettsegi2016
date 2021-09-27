package com.practise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kosar {
    private List<String> itemList = new ArrayList<>();

    public Kosar() {
    }

    public void addItem(String item) {
        this.itemList.add(item);
    }

    public void print() {
        for(String s : itemList) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    public Map<String, Integer> getItemsWithCount() {
        Map<String, Integer> items = new HashMap<>();

        for(String s : itemList) {
            if(items.containsKey(s)) {
                items.put(s, items.get(s) + 1);
            } else {
                items.put(s, 1);
            }
        }

        return items;
    }

    public boolean isInBasket(String item) {
        return itemList.contains(item);
    }

    public int getBasketSize() {
        return itemList.size();
    }

    public List<String> getItemList() {
        return itemList;
    }
}
