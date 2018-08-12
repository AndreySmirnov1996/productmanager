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
<form method="put" action="/update">
    <input type="hidden" name="id" value="${list.id}"/><br/>
    name:<br>
    <input type="text" name="name" value="${list.name}"/>
    <br>
    description:<br>
    <input type="text" name="description" value="${list.description}">
    <br>
    category:<br>
    <input type="text" name="category" value="${list.getCategories()}" >
    <br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>