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
    
    GETBOOKS("SELECT * FROM book"),
    
    GETAUTHORSFROMBOOK(("Select * FROM bookauthor ba INNER JOIN author a ON ba.author_id = a.author_id WHERE book_id = ?")),
    
    GETPUBLISHERWITHID("SELECT * FROM publisher WHERE publisher_id = ?");
    
    
    private String sqlString;

    private DB_StmtType(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getSqlString() {
        return sqlString;
    }
    
    
}
