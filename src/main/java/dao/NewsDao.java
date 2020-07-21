package dao;

import java.util.List;
import models.Department;
import models.News;

public interface NewsDao {

    //create
    void add (News news);

    //read
    List<News> getAll();
    News findById(int id);

    //update
    void update(String post, String postedBy);

    //delete
    void deleteById(int id);
    void clearAll();
}
