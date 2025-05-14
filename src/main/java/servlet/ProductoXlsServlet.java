package servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProdcutoServiceImplement;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import models.Producto;
import service.ProductoService;
import service.ProdcutoServiceImplement;

//Implementamos la anotacion
@WebServlet({"/productos.xls","/productos.json" ,"/prodictos.html"})

public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        ProductoService service = new ProdcutoServiceImplement();
        List<Producto> productos = service.listar();
        resp.setContentType("text/html;charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");
        boolean esJson = servletPath.endsWith(".json");
    if (esXls) {
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment; filename=productos.xls");
    } else if (esJson) {
        resp.setContentType("application/json");
        resp.setHeader("Content-Disposition", "attachment; filename=productos.json");
    } else {
        resp.setContentType("text/html");
    }
    try (PrintWriter out = resp.getWriter()) {
        if (!esXls) {
            //Crear la plantilla html
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Listado de Productos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Listado de Productos</h1>");
            out.println("<p><a href=\""+ req.getContextPath()+ "/productos.xls"+ "\"> Exportar a Excel</a> </p>");
            out.println("<p><a href=\""+ req.getContextPath() +"/productos.json"+ "\"> Mostar en JSON</a> </p>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Tipo</th>");
            out.println("<th>Precio</th>");
            out.println("</tr>");
            productos.forEach(p-> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                out.println("<td>" + p.getPrecio() + "</td>");
                out.println("</tr>");
            });
            out.println("</table>");
            if(!esXls) {
                out.println("</body>");
                out.println("</html>");

        }
        }
        }
    }
    }

