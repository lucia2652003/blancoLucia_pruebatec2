<%-- Importar clases para interactuar en el HTML--%>
    <%@ page import="java.util.List, com.example.entities.Turno, com.example.entities.Ciudadano" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Crear de Turnos</title>
            <link rel="stylesheet" href="./public/css/style.css">
        </head>

        <body>
            <%@ include file="partials/header.jsp" %>
                <div class="presentacion">
                    <h1>Creaci&oacute;n de turnos</h1>
                    <fieldset>
                        <legend>Introduce los par&oacute;metros</legend>
                        <form action="/app/crearTurno" method="post" class="anhadir_turno">
                            <label for="opciones">
                                <%-- Lista de los ciudadanos, aunque necesitamos el id para interactuar en la DB --%>
                                    Selecciona un ciudadano: <select id="opciones" name="cliente">
                                        <% List<Ciudadano> clientes = (List<Ciudadano>)
                                                request.getAttribute("clientes");
                                                for(Ciudadano cliente: clientes ) { %>
                                                <option value="<%=cliente.getId()%>">
                                                    <%= cliente.getNombre()%>
                                                        <%= cliente.getApellido()%>
                                                </option>
                                                <% } %>
                                    </select>
                            </label>
                            <br>
                            <label for="descripcion">
                                Descripci&oacute;n <input type="text" placeholder="Ingrese descripci&oacute;n"
                                    name="descripcion" id="descripcion">
                            </label>
                            <br>
                            <label for="fecha">
                                Fecha <input type="date" id="fecha" name="fecha" />
                            </label>
                            <br>
                            <label for="tipoEstado">
                                Selecciona Estado:
                                <select id="tipoEstado" name="tipoEstado">
                                    <option value="ESPERA">En espera</option>
                                    <option value="ATENDIDO">Atendido</option>
                                </select>
                            </label>
                            <br><br>
                            <%-- Al pulsar se envia al servlet y actualiza la DB y lo devuelve a la lista. --%>
                                <input type="submit" value="Enviar turno">
                        </form>
                    </fieldset>
                </div>
        </body>

        </html>