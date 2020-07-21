package dao;

import java.util.List;
import models.Department;
import models.News;

public interface NewsDao {

    //create
    void add (News news);

    //read
    List<News> getNews();
    News findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
