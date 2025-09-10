/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import hibernate.City;
import hibernate.HibernateUtil;
import hibernate.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hiran
 */
@WebServlet(name = "HibernateStart", urlPatterns = {"/HibernateStart"})
public class HibernateStart extends HttpServlet {

    //do get method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        //get the added city
        Criteria c1 = session.createCriteria(City.class);
        c1.add(Restrictions.eq("city_name","City A"));
        City city = (City) c1.uniqueResult();
        
        //Now we can insert a user
        User user = new User();
        user.setFirst_name("user1");
        user.setLast_name("user2");
        user.setEmail("user@gmail.com");
        user.setPassword("ABC");
        user.setCity(city);
        
        session.save(user);
        session.beginTransaction().commit();
        session.close();
        
    }

    

}
