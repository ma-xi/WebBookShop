/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Objects;

/**
 *
 * @author Maxi
 */
public class Article 
{
    private Book book;
    private int amount;
    private double totalPrice;

    public Article(Book book, int amount) {
        this.book = book;
        this.amount = amount;
        this.totalPrice = amount * book.getPrice();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        totalPrice = amount * book.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getFormattedPrice()
    {
        return String.format("%.2f €", book.getPrice());
    }
    
    public String getFormattedTotalPrice()
    {
        return String.format("%.2f €", totalPrice);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.book);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (!Objects.equals(this.book, other.book)) {
            return false;
        }
        return true;
    }
    
    
}
