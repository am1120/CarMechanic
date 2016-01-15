/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import database.DatabaseManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Alexander
 */
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        String dburl=context.getInitParameter("dbUrl");
        String dbusername=context.getInitParameter("dbUserName");
        String dbpassword=context.getInitParameter("dbPassword");

        DatabaseManager.createConnection(dburl, dbusername, dbpassword);
        System.out.println("Connection Establised To MySQL ");   
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DatabaseManager.closeConnection();
        System.out.println("Connection Closed To MySQL ");   
    }
    
}
