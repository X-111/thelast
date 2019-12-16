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
import com.htt.services.*;
import com.htt.pojo.*;
@Controller
public class DownloadController {
    @Autowired
    private CategoryDao cd;
    @RequestMapping( value="/downloadmg" )
    @ResponseBody
    public void down(Deline a,HttpServletResponse rsp){
        System.out.println("download1");

        ArrayList<messageDownEnty> s=cd.downmsquery(a);
        String [] rowname={"sender","receiver","time","message"};
        String filename="message"+".xls";
        String title="message";
        System.out.println("download"+a.getSender()+a.getReceiver()+"xx"+s.size());
        downloadServ d=new downloadServ(title,rowname,s,rsp,filename);
        try {
            d.downloadExcel();
        }
        catch ( Exception e){
            System.out.println("error");
        }




    }


}
