<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
<div>
    <form action="javascript:void(0)">

        <input type="hidden" value="<c:out value="${employee.employeeId}"/>" id="employeeId" name="employeeId">
        <input type="hidden" value="${pageContext.request.contextPath}/saveOrUpdateEmployee" id="saveOrUpdateEmployeeUrl">
        <input type="hidden" value="${pageContext.request.contextPath}/employees" id="employeesRedirectUrl">

        <label for="fname">First name:</label>
        <input type="text" id="fname" name="fname">
        <br><br>
        <label for="lname">Last name:</label>
        <input type="text" id="lname" name="lname">
        <br><br>
        <label for="bdate">Date:</label>
        <input type="date" id="bdate">
        <br><br>
        <label for="gender">Gender</label>
        <select name="gender" id="gender">
            <option value="0">Female</option>
            <option value="1">Male</option>
        </select>
        <br><br>
        <label for="price">Price:</label>
        <input type="text" id="price">
        <br><br>
        <input type="submit" id="saveOrUpdateButton" value="Save Employee">

    </form>
</div>

<script src="<c:url value="/resources/js/employee.js"/>"></script>

</body>
</html>
