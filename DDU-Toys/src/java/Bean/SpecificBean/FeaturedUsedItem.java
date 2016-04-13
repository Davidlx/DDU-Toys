/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.SpecificBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Supplier;
import java.util.logging.Logger;
/**
 *
 * @author Dennis T
 */
public class FeaturedUsedItem {
    private Bean.Stock usedItem;
    private Bean.Toy toyInfo;
    
    public FeaturedUsedItem(){};
    
    public Bean.Toy getToyInfo(){
        return this.toyInfo;
    }
    
    public Bean.Stock getUsedItem(){
        return this.usedItem;
    }
    // Also sets ToyInfo
    public void setUsedItem(Bean.Stock stock) throws SQLException{
        usedItem = stock;
        toyInfo = new Bean.Toy();
        toyInfo.setId(usedItem.getId());
        toyInfo.getOnId();
    }
    
}
