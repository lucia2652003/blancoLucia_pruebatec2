package com.example.servlets;

import com.example.controllers.CiudadanoController;
import com.example.controllers.TurnoController;
import com.example.entities.Ciudadano;
import com.example.entities.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listadoTurno")
public class TurnoServlet extends HttpServlet {
    TurnoController tc = new TurnoController();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        List<Turno> listado = tc.findAll(); //Listado de turnos

        request.setAttribute("listado", listado);

        //Redirigir a la vista JSP
        request.getRequestDispatcher("turno.jsp").forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valor = request.getParameter("tipoEstado");

        System.out.println(valor);

        response.sendRedirect(request.getContextPath()+"/listadoTurno");
    }

}
