/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexander
 */
public class SendMessage extends HttpServlet {

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
            out.println("<title>Servlet SendMessage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendMessage at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        
        // Get information
        String contactEmail = request.getParameter("contactEmail");
        String contactName = request.getParameter("contactName");
        String contactSubject = request.getParameter("contactSubject");
        String contactMessage = request.getParameter("contactMessage");
        
        String resultMessage;
        String result;
        
        // Recipient's email ID needs to be mentioned.
        String to = "alexanpatr@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "patras@uth.gr";

        // Setup mandrill
        String host = "smtp.mandrillapp.com";

        // Get system properties object
        Properties mailServerProperties = System.getProperties();
        String apiKey = "rHwHymgxZKsZcBm1tTUIJQ";
        mailServerProperties.setProperty("mail.user", "alexanpatr@gmail.com");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Get the default Session object.
        Session getMailSession = null;
        MimeMessage generateMailMessage;
        try {

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);

            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            generateMailMessage.setSubject(contactSubject);
            generateMailMessage.setFrom(new InternetAddress(from));

            String emailBody = "Message from " + contactName + ",\n" + contactMessage;
            generateMailMessage.setContent(emailBody, "text/html");

            Transport transport = getMailSession.getTransport("smtp");
            
            transport.connect(host, contactEmail, apiKey);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();

            resultMessage = "Η αποστολή του μηνύματος ήταν επιτυχής";
            result = "OK";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            result = "ERROR";
            resultMessage = "Υπήρξε κάποιο πρόβλημα κατά την αποστολή του μηνύματος";
        }
        
        request.setAttribute("result", result);
        request.setAttribute("resultMessage",resultMessage);
        
        
         RequestDispatcher view = request
                .getRequestDispatcher("contact.jsp");
        view.forward(request, response);
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
