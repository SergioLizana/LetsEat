package ikigaiworks.letseat.model;

import android.content.Context;

import java.util.ArrayList;
import ikigaiworks.letseat.R;
/**
 * Created by sergiolizanamontero on 13/9/17.
 */

public class Carrusel {


    private ArrayList<CarruselSlide> slides =  new ArrayList<CarruselSlide>();
    CarruselSlide menu;
    CarruselSlide orders;
    CarruselSlide promos;
    Context c;

    public static Carrusel newInstance(Context c){
        Carrusel carrusel = new Carrusel(c);
        return carrusel;
    }

    public Carrusel(Context c){
        this.c = c;
        init();
    }

    void init(){
        menu = new CarruselSlide("Menu",1,c.getString(R.string.desc_menu), R.drawable.menu);
        promos = new CarruselSlide("Orders",2,c.getString(R.string.desc_pedidos),R.drawable.orders);
        orders = new CarruselSlide("Promos",3,c.getString(R.string.desc_promos),R.drawable.promos);
        slides.add(menu);
        slides.add(orders);
        slides.add(promos);

    }

    public ArrayList<CarruselSlide> getSlides() {
        return slides;
    }

    public void setSlides(ArrayList<CarruselSlide> slides) {
        this.slides = slides;
    }

    public CarruselSlide getMenu() {
        return menu;
    }

    public void setMenu(CarruselSlide menu) {
        this.menu = menu;
    }

    public CarruselSlide getOrders() {
        return orders;
    }

    public void setOrders(CarruselSlide orders) {
        this.orders = orders;
    }

    public CarruselSlide getPromos() {
        return promos;
    }

    public void setPromos(CarruselSlide promos) {
        this.promos = promos;
    }



}
