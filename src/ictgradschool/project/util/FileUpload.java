package ictgradschool.project.util;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "fileupload", urlPatterns = {"/fileupload"})
public class FileUpload extends HttpServlet {
    private File uploadsFolder;
    private File tempFolder;
    private final String imagesRelativePath = "/images";
    private final List<String> acceptedMimeTypes = Arrays.asList("image/png", "image/jpeg", "image/jpg");

    @Override
    public void init() throws ServletException {
        super.init();

        this.uploadsFolder = new File(getServletContext().getRealPath(imagesRelativePath));
        if (!uploadsFolder.exists()) {
            uploadsFolder.mkdirs();
        }

        this.tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Sets the divert - this will divert the forwarding servlet depending on what is in the POST request
        int servletDivert = 0;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            File fullsizeImageFile = null;


            for (int i = 0; i < fileItems.size(); i++) {
                if ((fileItems.get(i).getFieldName()).equals("add")) {
                    servletDivert = 1;
                }
            }

            for (FileItem fi : fileItems) {
                if (!fi.isFormField() && acceptedMimeTypes.contains(fi.getContentType())) {
                    String fileName = fi.getName();
                    fullsizeImageFile = new File(uploadsFolder, fileName);
                    fi.write(fullsizeImageFile);
                    req.setAttribute("image", fi.getName());
                }

                //Set's up some attributes for the getter to read - these are tailored depending on the servletdivert
                if (servletDivert == 1) {
                    if (fi.isFormField()) {

                        if (fi.getFieldName().equals("title")) {
                            req.setAttribute("title", fi.getString());

                        } else if (fi.getFieldName().equals("text")) {
                            req.setAttribute("text", fi.getString());
                        }

                        req.setAttribute("add", "add");
                    }
                } else {
                    if (fi.isFormField()) {
                        if (fi.getFieldName().equals("firstname")) {
                            req.setAttribute("firstname", fi.getString());
                        } else if (fi.getFieldName().equals("lastname")) {
                            req.setAttribute("lastname", fi.getString());
                        } else if (fi.getFieldName().equals("username")) {
                            req.setAttribute("username", fi.getString());
                        } else if (fi.getFieldName().equals("bio")) {
                            req.setAttribute("bio", fi.getString());
                        }

                        req.setAttribute("edit", "edit");

                    }
                }


            }
        } catch (Exception e) {
            throw new ServletException(e);
        }


        if (servletDivert == 1) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/article");
            dispatcher.forward(req, resp);
        } else {

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/deleteuser");
            dispatcher.forward(req, resp);
        }
    }
}
