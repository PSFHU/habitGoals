<%--
  Created by IntelliJ IDEA.
  User: PSFHU_KOL
  Date: 2021. 05. 28.
  Time: 0:54
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
    <label for="measureType">Choose Measure type</label>
    <select name="measureType" id="measureType">
      <option disabled selected value> -- select an option --</option>
      <option value="MeasureField">Field</option>
      <option value="MeasureValue">Value</option>
    </select>
    <table>
      <tr id="titleTr" class="d-none">
        <td>Title:</td>
        <td><input type="text" name="title" id="title"/></td>
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
      <tr id="measureValueTr" class="d-none">
        <td>Measure Value:</td>
        <td><input type="number" name="measureValue" id="measureValue"/></td>
      </tr>
      <tr id="dateTr" class="d-none">
        <td>Date:</td>
        <td>
          <input type="date" name="date" id="date">
        </td>
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
    addEventHandler(document.getElementById('measureType'), 'change', function () {
      if ("MeasureField" === this.value) {
        document.querySelector("#titleTr").classList.remove('d-none');
        document.querySelector("#title").removeAttribute('disabled');
        document.querySelector("#measureFieldIdTr").classList.add('d-none');
        document.querySelector("#measureFieldId").disabled = 'true';
        document.querySelector("#measureValueTr").classList.add('d-none');
        document.querySelector("#measureValue").disabled = 'true';
        document.querySelector("#dateTr").classList.add('d-none');
        document.querySelector("#date").disabled = 'true';
      } else if ("MeasureValue" === this.value) {
        document.querySelector("#titleTr").classList.add('d-none');
        document.querySelector("#title").disabled = 'true';
        document.querySelector("#measureFieldIdTr").classList.remove('d-none');
        document.querySelector("#measureFieldId").removeAttribute('disabled');
        document.querySelector("#measureValueTr").classList.remove('d-none');
        document.querySelector("#measureValue").removeAttribute('disabled');
        document.querySelector("#dateTr").classList.remove('d-none');
        document.querySelector("#date").removeAttribute('disabled');
      }
      document.querySelector("#submitButton").removeAttribute('disabled')
    });
  });
</script>
