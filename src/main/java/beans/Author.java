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
public class Author {
    String vorname; 
    String nachname; 
    String url; 

    public Author(String vorname, String nachname, String url) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.url = url;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return nachname.toUpperCase() + " " + vorname;
    }
}
