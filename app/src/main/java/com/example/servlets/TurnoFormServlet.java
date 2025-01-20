package com.example.servlets;

import com.example.controllers.CiudadanoController;
import com.example.controllers.TurnoController;
import com.example.entities.Ciudadano;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//Para establecer en URL a donde se va a dirigir
@WebServlet("/crearTurno")
public class TurnoFormServlet extends HttpServlet {

    CiudadanoController ciudadanoController = new CiudadanoController();
    TurnoController turnoController = new TurnoController();

    //Para obtener informacíon y mostrarla
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ciudadano> listadoClientes = ciudadanoController.findAll();

        request.setAttribute("clientes", listadoClientes);

        request.getRequestDispatcher("formTurno.jsp").forward(request,response);
    }

    //Enviar valores de los formularios y mostrarlos por la página web
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Coger los parámetros de Turno
        String fecha = req.getParameter("fecha");
        String descripcion = req.getParameter("descripcion");
        String estado = req.getParameter("tipoEstado");
        Long ciu = Long.valueOf(req.getParameter("cliente"));
        Ciudadano ciudadano = new Ciudadano(ciu, null, null, null);

        turnoController.create(fecha,descripcion,estado,ciudadano);

        //Redirigir a la vista de listadoTurno para mostrarlo, se establece al final si está creado o no
        resp.sendRedirect(req.getContextPath()+"/listadoTurno");
    }
}
