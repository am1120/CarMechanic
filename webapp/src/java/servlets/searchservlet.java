package servlets;

import database.DatabaseManager;
import java.io.PrintWriter;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import model.CarDAO;
import exceptions.ApplicationException;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import model.Car;

/**
 *
 * @author Alexander Patras
 */
public class searchservlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/car_mechanic")
    DataSource ds;

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
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
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
        //Get Request parameters from form
        String formtype = request.getParameter("formtype");

        List<Car> result = null;

        if (formtype.equalsIgnoreCase("textform")) {
            System.out.println("text form");
            String searchString = request.getParameter("searchString");
            searchString = searchString.toUpperCase();

            String[] arguments = searchString.split("\\s");

            

            result = DatabaseManager.getDBM().searchCars(arguments);

        }

        if (formtype.equalsIgnoreCase("selectform")) {

            String makerParam = request.getParameter("maker");
            String modelParam = request.getParameter("model");
            String yearParam = request.getParameter("year");
            String engineParam = request.getParameter("engine");
            
            
            String maker = makerParam.split(":")[1];
            
            String model = modelParam.split(":")[1];
            String year = yearParam.split(":")[1];
            String engine = engineParam.split(":")[1];
           
            //try {

          
            result = DatabaseManager.getDBM().searchCars(maker, model, year, engine);
            /*Using PRG Pattern.
             * Instead of forwarding from doPost() method, we are doing a
             * redirection to avoid duplicate form submission.
             */

        }

        if (result != null) {
            
            request.setAttribute("result", "ok");
            request.setAttribute("searchresult", result);
            Enumeration enuma = request.getAttributeNames();
            while (enuma.hasMoreElements()) {
                System.out.println(enuma.nextElement());
            }
        } else {
                //} catch (ApplicationException e) {
            //Log the error
            request.setAttribute("error", "ERROR ON SEARCH");
        }

        RequestDispatcher view = request
                .getRequestDispatcher("index.jsp");
        view.forward(request, response);
        //response.sendRedirect("searchresult.do?s="
        //      + success);
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
