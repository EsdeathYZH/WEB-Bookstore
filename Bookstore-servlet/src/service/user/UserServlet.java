package service.user;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet",urlPatterns = "/profile")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//Solve the problem of chinese random code
        HttpSession userSession = request.getSession(false);
        PrintWriter writer = response.getWriter();
        if(userSession==null){
            writer.print("false");
        }else{
            JSONObject user = JSONObject.fromObject(userSession.getAttribute("user"));
            writer.print(user);
        }
        writer.close();
    }
}
