<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head><title>SpringBoot</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<hr/>
<form method="post" action="/save">
    <%--<input type="hidden" name="id" value="${lists.id}"/><br/>--%>
    name:<br>
    <input type="text" name="name" value="${lists.name}"/>
    <br>
    description:<br>
    <input type="text" name="description" value="${lists.description}">
    <br>
    category:<br>
    <input type="text" name="category" value="${lists.category}">
    <br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>