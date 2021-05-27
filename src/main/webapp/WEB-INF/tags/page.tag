<%@ tag language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>HabitGoals</title>
</head>
<body>
<div>
    <h3>HabitGoals</h3>
    <a href="${pageContext.request.contextPath}/">Dashboard</a>
    <a href="${pageContext.request.contextPath}/listGoals">Goals</a>
    <a href="${pageContext.request.contextPath}/listMeasures">Measures</a>
    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    <div>
        <jsp:doBody/>
    </div>
</div>
</body>
<style>
    #goals{
        float: left;
    }
    #values{
        float: left;
    }
    #values table{
        float: left;
    }

    td {
        border: 1px solid black;
    }

    td a{
        width: 100%;
        display: block;
        text-align: center;
    }

    thead > tr > td {
        font-weight: bold;
    }

    .d-none {
        display: none;
    }
</style>
</html>
