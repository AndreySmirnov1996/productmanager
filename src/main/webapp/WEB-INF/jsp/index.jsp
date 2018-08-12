<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>TestTaskROI</title>
    <style>
        /*table {*/
            /*font-family: arial, sans-serif;*/
            /*border-collapse: collapse;*/
            /*width: 100%;*/
        /*}*/
        /*td, th {*/
            /*border: 1px solid #dddddd;*/
            /*text-align: left;*/
            /*padding: 8px;*/
        /*}*/
        /*tr:nth-child(even) {*/
            /*background-color: #dddddd;*/
        /*}*/
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            border-radius: 10px;
            border-spacing: 0;
            text-align: center;
        }
        th {
            background: #8dd4eb;
            color: white;
            text-shadow: 0 1px 1px #2D2020;
            padding: 10px 20px;
        }
        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        th:first-child, td:first-child {
            text-align:center;
        }
        th:first-child {
            border-top-left-radius: 10px;
        }
        th:last-child {
            border-top-right-radius: 10px;
            border-right: none;
        }
        td {
            padding: 10px 20px;
            background: #d9f8f5;
        }
        tr:last-child td:first-child {
            border-radius: 0 0 0 10px;
        }
        tr:last-child td:last-child {
            border-radius: 0 0 10px 0;
        }
        tr td:last-child {
            border-right: none;
        }
    </style>
</head>
<body>

<table>
    <tr>
        <th>name</th>
        <th>description</th>
        <th>category</th>
        <th>action</th>
    </tr>
    <c:forEach var = "list" items = "${lists}">
        <tr>
            <td>${list.name}</td>
            <td>${list.description}</td>
            <td>${list.category}</td>
            <td>
                <a href="/edit/${list.id}">Edit</a>
                <a href="/delete/${list.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<hr/>

<form method="post" action="/save">
    <input type="hidden" name="id" value=""/>
    name:<br>
    <input type="text" name="name"/>
    <br>
    description:<br>
    <input type="text" name="description" >
    <br>
    category:<br>
    <input type="text" name="category" >
    <br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>