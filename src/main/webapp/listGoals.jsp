<%--
  Created by IntelliJ IDEA.
  User: PSFHU_KOL
  Date: 2021. 05. 27.
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <title>Goal List</title>
</head>
<t:page>
    <div id="values">
        <c:forEach items="${goals}" var="goal">
            <table>
                <thead>
                <tr>
                    <td><c:out value="${goal.title}"/></td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${goal.sideGoals}" var="sideGoal">
                    <tr>
                        <td><c:out value="${sideGoal.title}"/></td>
                        <td><c:out value="${sideGoal.goalValue}"/></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td><a href="${pageContext.request.contextPath}/addGoal">Add</a></td>
                </tr>
                </tbody>
            </table>
        </c:forEach>
    </div>
</t:page>
</html>
