package com.htt.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.ResponseBody;
import com.htt.mapper1.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htt.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
@Controller
public class infoController {
    @Autowired
    CategoryDao cd;
    private static ObjectMapper MAPPER = new ObjectMapper();
    @RequestMapping( value="/infoquery" )
    @ResponseBody
    public  String infoq(String name)throws Exception{
        ArrayList<info> s=cd.infoquery(name);
        String ljson = MAPPER.writeValueAsString(s);
        return ljson;
    }
    @RequestMapping( value="/infoinsert" )
    @ResponseBody
    public void  infoi(info a){
        cd.infoinsert(a);
    }
}
