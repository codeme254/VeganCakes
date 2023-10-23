package com.vegancakes;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

//import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@MultipartConfig
public class NewProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NewProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            // Get form data
            String cakeName = request.getParameter("cakeName");
            String cakeDescription = request.getParameter("cakeDescription");
            String cakeCategory = request.getParameter("cakeCategory");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));

            // Get the uploaded image and generate a unique filename
            Part part = request.getPart("cakeImage");
            String originalFileName = part.getSubmittedFileName();
            String uniqueIdentifier = generateRandomString(12);
            String uniqueFileName = uniqueIdentifier + "_" + originalFileName;
            String imagePath = getServletContext().getRealPath("/images/" + uniqueFileName);

            // Save the uploaded image
            InputStream is = part.getInputStream();
            boolean successUpload = uploadFile(is, imagePath);

            if (successUpload) {
                out.print("Uploaded file successfully as: " + uniqueFileName);
                boolean successInsert = insertDataIntoDatabase(uniqueFileName, cakeName, cakeDescription, cakeCategory, quantity, unitPrice);

                if (successInsert) {
                    out.print("Data inserted into the database successfully.");
                } else {
                    out.print("An error occurred while inserting data into the database.");
                }
            } else {
                out.print("An error was encountered while uploading the file.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] buffer = new byte[4096];
            int bytesRead;
            FileOutputStream fops = new FileOutputStream(path);
            while ((bytesRead = is.read(buffer)) != -1) {
                fops.write(buffer, 0, bytesRead);
            }
            fops.flush();
            fops.close();
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

    private boolean insertDataIntoDatabase(String imageName, String cakeName, String cakeDescription, String cakeCategory, int quantity, int unitPrice) {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcakes_v1", "root", "root");
            String sql = "INSERT INTO Products (cake_image, cake_name, cake_description, cake_category, cake_quantity, cake_price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, imageName);
            stmt.setString(2, cakeName);
            stmt.setString(3, cakeDescription);
            stmt.setString(4, cakeCategory);
            stmt.setInt(5, quantity);
            stmt.setInt(6, unitPrice);

            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            conn.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
