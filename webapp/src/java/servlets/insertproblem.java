/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DatabaseManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Car;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Alexander
 */
@WebServlet("/upload")
@MultipartConfig
public class insertproblem extends HttpServlet {

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

        RequestDispatcher view = request
                .getRequestDispatcher("insertproblem.jsp");
        view.forward(request, response);

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
        System.out.println("doGET");

        String type = request.getParameter("type");
        int mid = Integer.parseInt(request.getParameter("mid"));

        DatabaseManager db = new DatabaseManager();

        // Get Car info
        Car carInfo = db.getCar(mid);

        request.setAttribute("result", "ok");
        request.setAttribute("carinfo", carInfo);

        if (type.equalsIgnoreCase("solved")) { //solved problem

        } else { //unsolved problem

        }

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

        String UPLOAD_DIRECTORY = "E:\\tomcat\\apache-tomcat-8.0.30\\webapps\\data\\";
        System.out.println("doPOST");
       

        // Get input
        String descriptionText = request.getParameter("descriptiontext");
        String solutionText = request.getParameter("solutiontext");
        String model_id = request.getParameter("modelId");
        String fileName = "NULL";
        
        
        Part filePart = request.getPart("pic"); // Retrieves <input type="file" name="file">
        if(filePart != null){
            fileName = filePart.getSubmittedFileName();
            InputStream fileContent = filePart.getInputStream();
            File newFile = new File(UPLOAD_DIRECTORY + fileName);
            uploadPhotos(fileContent, newFile);
            fileName = newFile.getPath();
        }
        
        
        
        // Insert problem
        DatabaseManager db = new DatabaseManager();
        db.insertProblem(model_id, descriptionText, solutionText, fileName);
        
        processRequest(request, response);
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

    private void uploadPhotos(InputStream inputStream, File file) {
        String UPLOAD_DIRECTORY = "E:\\tomcat\\apache-tomcat-8.0.30\\webapps\\data\\";
        OutputStream outputStream = null;

        try {

            // write the inputStream to a FileOutputStream
            outputStream
                    = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            System.out.println("Done!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
