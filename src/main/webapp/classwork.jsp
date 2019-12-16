<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/12/11
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>手机移动支付业务问卷调查</h2>
<hr />
<form>
    1.您的教育程度是？<br>
    <input type="radio"name="education"checked="checked">高中
    <input type="radio"name="education">大专
    <input type="radio"name="education">本科
    <input type="radio"name="education">硕士
    <input type="radio"name="education">博士及以上<br>
    2.您的年龄是？<br>
    <input type="radio"name="age">18岁以下
    <input type="radio"name="age">18-25岁
    <input type="radio"name="age">26-30岁
    <input type="radio"name="age">31-35岁
    <input type="radio"name="age">35岁以上<br>
    3.您是否使用过移动支付业务？<br>
    <input type="radio"name="use">从未听说过
    <input type="radio"name="use">听说过但是未使用过
    <input type="radio"name="use">偶尔使用
    <input type="radio"name="use">经常使用<br>
    4.您了解以下哪些移动支付业务？<br>
    <input type="checkbox"name="business">支付宝
    <input type="checkbox"name="business">微信支付
    <input type="checkbox"name="business">电信翼支付
    <input type="checkbox"name="business">Apple Pay
    <input type="checkbox"name="business">以上均不了解<br>
    <input type="submit"value="提交">
</form>
</body>
</html>
