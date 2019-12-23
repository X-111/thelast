package com.htt.mapper1;

import java.util.ArrayList;
import com.htt.pojo.*;
public interface CategoryDao {
    public ArrayList<message> messageqq(Deline a);
    public void messageinsert(messageenty b);
    public ArrayList<friend> friendquery(String a);
    public void friendinsert(relation a);
    public ArrayList<String> passwordquery(String a);
    public  ArrayList<info> infoquery(String name);
    public void infoinsert(info a);
    public ArrayList<messageDownEnty> downmsquery(Deline a);
}

