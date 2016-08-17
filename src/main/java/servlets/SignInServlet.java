package servlets;

//import accounts.AccountService;
//import accounts.UserProfile;
import dbService.DBException;
import dbService.DBService;
import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

//        private AccountService accountService;

  //      public SignInServlet(AccountService accountService){
//            this.accountService = accountService;
//        }

    private  DBService dbService;
    public SignInServlet(DBService dbService) { this.dbService = dbService;}

    public void doPost (HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

       String login = request.getParameter("login");
       String password = request.getParameter("password");



        try {
            Long userId = dbService.getIdByLogin(login);
            response.getWriter().print("User login :" + login + "\n" +
            " UserId : " + userId + "\n");

            UsersDataSet usersDataSet = dbService.getUser(userId);
            response.getWriter().println(usersDataSet.toString());
            if ((usersDataSet.getLogin()== null) || !password.equals(usersDataSet.getPassword())){
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("Unauthorized" );

            }
            else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Authorized: " + login);
                response.setStatus(HttpServletResponse.SC_OK);
            }

        } catch (DBException e) {
            e.printStackTrace();
        }


//        try {
            //give me userById

 //           long userId = dbService.getUserId(login);
//            UsersDataSet usersDataSet = dbService.getUser(1);
//            if ((usersDataSet == null) || !(password.equals(usersDataSet.getPassword()))){
//                response.setContentType("text/html;charset=utf-8");
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().println("Unauthorized" );
//
//                return;
//            }

//        }
//        catch (DBException e){
//            e.printStackTrace();
//        }

//        if ((profile == null) || (!password.equals(profile.getPass())) ){
//            response.setContentType("text/html;charset=utf-8");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().println("Unauthorized");
//            return;
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().println("Authorized: " + login);
//        response.setStatus(HttpServletResponse.SC_OK);

        }


    }



//        if (accountService.getUserByLogin(login).getLogin().equals(login)  &&
//                (accountService.getUserByLogin(login).getPass().equals(password)) ){
//            response.getWriter().println("Authorized:" + login);
//        }
//        else {
//            response.getWriter().println("Unauthorized:");
//        }

//}
