package model;

import entity.Book;
import jdk.management.resource.internal.inst.SocketRMHooks;

public class ShoppingItem {
    private Book book;
    private int amount;
    private String address;

    public ShoppingItem(Book book,int amount,String address){
        this.book = book;
        this.amount = amount;
        this.address = address;
    }

    public Book getBook() {
        return book;
    }

    public int getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object obj) {
        ShoppingItem item = (ShoppingItem) obj;
        Book book = item.getBook();
        return book.getId()==this.book.getId();
    }

    @Override
    public int hashCode(){
        return this.book.getId();
    }
}
