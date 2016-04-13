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
public class FirstHandItem {
    private Bean.Stock firstHandItem;
    private Bean.Toy toyInfo;
    
    public FirstHandItem(){};
    
    public Bean.Toy getToyInfo(){
        return this.toyInfo;
    }
    
    public Bean.Stock getFirstHandItem(){
        return this.firstHandItem;
    }
    // Also sets ToyInfo
    public void setUsedItem(Bean.Stock stock) throws SQLException{
        firstHandItem = stock;
        toyInfo = new Bean.Toy();
        toyInfo.setId(firstHandItem.getId());
        toyInfo.getOnId();
    }
    
}
