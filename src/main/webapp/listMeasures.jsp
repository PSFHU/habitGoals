<%--
  Created by IntelliJ IDEA.
  User: PSFHU_KOL
  Date: 2021. 05. 27.
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <title>Measure List</title>
</head>
<t:page>
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
                        <td><c:out value="${measureValue.timeStamp}"/></td>
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
</t:page>
</html>
