package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import database.DatabaseManager;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CMUser;
import utilities.StringMD5Hash;

/**
 *
 * @author Alexander
 */
public class signupservlet extends HttpServlet {

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

        // Sign up a user
        String username = request.getParameter("username");
        String uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        String passVerify = request.getParameter("passVerify");
        String email = request.getParameter("email");


        if ( DatabaseManager.getDBM().userExists(username)) {
            String result = "ERROR";
            String resultMessage = "Αυτός ο χρήστης υπάρχει ήδη";
            request.setAttribute("result", result);
            request.setAttribute("resultMessage", resultMessage);
            RequestDispatcher view = request
                    .getRequestDispatcher("signup.jsp");
            view.forward(request, response);
            return;
        }
        
        if (pass.equals(passVerify)) {

            try {
                // Create new user and hash password
                String passwordHashed = StringMD5Hash.md5(pass);
                CMUser newUser = new CMUser(-1, username, uname, passwordHashed, email, 1, "REGULAR", "NULL", "Not Available");

                // Add new User
                ResultSet rs =  DatabaseManager.getDBM().addUser(newUser);

                if (rs == null) {//success
                    //newUser.setUser_id(rs.getInt("u_id"));
                    HttpSession session;
                    // Login succesful, start session
                    session = request.getSession();
                    session.setAttribute("user", newUser.getUsername());
                    session.setAttribute("userId", newUser.getUser_id());
                    session.setAttribute("uName", newUser.getUname());
                    session.setAttribute("uEmail", newUser.getEmail());
                    session.setAttribute("accType", newUser.getAccountType());
                    session.setAttribute("otherInfo", newUser.getMisc());
                    session.setAttribute("birthDate", newUser.getBirthDate());

                    //setting session to expiry in 30 mins
                    session.setMaxInactiveInterval(60 * 60);
                    Cookie userName = new Cookie("user", newUser.getUsername());
                    userName.setMaxAge(60 * 60);
                    response.addCookie(userName);

                    // Redirect to home page as logged in
                    System.out.println("Logged in successfuly as " + newUser);

                    response.sendRedirect("index.jsp");
                    return;

                } else {
                    String result = "ERROR";
                    String resultMessage = "Υπήρξε κάποιο πρόβλημα κατά την εγγραφή";
                    request.setAttribute("result", result);
                    request.setAttribute("resultMessage", resultMessage);
                    RequestDispatcher view = request
                            .getRequestDispatcher("signup.jsp");
                    view.forward(request, response);
                    return;
                }
            } catch (Exception e) {
                System.out.println("Exception on signup:" + e.getMessage());
                String result = "ERROR";
                String resultMessage = "Ο κωδικοί που εισάγατε δεν συμφωνούν";
                request.setAttribute("result", result);
                request.setAttribute("resultMessage", resultMessage);
                RequestDispatcher view = request
                        .getRequestDispatcher("signup.jsp");
                view.forward(request, response);
                return;
            }
        } else {
            String result = "ERROR";
            String resultMessage = "Ο κωδικοί που εισάγατε δεν συμφωνούν";
            request.setAttribute("result", result);
            request.setAttribute("resultMessage", resultMessage);
            RequestDispatcher view = request
                    .getRequestDispatcher("signup.jsp");
            view.forward(request, response);
            return;

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
