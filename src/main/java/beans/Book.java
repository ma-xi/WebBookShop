/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Leonhard Wogg
 */
public class Book {

    String title;
    String url;
    double price;
    String isbn;
    List<Author> authorList = new LinkedList<Author>();
    Publisher publisher;

    public Book(String title, String url, double price, String isbn, List<Author> author, Publisher publisher) {
        this.title = title;
        this.url = url;
        this.price = price;
        this.isbn = isbn;
        this.authorList = author;
        this.publisher = publisher;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    public String getFormattedPrice()
    {
        return String.format("%.2f €", price);
    }

    @Override
    public String toString() {
        return String.format("<b>%s</b><br/>ISBN: %s<br/><a href=\"%s\">%s</a>", 
                title, isbn, url, url);
    }
    
    
}
