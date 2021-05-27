<%--
  Created by IntelliJ IDEA.
  User: PSFHU_KOL
  Date: 2021. 05. 27.
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <title>Add Goal</title>
</head>
<t:page>
  <h1>Add Goal</h1>

  <form method="post">
    <label for="goalType">Choose Goal type</label>
    <select name="goalType" id="goalType">
      <option disabled selected value> -- select an option --</option>
      <option value="MainGoal">Main</option>
      <option value="SideGoal">Side</option>
    </select>
    <table>
      <tr>
        <td>Title:</td>
        <td><input type="text" name="title"/></td>
      </tr>
      <tr id="mainGoalIdTr" class="d-none">
        <td>
        <select name="mainGoalId" id="mainGoalId">
          <option disabled selected value> -- select an option --</option>
          <c:forEach items="${mainGoals}" var="mainGoal">
            <option value="<c:out value="${mainGoal.id}"/>"><c:out value="${mainGoal.title}"/></option>
          </c:forEach>
        </select>
        </td>
      </tr>
      <tr id="measureFieldIdTr" class="d-none">
        <td>
        <select name="measureFieldId" id="measureFieldId">
          <option disabled selected value> -- select an option --</option>
          <c:forEach items="${measureFields}" var="measureField">
            <option value="<c:out value="${measureField.id}"/>"><c:out value="${measureField.title}"/></option>
          </c:forEach>
        </select>
        </td>
      </tr>
      <tr id="goalValueTr" class="d-none">
        <td>Goal Value:</td>
        <td><input type="number" name="goalValue" id="goalValue"/></td>
      </tr>
      <tr>
        <td><input type="submit" id="submitButton" disabled/></td>
      </tr>
    </table>
  </form>
</t:page>
</html>

<script>
  function addEventHandler(elem, eventType, handler) {
    if (elem.addEventListener) {
      elem.addEventListener(eventType, handler, false);
    } else if (elem.attachEvent) {
      elem.attachEvent('on' + eventType, handler);
    }
  }
  addEventHandler(document, 'DOMContentLoaded', function () {
    addEventHandler(document.getElementById('goalType'), 'change', function () {
      if ("MainGoal" === this.value) {
        document.querySelector("#mainGoalIdTr").classList.add('d-none');
        document.querySelector("#mainGoalId").disabled = 'true';
        document.querySelector("#measureFieldIdTr").classList.add('d-none');
        document.querySelector("#measureFieldId").disabled = 'true';
        document.querySelector("#goalValueTr").classList.add('d-none');
        document.querySelector("#goalValue").disabled = 'true';
      } else if ("SideGoal" === this.value) {
        document.querySelector("#mainGoalIdTr").classList.remove('d-none');
        document.querySelector("#mainGoalId").removeAttribute('disabled');
        document.querySelector("#measureFieldIdTr").classList.remove('d-none');
        document.querySelector("#measureFieldId").removeAttribute('disabled');
        document.querySelector("#goalValueTr").classList.remove('d-none');
        document.querySelector("#goalValue").removeAttribute('disabled');
      }
      document.querySelector("#submitButton").removeAttribute('disabled')
    });
  });
</script>