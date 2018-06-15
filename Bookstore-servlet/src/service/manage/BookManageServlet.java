package service.manage;

import entity.Book;
import entity.User;
import model.ShoppingItem;
import model.Shoppingcart;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(name = "BookManageServlet",urlPatterns = "/bookmanage")
public class BookManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//Solve the problem of chinese random code
        PrintWriter writer = response.getWriter();
        HttpSession userSession = request.getSession(false);
        if(userSession==null){
            return;
        }
        User user = (User) userSession.getAttribute("user");
        if(user == null || !user.getRole().equals("manager")){
            return;
        }
        Session session = null;
        Transaction tx = null;
        String method = request.getParameter("method");
        if(method.equals("modify")){
            int id = Integer.valueOf(request.getParameter("id"));
            String bookname = null;
            String author = null;
            String type = null;
            BigDecimal price = null;
            int amount = -1;
            if(request.getParameter("bookname")!=null) {
                bookname = request.getParameter("bookname");
            }
            if(request.getParameter("author")!=null) {
                author = request.getParameter("author");
            }
            if(request.getParameter("type")!=null) {
                type = request.getParameter("type");
            }
            if(request.getParameter("price")!=null && !request.getParameter("amount").equals("")) {
                 price = BigDecimal.valueOf(Double.valueOf(request.getParameter("price")));
            }
            if(request.getParameter("amount")!=null && !request.getParameter("amount").equals(""))
                amount = Integer.valueOf(request.getParameter("amount"));
            try{
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                // Get Query object,begin transaction
                Query query = session.createQuery("from Book where id=?");
                //Fill the placeholder
                query.setParameter(0, id);
                Book book = null;
                try {
                    book = (Book) query.getSingleResult();
                }catch (NoResultException e){
                    book =null;
                    tx.rollback();
                }
                //Handle the query result
                if(book!=null){
                    if(amount!=-1) book.setAmount(amount);
                    if(type!=null && !type.equals("")) book.setType(type);
                    if(bookname!=null && !bookname.equals("")) book.setBookname(bookname);
                    if(author!=null && !author.equals("")) book.setAuthor(author);
                    if(price!=null) book.setPrice(price);
                    session.update(book);
                    tx.commit();
                    writer.print("success");
                }
                else{
                    writer.print("failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                if(tx!=null) tx.rollback();
            } finally {
                if(writer!=null) writer.close();
            }
        }else if(method.equals("add")){
            String bookname = request.getParameter("bookname");
            String author = request.getParameter("author");
            String type = request.getParameter("type");
            BigDecimal price = BigDecimal.valueOf(Double.valueOf(request.getParameter("price")));;
            int amount = Integer.valueOf(request.getParameter("amount"));
            Book book = new Book();
            book.setAmount(amount);
            book.setBookname(bookname);
            book.setPrice(price);
            book.setAuthor(author);
            book.setType(type);
            book.setSaleAmount(0);
            try{
                session = HibernateUtil.getSessionFactory().getCurrentSession();
                tx = session.beginTransaction();
                session.save(book);
                tx.commit();
                writer.write("success");
            } catch (Exception e) {
                e.printStackTrace();
                if(tx!=null) tx.rollback();
            } finally {
                if(writer!=null) writer.close();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
