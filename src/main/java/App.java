import dao.*;
import exception.ApiException;
import models.DepartmentNews;
import models.Department;
import models.User;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Sql2oUserDao userDao;
        Sql2oNewsDao newsDao;
        Sql2oDepartmentDao departmentDao;
        Sql2oDepartmentNewsDao departmentNewsDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/organizational_news";
        Sql2o sql2o = new Sql2o(connectionString, "barsil", "madboysent7042");

        userDao = new Sql2oUserDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        conn = sql2o.open();

        get("/departments", "application/json", (request, response) -> {
            return gson.toJson(departmentDao.getDepartments());
        });

        get("/departments/:id", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("id")));
            }
            return gson.toJson(departmentToFind);
        });

        get("/users", "application/json", (request, response) -> {
            return gson.toJson(userDao.getUsers());
        });

        get("/users/:id", "application/json", (request, response) -> {
            response.type("application/json");
            int userId = Integer.parseInt(request.params("id"));
            return gson.toJson(departmentDao.findById(userId));
        });
        get("/departmentnews", "", (request, response) -> {
            return gson.toJson(departmentNewsDao.getDepartmentNews());
        });

        get("/departmentnews/:id", "application/json", (request, response) -> {
            int departmentNewsId = Integer.parseInt(request.params("id"));
            return gson.toJson(departmentNewsDao.findById(departmentNewsId));
        });
        get("/news","application/json",(request, response) -> {
            return gson.toJson(newsDao.getNews());

        });
        get("/news/:id","application/json",(request, response) -> {
            int newsId = Integer.parseInt(request.params("id"));
            return gson.toJson(newsDao.findById(newsId));
        });

        post("/departments/new", "application/json", (request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            return gson.toJson(department);
        });
        post("/users/new","application/json",(request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userDao.add(user);
            response.status(201);
            return gson.toJson(user);
        });

        post("/departmentnews/new","application/json",(request, response) -> {
            DepartmentNews departmentNews = gson.fromJson(request.body(),DepartmentNews.class);
            departmentNewsDao.add(departmentNews);
            response.status(201);
            return gson.toJson(departmentNews);
        });
        post("/news/new","application/json",(request, response) -> {
            News news = gson.fromJson(request.body(),News.class);
            newsDao.add(news);
            response.status(201);
            return  gson.toJson(news);
        });

        exception(ApiException.class, (exc, req, res) -> {
            ApiException err = (ApiException) exc;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

        after((req, res) -> {
            res.type("application/json");
        });
    }
}
