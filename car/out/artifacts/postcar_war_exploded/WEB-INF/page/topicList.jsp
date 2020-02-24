<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2020/2/2
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>全部帖子</title>
</head>
<body>
    <h2><a href="/addTopic.do?service=addTopic">我要发帖</a></h2>
    <table  >
        <tr  align="left">
            <th>标题</th>
            <th>发帖人</th>
            <th>发帖时间</th>
            <th>浏览量</th>

        </tr>
       <c:forEach  items="${topicList}" var="topic" >
           <tr align="left">
               <td>${topic.title}</td>
               <td>${topic.author}</td>
               <td><f:formatDate value="${topic.createDate}"  pattern="yyyy-MM-dd HH:mm:ss"></f:formatDate></td>
               <td>${topic.clickAmount}</td>
           </tr>
       </c:forEach>

    </table>

</body>
</html>
