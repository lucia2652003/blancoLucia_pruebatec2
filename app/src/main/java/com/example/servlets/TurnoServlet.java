package com.example.servlets;

import com.example.controllers.TurnoController;
import com.example.entities.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//URL /app/listadoTurno
@WebServlet("/listadoTurno")
public class TurnoServlet extends HttpServlet {
    TurnoController tc = new TurnoController();

    //Obtener el listado
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        List<Turno> listado = tc.findAll(); //Listado de turnos

        request.setAttribute("listado", listado); //Establecer un parámetro para JSP, hay que castearlo

        //Redirigir a la vista JSP
        request.getRequestDispatcher("turno.jsp").forward(request, resp);
    }

    //Enviar parámetros y mostrar un resultado
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener los parámetros
        String estado = request.getParameter("tipoEstado");
        String fecha = request.getParameter("fecha");

        List<Turno> listado = tc.filtroTurno(estado, fecha);

        request.setAttribute("listado", listado);//Debe coincidir en el JSP
        request.getRequestDispatcher("turno.jsp").forward(request, response);
    }

}
