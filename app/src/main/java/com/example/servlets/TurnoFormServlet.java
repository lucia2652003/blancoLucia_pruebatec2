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

@WebServlet("/listadoTurno")
public class TurnoFormServlet extends HttpServlet {

    TurnoController tc = new TurnoController();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        List<Turno> listado = tc.findAll();

        listado.forEach(turno -> {
            System.out.println("Fecha "+turno.getFecha());
            System.out.println("Descripcion "+turno.getDescripcion());
            System.out.println("Ciudadano "+turno.getCiudadano().getNombre());
        });

        request.setAttribute("listado", listado);

        request.getRequestDispatcher("turno.jsp").forward(request, resp);
    }
}
