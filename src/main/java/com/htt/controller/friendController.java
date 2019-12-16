package com.htt.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import com.htt.mapper1.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htt.pojo.*;
   @Controller
public class friendController {
       @Autowired
    private CategoryDao cd;
    private static ObjectMapper MAPPER = new ObjectMapper();
    @RequestMapping( value="/friendquery" )
    @ResponseBody
    public String friendq(HttpServletRequest request ) throws Exception {
        //System.out.println("alert");

       ArrayList<friend> s=cd.friendquery((String)request.getSession().getAttribute("uname"));
                  request.getSession().setAttribute("length", s.size());

            String ljson = MAPPER.writeValueAsString(s);
           // System.out.println("bug"+s.get(0).getCname()+" x"+s.get(0).getMname());
            return ljson;
    }
    @RequestMapping( value="/friendinsert" )
    @ResponseBody
    public   void friendi(HttpServletRequest request, HttpServletResponse response,relation a) throws Exception {
        cd.friendinsert(a);
    }


}

