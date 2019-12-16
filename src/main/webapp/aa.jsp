<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.mail.Session" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/3
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <TITLE> New Document </TITLE>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <META NAME="Author" CONTENT="WUBO">
    <META NAME="Keywords" CONTENT="ResultList">
    <link rel="stylesheet" rev="stylesheet" href="result.css" type="text/css">
    <script type="text/javascript" src="resultList.js" defer="defer"></script>
    <style>
        .div0{
            margin-left:650px;
            margin-top: 30px;
            height:730px;
            width: 550px;
        }
        .div1{
            background: #1c81ff;
            border: 1px solid;
            height:50px ;
            line-height: 50px;
        }
        .div2{
            border: 1px solid;
            height:450px ;
            text-align: right;
            overflow: auto;
        }
        .div3{
            position: relative;
            border: 1px solid;
            height: 230px;
        }
        p{
            text-align: center;
        }
        .circle1{
            position: absolute;
            min-width: 10px;
            height: 10px;
            background: red;
            box-sizing: border-box;//必须是border-box，自行体会
        color: white;
            font-size: 10px;
            text-align: center;
            line-height: 10px;
            padding: 0 5px;
            border-radius: 5px;
            right: 15px;
            display: inline-block;
        }
    </style>
</head>
<body>
<%
    int length=(int)session.getAttribute("length");
%>
<script>
    var number;
    var linkman;
    var  myObj;
    var messages=0;
    var info;
    var br1=document.createTextNode("<br>");
    (function () {
        setInterval(getmessage, 1000);
        setInterval(receiver, 1000);
        getlink();
        window.onload=function () {
            var timer,dd;
            for(var i=0;i<<%=length%>;i++){
                dd=document.getElementById("s"+i);
                dd.onmouseover=function () {
                    timer=setTimeout(function () {
                        document.getElementById("div"+i).style.display="block";
                    },1000);
                }
                dd.onmouseout=function () {
                    clearTimeout(timer);
                    document.getElementById("div"+i).style.display="none";
                }
            }
        }
    })();
    function receiver() {
        if(messages>0){
            var i;
            for(i=0;i<number;i++){
                for(var j=0;j<messages;j++){
                    if(myObj[j].sender==linkman[i].cname){
                    var date=new Date();
                    var b2=document.getElementById("b"+i);
                    var p2=document.createElement("p");
                    var di=document.createElement("div");
                    di.style.textAlign="left";
                    p2.innerHTML=date.toLocaleString();
                    for(var l=0;l<myObj[j].message1.length;l++){
                        b2.appendChild(p2);
                        di.innerHTML=di.innerHTML+"<br>"+myObj[j].message1[l].message;
                    }
                    b2.appendChild(di);
                }
                }
            }
        }
    }
    function getmessage() {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function()  {
            if (xmlhttp.readyState == 4) {
                myObj=JSON.parse(xmlhttp.responseText);
                messages=myObj.length;
            }
        }
        xmlhttp.open("GET", "messagequery", true);
        xmlhttp.send();
    }
    function getlink() {
        var xmlhttp1 = new XMLHttpRequest();
        xmlhttp1.onreadystatechange = function()  {
            if (xmlhttp1.readyState == 4 ) {
                linkman=JSON.parse(xmlhttp1.responseText);
                number=linkman.length;
            }
        }
        xmlhttp1.open("GET", "friendquery", true);
        xmlhttp1.send();
    }
    function getinformation(n) {
        var xmlhttp2 = new XMLHttpRequest();
        xmlhttp2.onreadystatechange = function()  {
            if (xmlhttp2.readyState == 4 ) {
                info=JSON.parse(xmlhttp2.responseText);
            }
        }
        xmlhttp2.open("GET", "infoquery?name="+n, true);
        xmlhttp2.send();
    }
    function x() {
        for(var j=0;j<number;j++){
            document.getElementById("s"+j).innerHTML=linkman[j].cname;
            document.getElementById("a"+j).innerHTML=linkman[j].cname;
        }
    }
    function showInfo(n) {
        for(var m=0;m<number;m++){
            document.getElementById("dv"+m).style.display="none";
        }
        document.getElementById("dv"+n).style.display="block";
    }
    function lol(l) {
        var date=new Date();
        var p1=document.createElement("p");
        p1.innerHTML=date.toLocaleString();
        p1.appendChild(br1);
        var d2=document.getElementById("b"+l);
        var info=document.getElementById("info"+l);
        var val=document.createTextNode(info.value);
        info.appendChild(br1);
        d2.appendChild(p1);
        d2.appendChild(val);
        var sender=document.getElementById("ResultTitle").innerText;
        var receiver=linkman[l].cname;
        var message=info.value;
        var time=date.getTime();
        info.value="";
        var xmlhttp2 = new XMLHttpRequest();
        xmlhttp2.onreadystatechange = function()  {
            if (xmlhttp2.readyState == 4 ) {
            }
        }
        xmlhttp2.open("GET", "messageinsert?sender="+sender+"&receiver="+receiver+"&message="+message, true);
        xmlhttp2.send();
    }
    function kpl(m) {//m表示第几个联系人
        var date=new Date();
        alert(myObj[m].sender);
        var b2=document.getElementById("b"+m);
        var p2=document.createElement("p");
        var di=document.createElement("div");
        di.style.textAlign="left";
        p2.innerHTML=date.toLocaleString();
        for(var i=0;i<myObj[m].message1.length;i++){
            b2.appendChild(p2);
            di.innerHTML=di.innerHTML+"<br>"+myObj[m].message1[i].message;
        }
        b2.appendChild(di);
    }
