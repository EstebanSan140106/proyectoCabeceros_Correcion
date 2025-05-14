package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProdcutoServiceImplement;
import service.ProductoService;
import models.Producto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet({"/productos.xls","/productos.json","/productos.html"})
public class ProductoXlsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        ProductoService service = new ProdcutoServiceImplement();
        List<Producto> productos = service.listar();

        String servletPath = req.getServletPath();
        boolean esXls = servletPath.endsWith(".xls");
        boolean esJson = servletPath.endsWith(".json");

        if (esXls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment; filename=productos.xls");
        } else if (esJson) {
            resp.setContentType("application/json;charset=UTF-8");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
        }

        try (PrintWriter out = resp.getWriter()) {
            if (esJson) {
                // Generar JSON simple
                out.println("[");
                for (int i = 0; i < productos.size(); i++) {
                    Producto p = productos.get(i);
                    out.println("  {");
                    out.println("    \"id\": " + p.getId() + ",");
                    out.println("    \"nombre\": \"" + p.getNombre() + "\",");
                    out.println("    \"tipo\": \"" + p.getTipo() + "\",");
                    out.println("    \"precio\": " + p.getPrecio());
                    out.print("  }");
                    if (i < productos.size() - 1) {
                        out.println(",");
                    } else {
                        out.println();
                    }
                }
                out.println("]");
            } else {
                // Para HTML y XLS generamos tabla HTML
                if (!esXls) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset=\"UTF-8\">");
                    out.println("<title>Listado de Productos</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Listado de Productos</h1>");
                    out.println("<p><a href=\"" + req.getContextPath() + "/productos.xls\">Exportar a Excel</a></p>");
                    out.println("<p><a href=\"" + req.getContextPath() + "/productos.json\">Mostrar en JSON</a></p>");
                }

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>Nombre</th>");
                out.println("<th>Tipo</th>");
                out.println("<th>Precio</th>");
                out.println("</tr>");

                for (Producto p : productos) {
                    out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getNombre() + "</td>");
                    out.println("<td>" + p.getTipo() + "</td>");
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");

                if (!esXls) {
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        }
    }
}
