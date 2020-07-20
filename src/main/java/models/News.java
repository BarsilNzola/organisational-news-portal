package models;

public class News {
    private String post;
    private String postedBy;

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
}
