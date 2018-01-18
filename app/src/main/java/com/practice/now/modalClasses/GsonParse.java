package com.practice.now.modalClasses;

import java.util.ArrayList;

/**
 * Created by user on 29-05-2017.
 */

public class GsonParse {

    private String status;
    private String source;
    private String sortBy;
    private ArrayList<Article> articles;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSource(){
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getSortBy(){
        return sortBy;
    }

    public void setSortBy(String sortBy)
    {
        this.sortBy = sortBy;
    }

    public ArrayList<Article> getArticles(){
        return articles;
    }

    public void setArticles(ArrayList<Article> articles){
        this.articles = articles;
    }


}
