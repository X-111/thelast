package com.htt.controller;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

import com.htt.mapper1.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {
    @Autowired
    CategoryDao cd;
    private static ObjectMapper MAPPER = new ObjectMapper();
    @RequestMapping( value="/login" )
    public ModelAndView login(HttpServletRequest req, String uname, String password){
        System.out.println("success");
        ArrayList<String> p1=cd.passwordquery(uname);

        if(p1.get(0).equals(password)){
            req.getSession().setAttribute("uname",uname);
            ArrayList<friend> s=cd.friendquery((String)req.getSession().getAttribute("uname"));
            req.getSession().setAttribute("length", s.size());
            WebApplicationContext context= ContextLoader.getCurrentWebApplicationContext();
            ServletContext cn=context.getServletContext();
           cn.setAttribute(uname+"time",new Date().getTime());
            ModelAndView m1=new ModelAndView("aa.jsp");
            return m1;
        }
        else {
            ModelAndView m2=new ModelAndView("/index.jsp");
            return m2;
        }
    }
}
