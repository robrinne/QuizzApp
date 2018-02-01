package aston.fr.quizzapp.models;

public class Carte
{
    private String id;
    private String imgUrl;
    private boolean found;
    private boolean visible;
    private int position;

    public Carte()
    {
        this.id = "";
        this.imgUrl = "";
        this.found = false;
        this.visible = false;
        this.position = 0;
    }

    public Carte(int id, String imgUrl)
    {
        if (id < 10)
        {
            this.id = "00" + id;
        }
        else if (id < 100)
        {
            this.id = "0" + id;
        }
        else
        {
            this.id = "" + id;
        }
        this.imgUrl = imgUrl;
        this.found = false;
        this.visible = false;
        this.position = 0;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    public boolean isFound()
    {
        return found;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public int getPosition()
    {
        return position;
    }
}
