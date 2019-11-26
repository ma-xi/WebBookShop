/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Leonhard Wogg
 */
public class Publisher {
    String nameOfPublisher;
    String url; 

    public Publisher(String nameOfPublisher, String url) {
        this.nameOfPublisher = nameOfPublisher;
        this.url = url;
    }

    public String getNameOfPublisher() {
        return nameOfPublisher;
    }

    public void setNameOfPublisher(String nameOfPublisher) {
        this.nameOfPublisher = nameOfPublisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
