package com.finalproject.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieModel implements Serializable {


    private String id;
    private String title;
    private String rate;
    private String video;
    private String image;
    private CategoryModel category;
    private String price;
    private String details;
    private List<HeroModel> heroes;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRate() {
        return rate;
    }

    public String getVideo() {
        return video;
    }

    public String getImage() {
        return image;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }

    public List<HeroModel> getHeroes() {
        return heroes;
    }




}
