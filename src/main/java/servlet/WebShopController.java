/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

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
            //        Author author = new Author("Leonhard", "Wogg", "www.google.com");
//        List<Author> authorL = List.of(author);
//        Book b = new Book("Leben und Tod des Johnny Cash", "www.google.com", 100, "69", 
//                authorL,
//                new Publisher("Heinrich Freddy Quinn", "www.google.com"));
//            String authorStr = "";
            String sof = request.getParameter("sortOrFilter");
            String filterParam = request.getParameter("filter");
            String filterType = request.getParameter("filterSel");
            LinkedList<Book> booklist;
            List<Book> filteredBookList = new LinkedList<>();
            JOptionPane.showMessageDialog(null, sof);
            if((filterParam == null || 
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
//            for (Book book : booklist) {
//                System.out.println(book.getTitle());
//            }
//            LinkedList<Book> notRightList = new LinkedList<Book>();

            //if(request.getParameter("sortSelection").equals("Titel"))
            //JOptionPane.showMessageDialog(null, Arrays.toString(booklist.toArray()));
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

            if (filterParam != null && sof != null && sof.equals("filter"))
            {
                filterParam = filterParam.trim();
                String filter = filterParam.toLowerCase();
                if (filterType.equals("author")) {
////                    JOptionPane.showMessageDialog(null, "author oida");
//                    for (Book b : booklist) {
//                        boolean notRight = true;
//                        for (Author a : b.getAuthorList()) {
//
//                            if (a.getNachname().toLowerCase().contains(filter) || a.getVorname().toLowerCase().contains(filter)) {
//                                notRight = false;
//                            }
//                        }
//                        if (notRight) {
//                            notRightList.add(b);
//                        }
//                    }
//                    for (Book b : notRightList) {
//                        booklist.remove(b);
//                    }
                    filteredBookList = booklist.stream().filter((b) -> {
                        List<Author> aList = b.getAuthorList();
                        for (Author author : aList)
                        {
                            if(author.getNachname().toLowerCase().contains(filter)
                                    || author.getVorname().toLowerCase().contains(filter))
                            {
                                return true;
                            }
                        }
                        return false;
                    }).collect(Collectors.toList());
                    
                } else {
//                    for(Book b:booklist)
//                    {
//                        if(!b.getTitle().toLowerCase().contains(filter))
//                        {
//                            notRightList.add(b);
//                        }
//                    }
//                    for(Book b:notRightList)
//                    {
//                        booklist.remove(b);
//                    }
//                    JOptionPane.showMessageDialog(null, "hfkjdasahkfjldsahkljfhkdasjlfhdlj");
//                    booklist.stream()
//                            .filter((b) -> (b.getTitle().toLowerCase().contains(filter.toLowerCase())))
//                            .collect(Collectors.toList());
                    filteredBookList = booklist.stream()
                            .filter((b) -> {
//                                System.out.println("Filter: "+filter
//                                    +" - Titel: "+b.getTitle().toLowerCase()
//                                    +" - contains: "+b.getTitle().toLowerCase().contains(filter));
                                return b.getTitle().toLowerCase().contains(filter);
                            })
                            .collect(Collectors.toList());

                }
            }
            else
            {
                filteredBookList = booklist;
            }

            //        booklist.add(b);
//            notRightList.clear();
            request.getSession().setAttribute("books2display", filteredBookList);
            request.getSession().setAttribute("setFilter", filterParam);
            request.setAttribute("setSort", sortStr);
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
