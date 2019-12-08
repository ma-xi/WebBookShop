/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Article;
import beans.Author;
import beans.Book;
import beans.Publisher;
import database.DB_Access;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonhard Wogg
 */
@WebServlet(name = "WebShopController", urlPatterns = {"/WebShopController"})
public class WebShopController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WebShopController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WebShopController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String sof = request.getParameter("sortOrFilter");
            String filterParam = request.getParameter("filter");
            String filterType = request.getParameter("filterSel");
            LinkedList<Book> booklist;
            List<Book> filteredBookList = new LinkedList<>();
            if((filterParam == null || 
                filterParam.equals("") ||
                !filterParam.equals(request.getSession().getAttribute("setFilter")) ||
                filterType == null ||    
                !filterType.equals(request.getSession().getAttribute("setFilterSel")))
                    &&
                (sof == null || sof.equals("filter")))
            {
                booklist = new LinkedList<>(DB_Access.getAllBooksFromAuthor());
            }
            else
            {
                booklist = new LinkedList((Collection) request.getSession().getAttribute("books2display"));
            }
            
            if(request.getParameter("empty") != null)
            {
                List<Article> shoppingCart = (List<Article>) request.getSession().getAttribute("shoppingCart");
                if(shoppingCart != null && !shoppingCart.isEmpty())
                {
                    shoppingCart.clear();
                }
                request.getSession().setAttribute("shoppingCart", shoppingCart);
                request.getRequestDispatcher("/bookShopView.jsp").forward(request, response);
                return;
            }
            
            String book4sc = request.getParameter("bTitle");
            if(book4sc != null && !book4sc.equals(""))
            {
                List<Article> shoppingCart = (List<Article>) request.getSession().getAttribute("shoppingCart");
                if(shoppingCart == null)
                {
                    shoppingCart = new LinkedList<>();
                }
                for (Book b1 : booklist)
                {
                    if(b1.getTitle().equals(book4sc))
                    {
                        Article art = new Article(b1, 1);
                        boolean artSet = false;
                        for (Article oldArticle : shoppingCart) 
                        {
                            if(oldArticle.getBook().getTitle().equals(art.getBook().getTitle()))
                            {
                                int index = shoppingCart.indexOf(oldArticle);
                                shoppingCart.get(index).setAmount(shoppingCart.get(index).getAmount()+1);
                                artSet = true;
                            }
                        }
                        if(!artSet)
                        {
                            shoppingCart.add(art);
                        }
                    }
                }
                request.getSession().setAttribute("shoppingCart", shoppingCart);
                request.getRequestDispatcher("/bookShopView.jsp").forward(request, response);
                return;
            }
            
            String sortStr = request.getParameter("sortSelection");
            if(sortStr == null)
            {
                sortStr = "Titel";
            }
            if (sortStr.equals("Titel")) {
                Collections.sort(booklist, (o1, o2) -> {
                    return o1.getTitle().compareTo(o2.getTitle());
                });
            } else if (sortStr.equals("Autor")) {
                Collections.sort(booklist, (o1, o2) -> {
                    return (o1.getAuthorList().get(0).getNachname())
                            .compareTo(o2.getAuthorList().get(0).getNachname());
                });
            } else if (sortStr.equals("Preis")) {
                Collections.sort(booklist, (o1, o2) -> {
                    return (o1.getPrice() + "").compareTo(o2.getPrice() + "");
                });
            }

            if (filterParam != null && !filterParam.equals("") && sof != null && sof.equals("filter"))
            {
                filterParam = filterParam.trim();
                String filter = filterParam.toLowerCase();
                if (filterType.equals("author")) {
                    filteredBookList = booklist.stream().filter((b) -> {
                        List<Author> aList = b.getAuthorList();
                        for (Author author : aList)
                        {
                            if((author.getVorname()+" "+author.getNachname()).toLowerCase().contains(filter))
                            {
                                return true;
                            }
                        }
                        return false;
                    }).collect(Collectors.toList());
                    
                } else {
                    filteredBookList = booklist.stream()
                            .filter((b) -> {
                                return b.getTitle().toLowerCase().contains(filter);
                            })
                            .collect(Collectors.toList());

                }
            }
            else
            {
                filteredBookList = booklist;
            }

            request.getSession().setAttribute("books2display", filteredBookList);
            request.getSession().setAttribute("setFilter", filterParam);
            request.getSession().setAttribute("setSort", sortStr);
            request.getSession().setAttribute("setFilterSel", filterType);
            request.getRequestDispatcher("/bookShopView.jsp").forward(request, response);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
