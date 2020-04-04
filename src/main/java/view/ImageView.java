/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.FileUtility;
import entity.Feed;
import logic.ImageLogic;
import entity.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.FeedLogic;
import scraper.Post;
import scraper.Scraper;
import scraper.Sort;

/**
 *
 * @author hnpav
 * @version 2.0
 * @since 2019-09-30
 */
public class ImageView extends HttpServlet {

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

            ImageLogic imageLogic = new ImageLogic();

            List<Image> entity = imageLogic.getAll();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet at ImageView</title>");
            out.println("</head>");
            out.println("<body>");
            //  out.println("<h1>Servlet ImageView " + request.getContextPath() + "</h1>");
            for (Image img : entity) {

                out.println("<div class=\"imageContainer\">");
                out.printf("<img class= \"imageThumb\" src=\"image/%s\"></div>", FileUtility.getFileName(img.getPath()));
                // out.printf("<img class= \"imageThumb\" padding= \"20\"width=\"400\"height=\"auto\""+"style=\"display=\"grid\""+"src=\"image/%s\"></div>",FileUtility.getFileName(img.getPath()));  

                out.println("</div>");
            };

            out.println("</body>");
            out.println("</html>");
//            <img src="image/"+FileUtility.getFileName(image.getPath())/>
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String imagePath = System.getProperty("user.home") + "/Documents/Reddit Images/";
        FileUtility.createDirectory(imagePath);

        ImageLogic imageLogic = new ImageLogic();
        FeedLogic feedLogic = new FeedLogic();

        //create a lambda that accepts post
        Consumer<Post> saveImage = (Post post) -> {
            //if post is an image and SFW
            if (post.isImage() && !post.isOver18()) {
                //get the path for the image which is unique
                String path = post.getUrl();
                //save it in img directory
                FileUtility.downloadAndSaveFile(path, imagePath);

                Map<String, String[]> map = new HashMap<>(3);
                map.put(ImageLogic.DATE, new String[]{Long.toString(post.getDate().getTime())});
                map.put(ImageLogic.NAME, new String[]{post.getTitle()});
                map.put(ImageLogic.PATH, new String[]{post.getUrl()});

                if (imageLogic.getImageWithPath(post.getUrl()) == null) {

                    Image savedImage = imageLogic.createEntity(map);
                    //Because, Image is depends on feed, add feed to image
                    Feed savedFeed = feedLogic.getWithId(4);

                    // Feed feed = new FeedLogic().getWithId(Integer.parseInt(request.getParameter(ImageLogic.FEED_ID)));
                    savedImage.setFeedid(savedFeed);
                    imageLogic.add(savedImage);

                    //add image
                    //imageLogic.add( img);
                }

            }
        };

        //create a new scraper
        Scraper scrap = new Scraper();
        //authenticate and set up a page for wallpaper subreddit with 5 posts soreted by HOT order
        scrap.authenticate().buildRedditPagesConfig("Wallpaper", 5, Sort.HOT);

        //"Wallpaper", 5, Sort.HOT
        //get the next page 3 times and save the images.
        scrap.requestNextPage().proccessNextPage(saveImage);

        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
