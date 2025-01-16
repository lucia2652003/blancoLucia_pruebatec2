<%-- Importar clases para interactuar en el HTML--%>
<%@ page import="java.util.List, com.example.entities.Turno, com.example.entities.Ciudadano" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Turnos</title>
</head>
<body>
  <h1>Listado de personas</h1>
  
  <table>
    <thead>
        <th>Nombre Ciudadano</th>
        <th>Nombre Turno</th>
        <th>Fecha Turno</th>
        <th>Estado Turno</th>
    </thead>
    <tbody>
        <% List<Turno> listado = (List<Turno>) request.getAttribute("listado");
            for(Turno turno: listado) { %>
                <tr>
                    <td><%=turno.getCiudadano().getNombre() %> <%=turno.getCiudadano().getApellido() %></td>
                    <td><%=turno.getDescripcion() %></td>
                    <td><%=turno.getFecha() %></td>
                    <td><%=turno.getEstado() %></td>
                </tr>
        <% } %>
    </tbody>
  </table>   
</body>
</html>