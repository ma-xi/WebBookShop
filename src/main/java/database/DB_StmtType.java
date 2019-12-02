/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author 
 */
public enum DB_StmtType {
    
    GET_BOOKS_FROM_AUTHOR("SELECT b. FROM book b INNER JOIN bookauthor ba ON b.book_id = ba.book_id "
            + "INNER JOIN author a ON ba.author_id = a.author_id "
            + "WHERE a.lastname LIKE ?"),
    
    GETAUTHORSFROMBOOK(("Select * FROM bookauthor INNER JOIN author USING author_id WHERE book_id = ?")),
    
    GETPUBLISHERWITHID("SELECT * FROM publisher WHERE publisher_id = ?");
    
    
    private String sqlString;

    private DB_StmtType(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getSqlString() {
        return sqlString;
    }
    
    
}
