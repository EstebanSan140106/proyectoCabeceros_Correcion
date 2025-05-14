package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF -8");
        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI();
        String requesturl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String serlvetPath = req.getServletPath();
        String ip = req.getLocalAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("host");
        String url = scheme + "://" + host + " : " + contextPath + serlvetPath;
        String url2 = scheme + ":// " + ip + ":" + port + contextPath + serlvetPath;
        String ipCliente = req.getRemoteAddr();

        try (PrintWriter out = resp.getWriter()) {
            //Creo ta plantilla html
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Cabeceras Http request</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cabeceras Http request!</h1>");
            out. println("<ul>");
            out.println("<li>Obteniendo el método: "+ metodoHttp +"</li>");
            out.println("<li> request uri: "+ requestUri +"</li>");
            out.println("<li> request url: "+ requesturl +"</li>");
            out.println("<li> Context Path: "+ contextPath +"</li>");
            out.println("<li> Servlet Path: "+ serlvetPath +"</li>");
            out.println("<li> IP: "+ ip +"</li>");
            out.println("<li> Port : "+ port +"</li>");
            out.println("<li>Scheme: "+ scheme +"</li>");
            out.println("<li>Host: "+ host +"</li>");
            out.println("<li>URL: "+ url +"</li>");
            out.println("<li>URL2: "+ url2 +"</li>");
            out.println("<li>Remote Cliente: "+ ipCliente +"</li>");
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.println("<li>" + cabecera+ ": " + req.getHeader(cabecera)+ "</li>");
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");


        }
    }
}