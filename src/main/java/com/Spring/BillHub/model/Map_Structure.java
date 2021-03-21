package com.Spring.BillHub.model;
import java.lang.*;

import static java.lang.Math.floor;

public class Map_Structure{
    private int x = 100;
    private int y = 50 ;
    private Map_Data_Point[][] map = new Map_Data_Point[x][y];
    private double top = 49.37323455439729;
    private double left = -123.31449973295146;
    private double right = -122.0964842157064;
    private double bottom = 48.99932350134718;
    private int step_hor;
    private int step_ver;
    private double vert;
    private double hori;
    private double vstepsize;
    private double hstepsize;
    public Map_Structure (){
        vert = top-bottom;
        hori = left-right;
        step_hor =  (int)floor(hori/x);
        step_ver =  (int)(floor(vert/y));
    }
    public Map_Structure(int i){
        System.out.println("creating random map values" );
        vert = top-bottom;
        hori = left-right;
        vstepsize = vert/y;
        hstepsize = hori/x;
        step_hor =  (int) (hori/hstepsize);
        step_ver =  (int) (vert/vstepsize);
        System.out.println("horizontal steps = " + step_hor + hstepsize);
        System.out.println("vertical steps = " + step_ver + vstepsize);
        int cx = 0;
        while(cx < step_hor-1){
            int cy = 0;
            //System.out.println("newcolumn");
            while(cy < step_ver-2){
                //System.out.println("newrow" + cy);
                map[cx][cy] = new Map_Data_Point(0);
                cy++;
            }
            cx++;
        }



    }

    public Map_Data_Point find_point (double lat, double lng){

        int x_index = (int)((left-lng)/hstepsize);
        int y_index = (int)((top-lat)/vstepsize);
        System.out.println("x index = " + x_index + "\ny index = " + y_index +
                "\nhstep = " + hstepsize + "\nvstep = " + vstepsize
                );
        return map[x_index][y_index];
    }

    public void add_Data (long lat, long lng,User_Data newdata){
        int x_index = (int)lng/step_hor;
        int y_index = (int)lat/step_ver;
        map[x_index][y_index].add_new(newdata);
    }
}

