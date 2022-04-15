package com.finalproject.model;

import java.io.Serializable;
import java.util.List;

public class HomeDataModel extends StatusResponse implements Serializable {
    private List<SliderModel>slider;
    private List<MovieModel>moves;
    private List<ShowModel>shows;
    private List<ComingSoonModel>soon;

    public List<SliderModel> getSlider() {
        return slider;
    }

    public List<MovieModel> getMoves() {
        return moves;
    }

    public List<ShowModel> getShows() {
        return shows;
    }

    public List<ComingSoonModel> getSoon() {
        return soon;
    }
}
