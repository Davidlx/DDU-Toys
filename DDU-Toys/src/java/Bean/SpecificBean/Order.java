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
    private String orderId;
    private Timestamp orderTime;
    private float totalPrice;
    private int numberOfItem;
    
    private ArrayList<FirstHandItem> firstHandItems;
    private ArrayList<SecondHandItem> secondHandItems;

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the orderTime
     */
    public Timestamp getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime the orderTime to set
     */
    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * @return the totalPrice
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the numberOfItem
     */
    public int getNumberOfItem() {
        return numberOfItem;
    }

    /**
     * @param numberOfItem the numberOfItem to set
     */
    public void setNumberOfItem(int numberOfItem) {
        this.numberOfItem = numberOfItem;
    }

    /**
     * @return the firstHandItems
     */
    public ArrayList<FirstHandItem> getFirstHandItems() {
        return firstHandItems;
    }

    /**
     * @param firstHandItems the firstHandItems to set
     */
    public void setFirstHandItems(ArrayList<FirstHandItem> firstHandItems) {
        this.firstHandItems = firstHandItems;
    }

    /**
     * @return the secondHandItems
     */
    public ArrayList<SecondHandItem> getSecondHandItems() {
        return secondHandItems;
    }

    /**
     * @param secondHandItems the secondHandItems to set
     */
    public void setSecondHandItems(ArrayList<SecondHandItem> secondHandItems) {
        this.secondHandItems = secondHandItems;
    }
    
    
}
