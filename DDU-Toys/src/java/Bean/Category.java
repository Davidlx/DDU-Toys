/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author David Liu
 */
public class Category {
    private int id = 5;
    private String name = "cat";
    
    public Category(){
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setId (int id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
