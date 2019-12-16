package com.htt.controller;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ResponseBody;
import com.htt.mapper1.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Controller
public class messageController {
    @Autowired
    CategoryDao cd;
    private static ObjectMapper MAPPER = new ObjectMapper();
    @RequestMapping( value="/messagequery" )
    @ResponseBody
    public   String messqgeq(HttpServletRequest request) throws Exception {
        Deline p=new Deline();
        String uname=(String)request.getSession().getAttribute("uname");
        WebApplicationContext context=ContextLoader.getCurrentWebApplicationContext();
        ServletContext cn=context.getServletContext();
        long time= (long)cn.getAttribute(uname+"time");
        p.setLasttime(time);
        p.setReceiver(uname);
        System.out.println("start");
        ArrayList<message> s=cd.messagequery(p);
        long tim=0;
        for(int i=0;i<s.size();i++)
        {
            for(int j=0;j<s.get(i).getMessage1().size();j++)
                if(tim<s.get(i).getMessage1().get(j).getTime1())
                    tim=s.get(i).getMessage1().get(j).getTime1();

         }
        System.out.println(request.getSession().getAttribute("uname")+"  size"+s.size() +tim+"time    "+time);
        if(tim>0)
        {cn.setAttribute(uname+"time",tim);

        ;}

        String ljson = MAPPER.writeValueAsString(s);
        return ljson;
    }
    @RequestMapping( value="/messageinsert" )
    @ResponseBody
    public   void messqgei(HttpServletRequest request ,String sender,String receiver,String message) throws Exception {

    messageenty b=new messageenty();
    b.setMessage(message);
    b.setReceiver(receiver);
    b.setTime1(new Date().getTime());
    b.setSender(sender);
        System.out.println("insertstart"+b.getTime1()+b.getSender()+b.getReceiver()+b.getMessage());
        cd.messageinsert(b);
    System.out.println("insertenddddddddd");
    }
}
