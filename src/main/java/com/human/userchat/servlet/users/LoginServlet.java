package com.human.userchat.servlet.users;


import com.human.userchat.model.User;
import com.human.userchat.servlet.RequestValidator;
import com.human.userchat.util.DataValidator;
import com.human.userchat.util.EncryptionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet({"/login"})
public class LoginServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestValidator<User> validator = LoginServlet.validate(req);
            if (validator.isValid()) {
                User user = validator.getEntity();
                Optional<User> optionalUser =  super.userService.getUser(user.getEmail(),user.getPassword());
                if (optionalUser.isPresent()) {
                    user = optionalUser.get();
                    req.getSession().setAttribute("user", user);
                    resp.sendRedirect("/home");
                    return;
                }
                req.setAttribute("wrongEmailPassword", "Wrong Email or Password!");
            }
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private static RequestValidator<User> validate(HttpServletRequest request) {
        boolean isValid = true;

        String email = request.getParameter("email");
        if (DataValidator.isEmpty(email)) {
            request.setAttribute("errorEmail", "Email is Required");
            isValid = false;
        }
        String password = request.getParameter("password");
        if (DataValidator.isEmpty(password)) {
            request.setAttribute("errorPassword", "Password is Required");
            isValid = false;
        }
        RequestValidator<User> validator = new RequestValidator<>();
        validator.setValid(isValid);
        if (isValid) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(EncryptionUtil.encrypt(password));
            validator.setEntity(user);
        }
        return validator;

    }

}
