//package main;
//
//
//import dbService.DBException;
//import dbService.DBService;
//import dbService.dataSets.UsersDataSet;
//
//
//public class Main {
//    public static void main(String[] args) {
//        DBService dbService = new DBService();
//        dbService.printConnectInfo();
//        try {
//            long userId = dbService.addUser("tully");
//            System.out.println("Added user id: " + userId);
//
//            UsersDataSet dataSet = dbService.getUser(userId);
//            System.out.println("User data set: " + dataSet);
//
//            dbService.cleanUp();
//        } catch (DBException e) {
//            e.printStackTrace();
//        }
//    }
//}

package main;

//import accounts.AccountService;
import dbService.DBService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {

    public static void main(String[] args) throws Exception {
       // AccountService accountService = new AccountService();
        DBService dbService = new DBService();


        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SignInServlet(dbService)), "/signin");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(dbService)), "/signup");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("pages");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, contextHandler});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }


}