package model;

import entity.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Shoppingcart implements Serializable {
    private HashMap<Integer,ShoppingItem> cart;

    public Shoppingcart(){
        this.cart = new HashMap<>();
    }

    public void addItem(ShoppingItem item){
        if(cart.containsKey(item.getBook().getId())){
            ShoppingItem old = cart.get(item.getBook().getId());
            old.setAmount(old.getAmount()+item.getAmount());
        }else{
            cart.put(item.getBook().getId(),item);
        }
    }

    public boolean removeItem(int id){
        ShoppingItem item = cart.remove(id);
        return item!=null;
    }

    public boolean modifyItem(int id ,int amount,String address){
        ShoppingItem old = cart.get(id);
        if(old ==null) return false;
        old.setAddress(address);
        old.setAmount(amount);
        return true;
    }

    public List<ShoppingItem> list(){
        return new ArrayList<ShoppingItem>(cart.values());
    }
}
