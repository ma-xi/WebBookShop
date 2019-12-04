/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
        if(!url.equals("") && !url.startsWith("http"))
        {
            url = "http://"+url;
        }
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
        Collections.sort(authorList, (a1, a2) -> a1.getNachname().compareTo(a2.getNachname()));
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
        return String.format("%.2f€", price);
    }

    @Override
    public String toString() {
        return String.format("<b>%s</b><br/>ISBN: %s<br/><a href=\"%s\">%s</a>", 
                title, isbn, url, url);
    }
    
    public void setAuthorList(Author b)
    {
         authorList.add(b);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.title);
        hash = 19 * hash + Objects.hashCode(this.url);
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 19 * hash + Objects.hashCode(this.isbn);
        hash = 19 * hash + Objects.hashCode(this.authorList);
        hash = 19 * hash + Objects.hashCode(this.publisher);
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
        final Book other = (Book) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.authorList, other.authorList)) {
            return false;
        }
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        return true;
    }
    
    
}
