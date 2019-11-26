/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Author;
import beans.Book;
import beans.Publisher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class DB_Access {
    
    List<Author> authorlist = new LinkedList();
    List<Publisher> publisherlist = new LinkedList();
            
            
            
    private DB_PStatPool pStatPool = DB_PStatPool.getInstance();

    public List<Book> getAllBooksFromAuthor() throws Exception {
        PreparedStatement pStat = pStatPool.getPStat(DB_StmtType.GET_BOOKS_FROM_AUTHOR);
        pStat.setString(1, "%%");
        ResultSet rs = pStat.executeQuery();
        List<Book> bookList = new LinkedList<>();
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String url = rs.getString("url");
            double price = rs.getDouble("price");
            int publisher_id = rs.getInt("publisher_id");
            String isbn = rs.getString("isbn");
            String name = rs.getString("vorname");
            String nachname = rs.getString("vorname");
            
            
        }
        return bookList;
    }
    
    public static void main(String[] args) {
        DB_Access dba = new DB_Access();
        try {
            List<Book> books = dba.getAllBooksFromAuthor();
            System.out.println(books);
        } catch (Exception ex) {
            Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}


