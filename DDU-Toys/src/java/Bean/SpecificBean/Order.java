/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.SpecificBean;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author David Liu
 */
public class Order {
    private Bean.CustomerOrder orderInfo;
    private float totalPrice = 0;
    private int numberOfItems = 0;
    private ArrayList<Bean.SpecificBean.OItem> items;
    
    
    /**
     * @return the orderInfo
     */
    public Bean.CustomerOrder getOrderInfo() {
        return orderInfo;
    }

    /**
     * @param orderInfo the orderInfo to set
     */
    public void setOrderInfo(Bean.CustomerOrder orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * @return the numberOfItems
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * @return the items
     */
    public ArrayList<OItem> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(ArrayList<Bean.SpecificBean.OItem> items) {
        this.items = items;
        setPriceAndTotalNumber();
    }
    
    private void setPriceAndTotalNumber(){
        float price = 0;
        int amount = 0;
        for(int i = 0; i < items.size(); i++){
            price += items.get(i).getItemInfo().getAmount() * items.get(i).getItemInfo().getPrice();
            amount += items.get(i).getItemInfo().getAmount();
        }
        this.totalPrice = price;
        this.numberOfItems = amount;
    }

}
