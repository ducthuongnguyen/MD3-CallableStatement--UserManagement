package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.IUserDAO;
import com.codegym.service.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLDataException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    IUserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
//            case "sort":
//                sortByName(request,response);
//                break;
            default:
                try {
                    showList(request, response);
                } catch (SQLDataException e) {
                    e.printStackTrace();
                }
        }
    }

    private void sortByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        userDAO.orderByName();
        dispatcher.forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/delete.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.select(id);
        request.setAttribute("deleteUser", user);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.select(id);
        request.setAttribute("editUser", user);
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLDataException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        List<User> userList;
        String key = request.getParameter("key");
        String sort = request.getParameter("sort");
        if ((key == null || key == "") && (sort == null || sort == "")) {
            userList = userDAO.selectAll();
        } else if (key != null && sort == null || sort == "") {
            userList = userDAO.findByCountry(key);
        } else {
            userList = userDAO.orderByName();
        }

        request.setAttribute("list", userList);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    createUser(request, response);
                } catch (SQLDataException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    editUser(request, response);
                } catch (SQLDataException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    deleteUser(request, response);
                } catch (SQLDataException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLDataException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.delete(id);
        response.sendRedirect("/users");
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws SQLDataException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        userDAO.update(new User(id, name, email, country));
        response.sendRedirect("/users");
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLDataException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        userDAO.insert(new User(name, email, country));
        response.sendRedirect("/users");
    }
}
