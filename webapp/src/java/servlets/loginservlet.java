package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.DatabaseManager;
import ldap.Uthldap;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alexander
 */
public class loginservlet extends HttpServlet {

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
            out.println("<title>Servlet loginservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginservlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        // Login - At the moment use LDAP
        String user = request.getParameter("uname");
        String pass = request.getParameter("pass");
        
        DatabaseManager db = new DatabaseManager();
        
        Uthldap ldap = new Uthldap(user, pass);
        
        HttpSession session;

        //Succesful Login
        if (ldap.auth()) {
            
            // Check in our DB
            ResultSet rs = db.getUser(user);
            String uName = "NULL";
            try{
            if(rs != null) {
                // User is already in our db
                System.out.println("LoginServlet: Already in our db");
                uName = rs.getString("first_name");
            }else{
                // Not in our db, insert him
                System.out.println("LoginServlet: Adding in our db");
                db.addUser(user, ldap);
                uName = ldap.getName();
            }
            }catch(SQLException e){
                System.out.println("LoginServlet: SQLException" + e.getMessage());
            }
            
            // Login succesful, start session
            session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("uName",uName);
            
            
            //setting session to expiry in 30 mins
            session.setMaxInactiveInterval(60 * 60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(60 * 60);
            response.addCookie(userName);
            
            // Redirect to home page as logged in
            System.out.println("Logged in successfuly as " + user );
            //RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            //view.forward(request, response);
            response.sendRedirect("index.jsp");
            
        } else {
            // Not succeeded
            System.out.println("Error on login");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
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
