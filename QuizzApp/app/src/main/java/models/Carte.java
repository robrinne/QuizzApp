package models;

public class Carte
{
    private int id;
    private String imgUrl;
    private boolean found;

    public Carte()
    {
        this.id = 0;
        this.imgUrl = "";
        this.found = false;
    }

    public Carte(int id, String imgUrl)
    {
        this.id = id;
        this.imgUrl = imgUrl;
        this.found = false;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public void setFounded()
    {
        found = true;
    }


}
