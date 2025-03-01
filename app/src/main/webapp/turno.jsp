<%-- Importar clases para interactuar en el HTML--%>
<%@ page import="java.util.List, com.example.entities.Turno, com.example.entities.Ciudadano" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Turnos</title>
    <link rel="stylesheet" href="./public/css/style.css">
    <script src="./public/js/index.js"></script>
</head>

<body>
    <a name="arriba"></a>
    <%@ include file="partials/header.jsp" %>
    
    <div class="presentacion">
        <h1>Lista de turnos</h1>
        <fieldset>
            <legend>Buscar turnos</legend>
            <form action="/app/listadoTurno" method="post">
                <label for="fecha">Fecha Desde: <input type="date" id="fecha" name="fecha" /></label>
                <br><br>
                <label for="tipoEstado">
                    Selecciona Estado:
                    <select id="tipoEstado" name="tipoEstado" class="filtra_select">
                        <option value="ESPERA">En espera</option>
                        <option value="ATENDIDO">Atendido</option>
                    </select>
                </label>
                <br><br>
                <button onclick="filtracion()">Filtrar</button>
            </form>
        </fieldset>
        
        <table>
            <thead>
                <th>Ciudadano</th>
                <th>N&uacute;mero Turno</th>
                <th>Nombre Turno</th>
                <th>Fecha Turno</th>
                <th>Estado Turno</th>
            </thead>
    
            <tbody>
                <%-- Listado de los turnos --%>
                <% List<Turno> listado = (List<Turno>) request.getAttribute("listado"); %>
                        <p class="total">Num. turnos: <%= listado.size() %></p>
                        <% for(Turno turno: listado) { %>
                        <tr>
                            <td>
                                <%=turno.getCiudadano().getNombre() %>
                                    <%=turno.getCiudadano().getApellido() %>
                            </td>
                            <td>
                                000000-0000<%=turno.getId() %>
                            </td>
                            <td>
                                <%=turno.getDescripcion() %>
                            </td>
                            <td>
                                <%=turno.getFecha() %>
                            </td>
                            <td>
                                <%=turno.getEstado() %>
                            </td>
                        </tr>
                        <% } %>
            </tbody>
        </table>
        <a href="#arriba"  class="subir">Volver arriba</a>
    </div>
    <%@ include file="partials/footer.jsp" %> 
    
</body>

</html>