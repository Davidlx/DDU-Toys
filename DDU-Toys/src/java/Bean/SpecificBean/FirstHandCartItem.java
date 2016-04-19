/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean.SpecificBean;

/**
 *
 * @author Dennis T
 */
public class FirstHandCartItem extends FirstHandItem {
    private int itemAmount;
    public int getItemAmount(){
        return itemAmount;
    }
    public void setItemAmount(int amount) {
        itemAmount = amount;
    }
}
