<%-- 
    Document   : ShowTable-Host-BAD
    Created on : Nov 18, 2018, 12:28:49 AM
    Author     : Shawn Emami
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="entity.Host"%>
<%@page import="logic.HostLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Servlet Sample2Servlet</title>
        <!-- https://www.w3schools.com/css/css_table.asp -->
        <link rel="stylesheet" type="text/css" href="style/tablestyle.css">
        <script>
            var isEditActive = false;
            var activeEditID = -1;
            function createTextInput(text, name) {
                var node = document.createElement("input");
                node.name = name;
                node.className = "editor";
                node.type = "text";
                node.value = text;
                return node;
            }
            window.onload = function () {
                var elements = document.getElementsByClassName("edit");
                for (let i = 0; i < elements.length; i++) {
                    elements[i].childNodes[0].onclick = function () {
                        var id = elements[i].id;
                        if (isEditActive) {
                            if (activeEditID === id) {
                                this.type = "submit";
                            }
                            return;
                        }
                        isEditActive = true;
                        activeEditID = id;
                        this.value = "Update";
                        
                        var idCell = document.getElementById(++id);
                        var nameCell = document.getElementById(++id);
                        
                        var idInput = createTextInput(idCell.innerText, "<%=HostLogic.ID%>");
                        idInput.readOnly = true;
                        var nameInput = createTextInput(nameCell.innerText, "<%=HostLogic.NAME%>");
                        
                        idCell.innerText = null;
                        nameCell.innerText = null;
                        
                        idCell.appendChild(idInput);
                        nameCell.appendChild(nameInput);
                    };
                }
            };
        </script>
    </head>
    <body>
        <!-- https://www.w3schools.com/css/css_table.asp -->
        <form method="post">
            <table align="center">
                <tr>
                    <td><input type="text" name="searchText" /></td>
                    <td><input type="submit" name="search" value="Search" /></td>
                </tr>
            </table>

            <table align="center" border="1">
                <tr>
                    <th><input type="submit" name="delete" value="Delete" /></th>
                    <th>Edit</th>
                    <th>ID</th>
                    <th>Name</th>
                </tr>
                <%
                    HostLogic logic = new HostLogic();
                    List<Host> hosts = logic.getAll();
                    long counter = 0;
                    for (Host  host: hosts) {
                %>
                <tr>
                    <td class="delete">
                        <input type="checkbox" name="deleteMark" value="<%=host.getId()%>" />
                    </td>
                    <td class="edit" id="<%=counter++%>" ><input class="update" type="button" name="edit" value="Edit" /></td>
                    <td class="ID" id="<%=counter++%>" ><%=host.getId()%></td>
                    <td class="Name" id="<%=counter++%>" ><%=host.getName()%></td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <th><input type="submit" name="delete" value="Delete" /></th>
                    <th>Edit</th>
                    <th>ID</th>
                    <th>Name</th>
                </tr>
            </table>
        </form>
        <div style="text-align: center;"><pre><%=toStringMap(request.getParameterMap())%></pre></div>
    </body>
</html>
<%!
    private String toStringMap(Map<String, String[]> m) {
        StringBuilder builder = new StringBuilder();
        for (String k : m.keySet()) {
            builder.append("Key=").append(k)
                    .append(", ")
                    .append("Value/s=").append(Arrays.toString(m.get(k)))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
%>
