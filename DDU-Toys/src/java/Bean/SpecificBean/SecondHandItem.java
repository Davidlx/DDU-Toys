/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.SpecificBean;

import Bean.Customer;
import java.sql.SQLException;

/**
 *
 * @author Ugo
 */
public class SecondHandItem {
    private Bean.Stock usedItem;
    private Bean.Toy toyInfo;
    private Bean.Customer customerInfo;
    
    public SecondHandItem(){};
    
    public Bean.Toy getToyInfo(){
        return this.toyInfo;
    }
    
    public Bean.Customer getCustomerInfo(){
        return this.customerInfo;
    }
    
    public Bean.Stock getUsedItem(){
        return this.usedItem;
    }
    
    // Also sets ToyInfo
    public void setUsedItem(Bean.Stock stock) throws SQLException{
        usedItem = stock;
        toyInfo = new Bean.Toy();
        toyInfo.setId(usedItem.getTid());
        toyInfo.getOnId();
        customerInfo = new Customer();
        customerInfo.setId(usedItem.getCid());
        customerInfo.getOnId();
    }
    
}