</script>
<div id="resultPanel">
    <div id="ResultTitle" style="cursor: pointer;" onclick="showResultBody();x()"align="center"><%=session.getAttribute("uname")%></div>
    <div id="ResultBody" style="display: none;" >
        <table id="secTable" width="250" class="wc" style="font-size:12px;">
            <tr>
                <th id="cl01" class="result03 result01" onclick="secBoard(1)"align="center">联系人</th>
                <th id="cl02" class="result03 result02" onclick="secBoard(2)"align="center">消息</th>
            </tr>
            <tr>
                <td class="border" colspan="2">
                    <div id="tbx01">
                        <%
                            int number=(int)session.getAttribute("length");
                            for(int i=0;i<number;i++){
                        %>
                        <span onclick="showInfo(<%=i%>)"id="s<%=i%>"></span>
                        <%}%>
                    </div>
                    <div id="tbx02">
                        <div class="tbx03">
                            <div class="circle1"></div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
<%
    for(int j=0;j<number;j++){
%>
<div id="dv<%=j%>" style="display: none;z-index: 1"class="div0">
    <div class="div1"align="center"id="a<%=j%>"></div>
    <div class="div2"id="b<%=j%>"></div>
    <div class="c<%=j%>"style="position: relative">
        <textarea id="info<%=j%>" style="height:230px;width: 550px;resize: none"></textarea>
            <input type="button"  onclick="lol(<%=j%>)"style="position:absolute;bottom: 0px;right: 0px"value="发送"id="p<%=j%>">
    </div>
</div>
<div id="div<%=j%>"style="position:absolute;top:30px;left: 520px;height: 200px;width: 200px;border: 1px solid #C37834;display: block;z-index: 999;background: #16edff;overflow: hidden">
    联系人资料卡片<br>
</div>
<script>
    getinformation(linkman[<%=j%>].cname);
    var p=document.getElementById("div<%=j%>");
    p.innerHTML=p.innerHTML+"年龄："+info[0].age+"<br>"+"姓名："+info[0].uname+"<br>"+"地址："+info[0].address+"<br>"+"性别："+info[0].gender+"<br>"+"备注："+info[0].instruction+"<br>";
</script>
<%}%>
</body>
</html>
