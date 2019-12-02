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
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
public class DB_Access {
    
    private static List<Author> authorlist = new LinkedList();
    private static List<Publisher> publisherlist = new LinkedList();            
    private static DB_PStatPool pStatPool = DB_PStatPool.getInstance();    
    

    public static List<Book> getAllBooksFromAuthor(String author) throws Exception {
        PreparedStatement pStat = pStatPool.getPStat(DB_StmtType.GET_BOOKS_FROM_AUTHOR);
        if(!author.equals(""))
        {
               pStat.setString(1, author);
        }
        
        else
        {
            pStat.setString(1, "%%");
        }
     
        ResultSet rs = pStat.executeQuery();
        List<Book> bookList = new LinkedList<>();
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
     
            String bookurl = rs.getString("url");

            double price = rs.getDouble("price");
            int publisher_id = rs.getInt("publisher_id");
            String isbn = rs.getString("isbn");
  
            bookList.add(new Book(title,bookurl,price,isbn,getAuthors(book_id),getPublisher(publisher_id)));
            
        }
        pStatPool.realesePStat(pStat);
        return bookList;
    }
   
    public static LinkedList<Author> getAuthors(int bookid) throws Exception
    {
        LinkedList<Author> returnlist = new LinkedList<>();
        PreparedStatement pstat = pStatPool.getPStat(DB_StmtType.GETAUTHORSFROMBOOK);
        pstat.setInt(1, bookid);
        ResultSet rs = pstat.executeQuery();
        while(rs.next())
        {
            String authorname = rs.getString("firstname");
            String authorlastname = rs.getString("lastname");
            String url = rs.getString("url");
            
            returnlist.add(new Author(authorname, authorlastname, url));
        }
        pStatPool.realesePStat(pstat);
        return returnlist;
    }
    public static Publisher getPublisher(int publisher_id) throws Exception
    {
        PreparedStatement pstat = pStatPool.getPStat(DB_StmtType.GETPUBLISHERWITHID);
        pstat.setInt(1, publisher_id);
        ResultSet rs = pstat.executeQuery();
        Publisher p = null;
        while(rs.next())
        {
            String name = rs.getString("name");
            String url = rs.getString("url");
             p = new Publisher(name, url);
        }
        pStatPool.realesePStat(pstat);
        return p;
    }
    public static void main(String[] args) {
        DB_Access dba = new DB_Access();
        try {
         //   List<Book> books = dba.getAllBooksFromAuthor();
          //  System.out.println(books);
        } catch (Exception ex) {
            Logger.getLogger(DB_Access.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}


