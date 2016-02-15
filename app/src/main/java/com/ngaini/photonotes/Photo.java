package com.ngaini.photonotes;

/**
 * Created by Nathan on 2/14/2016.
 */
public class Photo {


    private int id;
    private String photo_path;
    private String caption;

    public Photo()
    {
        id = 0;
        photo_path = null;
        caption = null;


    }

    public Photo(int id, String path, String caption)
    {
        this.id = id;
        photo_path = path;
        this.caption = caption;

    }

    public void setPath(String path)
    {
        photo_path = path;
    }


    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    public String getPath()
    {
        return photo_path;

    }


    public String getCaption()
    {
        return caption;
    }

}
