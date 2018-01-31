package models;

public class Carte
{
    private int id;
    private String imgUrl;

    public Carte(int id, String imgUrl)
    {
        this.id = id;
        this.imgUrl = imgUrl;
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
}
