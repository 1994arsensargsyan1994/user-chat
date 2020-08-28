package com.human.userchat.servlet.users;


import com.human.userchat.model.User;
import com.human.userchat.servlet.RequestValidator;
import com.human.userchat.util.DataValidator;
import com.human.userchat.util.EncryptionUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/register"})
public class RegisterServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/register.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        RequestValidator<User> validator = validate(req);

            if (validator.isValid()) {
                User user = validator.getEntity();
                if (this.userService.userExist(user.getEmail())) {
                    req.setAttribute("errorEmail", "user already exist");
                } else {
                    InputStream imageStream = !DataValidator.isEmpty(validator.getFileItems()) ? validator.getFileItems().get(0).getInputStream() : null;
                    this.userService.save(user,imageStream);
                    req.getSession().setAttribute("successfully", "user successfully registered.");
                    resp.sendRedirect("/login");
                    return;
                }
            }
            req.getRequestDispatcher("WEB-INF/register.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    private static RequestValidator<User> validate(HttpServletRequest req) throws FileUploadException {
        String name = null;
        String surname = null;
        String email = null;
        String password = null;
        String confirmPassword = null;
        List<FileItem> fileItems = new ArrayList<>();

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "name":
                            name = item.getString();
                            break;
                        case "surname":
                            surname = item.getString();
                            break;
                        case "email":
                            email = item.getString();
                            break;
                        case "password":
                            password = item.getString();
                            break;
                        case "confirmPassword":
                            confirmPassword = item.getString();
                            break;
                    }
                }
                else {
                    if (item.getSize() > 0) {
                        fileItems.add(item);
                    }
                }
            }
        } else {
            name = req.getParameter("name");
            surname = req.getParameter("surname");
            email = req.getParameter("email");
            password = req.getParameter("password");
            confirmPassword = req.getParameter("confirmPassword");
        }


            boolean isValid = true;
            if (DataValidator.isEmpty(name)) {
                req.setAttribute("errorName", "Name is Required");
                isValid = false;
            }
            if (DataValidator.isEmpty(surname)) {
                req.setAttribute("errorSurname", "Surname is Required");
                isValid = false;
            }
            if (DataValidator.isEmpty(email)) {
                req.setAttribute("errorEmail", "Email is Required");
                isValid = false;
            } else if (!DataValidator.isValidEmail(email)) {
                req.setAttribute("errorEmail", "Wrong email format!");
                isValid = false;
            }
            if (DataValidator.isEmpty(password)) {
                req.setAttribute("errorPassword", "Password is Required");
                isValid = false;
            } else {
                if (!password.trim().equals(confirmPassword)) {
                    req.setAttribute("errorConfirmPassword", "Password does not match!");
                    isValid = false;
                }
            }
            RequestValidator<User> validator = new RequestValidator<>();
            validator.setValid(isValid);
            if (isValid) {
                User user = new User();
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setPassword(EncryptionUtil.encrypt(password));
                validator.setEntity(user);
                validator.setFileItems(fileItems);
            } else {
                validator.setValid(true);
            }
            return validator;
        }

    }
