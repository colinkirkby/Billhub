package com.billhub.main.mappage.java.model;
public class Map_Structure{
    private int x = 10000;
    private int y = 8000
    private Map_Data_Point[x][y] map;
    private long top = 49.37323455439729;
    private long left = -123.31449973295146;
    private long right = -122.0964842157064;
    private long bottom = 48.99932350134718;
    private step_horizontal;
    private step_vertical;
    private vert;
    private hori;
    public Map_Structure (){
        vert = top-bottom;
        hori = left-right;
        step_hor = hori/x;
        step_vet = vert/y;
    }

    public Map_Data_Point find_point (long lat, long lng){
        int x_index = math.toint(floor(lng/step_ho));
        int y_index = math.toint(floor(lat/step_vert));
        return map[x_index][yindex];
    }

    public void add_Data (long lat, long lng,User_Data newdata){
        x_index = lng/step_hor;
        y_index = lat/step_vert;
        map[x_index][yindex].add_new(newdata);
    }
}

