/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.SpecificBean;

import java.sql.SQLException;

/**
 *
 * @author Dennis T
 */
public class OItem {
    private Bean.OrderItem itemInfo;
    private Bean.Toy toyInfo;
    private Bean.Customer sellerInfo;


    /**
     * @return the itemInfo
     */
    public Bean.OrderItem getItemInfo() {
        return itemInfo;
    }

    /**
     * @param itemInfo the itemInfo to set
     */
    public void setItemInfo(Bean.OrderItem itemInfo) throws SQLException {
        this.itemInfo = itemInfo;
        this.toyInfo = new Bean.Toy();
        this.toyInfo.setId(itemInfo.getTid());
        this.toyInfo.getOnId();
        if(this.itemInfo.getRecycled() == 1) {
            this.sellerInfo = new Bean.Customer();
            this.sellerInfo.setId(this.itemInfo.getCid());
            this.sellerInfo.getOnId();
        }
    }

    /**
     * @return the toyInfo
     */
    public Bean.Toy getToyInfo() {
        return toyInfo;
    }

    /**
     * @return the sellerInfo
     */
    public Bean.Customer getSellerInfo() {
        return sellerInfo;
    }

    
    
}
