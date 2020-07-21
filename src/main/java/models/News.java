package models;

import java.util.Objects;

public class News {
    private String post;
    private String postedBy;
    int id;

    public News(String post, String postedBy) {
        this.post = post;
        this.postedBy = postedBy;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                post.equals(news.post) &&
                postedBy.equals(news.postedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post, postedBy, id);
    }
}
