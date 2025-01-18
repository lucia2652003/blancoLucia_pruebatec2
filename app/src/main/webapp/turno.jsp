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
            <%@ include file="partials/header.jsp" %>
                <div class="presentacion">
                    <h1>Lista de turnos</h1>
                    <fieldset>
                        <legend>Buscar turnos</legend>
                        <form action="/app/listadoTurno" method="post">
                            <label for="fecha">Desde: <input type="date" id="fecha" name="fecha" /></label>
                            <label for="tipoEstado">
                                Selecciona Estado:
                                <select id="tipoEstado" name="tipoEstado">
                                    <option value="ESPERA">En espera</option>
                                    <option value="ATENDIDO">Atendido</option>
                                </select>
                            </label>
                            <button onclick="enviar()">Filtrar</button>
                        </form>
                    </fieldset>
                    <table>
                        <thead>
                            <th>Ciudadano</th>
                            <th>Nombre Turno</th>
                            <th>Fecha Turno</th>
                            <th>Estado Turno</th>
                        </thead>

                        <tbody>
                            <% List<Turno> listado = (List<Turno>) request.getAttribute("listado");
                                    for(Turno turno: listado) { %>
                                    <tr>
                                        <td>
                                            <%=turno.getCiudadano().getNombre() %>
                                                <%=turno.getCiudadano().getApellido() %>
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
                </div>

        </body>

        </html>