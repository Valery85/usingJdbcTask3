package servlets;

//import accounts.AccountService;
//import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
//    private AccountService accountService;

//    public SignUpServlet(AccountService accountService) {
//        this.accountService = accountService;
//    }
    private  DBService dbService;
    public SignUpServlet(DBService dbService) { this.dbService = dbService;}


    public void doPost (HttpServletRequest request, HttpServletResponse response){
        String login = request.getParameter("login");
        String password = request.getParameter("password");

/*      UserProfile userProfile = new UserProfile(login,pass);
        accountService.addNewUser(userProfile);
*/
        try {
            Long userId = dbService.addUser(login, password);
            response.getWriter().println("UserID : " + userId);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//           long userId = dbService.addUser(login, password);
//            System.out.println(userId);
//        }
//        catch (DBException e){
//            e.printStackTrace();
//        }

//        if (login == null || pass == null) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        }

    }
}
