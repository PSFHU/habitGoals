<%--
  Created by IntelliJ IDEA.
  User: PSFHU_KOL
  Date: 2021. 05. 27.
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<t:page>
    <c:if test="${!nodata}">
    <table id="goals">
        <thead>
        <tr <c:if test="${mainGoalCompleted}">style="background-color: green" </c:if>>
            <td>  <c:out value="${mainGoal.getTitle()}"/></td>
        </tr>
        </thead>
        <t:sideGoalList/>
    </table>
    <div id="values">
    <c:forEach items="${measureFields}" var="measureField">
        <table>
            <thead>
            <tr>
                <td><c:out value="${measureField.title}"/></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${measureField.measureValues}" var="measureValue">
                <tr>
                    <td><c:out value="${measureValue.date}"/></td>
                    <td><c:out value="${measureValue.value}"/></td>
                </tr>
            </c:forEach>
            <tr>
                <td><a href="${pageContext.request.contextPath}/addMeasure">Add</a></td>
            </tr>
            </tbody>
        </table>
    </c:forEach>
    </div>
    </c:if>
    <c:if test="${nodata}">
        <h1>Sorry theres no data at the moment!</h1>
        <h2>Go to the Goals or Measures to add data.</h2>
    </c:if>
</t:page>
</html>
