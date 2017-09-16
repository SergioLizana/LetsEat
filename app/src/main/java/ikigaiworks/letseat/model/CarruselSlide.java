package ikigaiworks.letseat.model;

/**
 * Created by sergiolizanamontero on 13/9/17.
 */

public class CarruselSlide {

    private String title;
    private int id;
    private int idText;
    private int icon;

    public CarruselSlide(){}

    public CarruselSlide(String title, int id, int idText, int icon) {
        this.title = title;
        this.id = id;
        this.idText = idText;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIdText() {
        return idText;
    }

    public void setIdText(int idText) {
        this.idText = idText;
    }
}
