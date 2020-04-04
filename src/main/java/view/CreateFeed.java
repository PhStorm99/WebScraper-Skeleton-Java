package view;

import entity.Feed;
import entity.Host;
import logic.FeedLogic;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.HostLogic;

/**
 *
 * @author hnpav
 * @version 2.0
 * @since 2019-09-30
 */
@WebServlet(name = "CreateFeed", urlPatterns = {"/CreateFeed"})
public class CreateFeed extends HttpServlet {

    // Private variable string which has null value
    private String errorMessage = null;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Create Feed</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style=\"text-align: center;\">");
            out.println("<div style=\"display: inline-block; text-align: left;\"> CREATE FEED ");
            out.println("<form method=\"post\">");
            //instead of typing the name of column manualy use the static vraiable in logic
            //use the same name as column id of the table. will use this name to get date
            //from parameter map.
            out.println("Name:<br>");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\"><br>", FeedLogic.NAME);
            out.println("<br>");

            //For inputting type
            out.println("Type:<br>");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\"><br>", FeedLogic.TYPE);
            out.println("<br>");

            out.println("Path:<br>");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\"><br>", FeedLogic.PATH);
            out.println("<br>");

            out.println("Host ID:<br>");
            out.printf("<input type=\"text\" name=\"%s\" value=\"\"><br>", FeedLogic.HOST_ID);
            out.println("<br>");

            out.println("<input type=\"submit\" name=\"view\" value=\"Add and View\">");
            out.println("<input type=\"submit\" name=\"add\" value=\"Add\">");
            out.println("</form>");
            if (errorMessage != null && !errorMessage.isEmpty()) {
                out.println("<p color=red>");
                out.println("<font color=red size=4px>");
                out.println(errorMessage);
                out.println("</font>");
                out.println("</p>");
            }
            out.println("<pre>");
            out.println("Submitted keys and values:");
            out.println(toStringMap(request.getParameterMap()));

            if (request.getParameterMap().get("id").length == 0) {
                out.println("EMPTY");
            } else {
                out.println(request.getParameterMap().get("id").length);
            }
            if (request.getParameterMap().get("name").length == 0) {
                out.println("EMPTY");
            } else {
                out.println(request.getParameterMap().get("name").length);
            }
            if (request.getParameterMap().get("type").length == 0) {
                out.println("EMPTY");
            } else {
                out.println(request.getParameterMap().get("type").length);
            }
            if (request.getParameterMap().get("path").length == 0) {
                out.println("EMPTY");
            } else {
                out.println(request.getParameterMap().get("path").length);
            }

            out.println("</pre>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    //private StringMap method for mapping the key and value
    private String toStringMap(Map<String, String[]> values) {
        StringBuilder builder = new StringBuilder();
        values.forEach((k, v) -> builder.append("Key=").append(k)
                .append(", ")
                .append("Value/s=").append(Arrays.toString(v))
                .append(System.lineSeparator()));
        return builder.toString();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * get method is called first when requesting a URL. since this servlet will
     * create a host this method simple delivers the html code. creation will be
     * done in doPost method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("GET");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * this method will handle the creation of entity. as it is called by user
     * submitting data through browser.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("POST");
        //create logic
        FeedLogic fLogic = new FeedLogic();

        //grab the parameter NAME first
        String name = request.getParameter(FeedLogic.NAME);
        String type = request.getParameter(FeedLogic.TYPE);
        String path = request.getParameter(FeedLogic.PATH);
        //since it is unique we check for duplicates.
        //Because name and type is the list, so it should call isEmpty() method .
        try {
            if (fLogic.getFeedsWithName(name).isEmpty() && fLogic.getFeedWithPath(path) == null && fLogic.getFeedsWithType(type).isEmpty()) {
                //if no duplicates create the entity and add it.
                Feed feed = fLogic.createEntity(request.getParameterMap());

                HostLogic hLogic = new HostLogic();
                Host host = hLogic.getWithId(Integer.parseInt(request.getParameter(FeedLogic.HOST_ID)));
                //hLogic.getWithId(1);
                //setting hostId to the feed
                feed.setHostid(host);
                fLogic.add(feed);
            } else {
                //if duplicate print the error message
                errorMessage = "Feed: \"" + name + "\" already exists";
            }
        } catch (RuntimeException ex) {
            errorMessage = ex.getMessage();
        }
        if (request.getParameter("add") != null) {
            //if add button is pressed return the same page
            processRequest(request, response);
        } else if (request.getParameter("view") != null) {
            //if view button is pressed redirect to the appropriate table
            response.sendRedirect("FeedTable");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Create a Feed Entity";
    }

    private static final boolean DEBUG = true;

    @Override
    public void log(String msg) {
        if (DEBUG) {
            String message = String.format("[%s] %s", getClass().getSimpleName(), msg);
            getServletContext().log(message);
        }
    }

    @Override
    public void log(String msg, Throwable t) {
        String message = String.format("[%s] %s", getClass().getSimpleName(), msg);
        getServletContext().log(message, t);
    }
}
