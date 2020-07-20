package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Test
    public void getPostByReturnsCorrectPost() {
        News testNews = setUpNews();
        assertEquals("Salary Decreament", testNews.getPost());
    }

    @Test
    public void setPostSetsCorrectPost() {
        News testNews = setUpNews();
        testNews.setPost("client added features");
        assertNotEquals("Salary Decreament", testNews.getPost());
    }

    @Test
    public void getPostedByReturnsCorrectPostedBy() {
        News testNews = setUpNews();
        assertEquals("Jackqueen", testNews.getPostedBy());
    }

    @Test
    public void setPostedBySetsCorrectPostedBy() {
        News testNews = setUpNews();
        testNews.setPostedBy("Barsil");
        assertNotEquals("Jackqueen", testNews.getPostedBy());
    }

    public News setUpNews() {
        return new News("Salary Decreament", "Jackqueen");
    }
}