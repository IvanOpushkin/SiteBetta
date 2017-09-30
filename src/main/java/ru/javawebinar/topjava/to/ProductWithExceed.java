package ru.javawebinar.topjava.to;

import java.time.LocalDateTime;

public class ProductWithExceed {
    private final Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    private final String type1;
    private final String type2;
    private final Integer cod;
    private final String naimenovanie;
    private final String proizvoditel;
    private final String edizmereniya;
    private final Integer kolvo;
    private final Integer cena;
    private final String primechanie;
    private final boolean checkType;
    private final String picture;
    private final String articul;
    private final Integer fullprice;


    public ProductWithExceed(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed, String type1, String type2, Integer cod, String naimenovanie, String proizvoditel, String edizmereniya, Integer kolvo, Integer cena, String primechanie, String picture, String articul) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.type1 = type1;
        this.type2 = type2;
        this.cod = cod;
        this.naimenovanie = naimenovanie;
        this.proizvoditel = proizvoditel;
        this.edizmereniya = edizmereniya;
        this.kolvo = kolvo;
        this.cena = cena;
        this.primechanie = primechanie;
        this.checkType = getSort(type1);
        this.picture = picture;
        this.articul = articul;
        this.fullprice = kolvo*cena;


    }

    //
    public boolean getSort(String type)
    {
        if (type.equals(type1))
            return true;
        else return false;
    }




    public String getType1() {
        return type1;
    }

    public String getType2() {
        return type2;
    }

    public Integer getCod() {
        return cod;
    }

    public String getNaimenovanie() {
        return naimenovanie;
    }

    public String getProizvoditel() {
        return proizvoditel;
    }

    public String getEdizmereniya() {
        return edizmereniya;
    }

    public Integer getKolvo() {
        return kolvo;
    }

    public Integer getCena() {
        return cena;
    }

    public String getPrimechanie() {
        return primechanie;
    }

    public String getArticul() {
        return articul;
    }

    public Integer getFullprice() {
        return fullprice;
    }

    public String getPicture() {
        return picture;
    }



    /*
    public ProductWithExceed(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }
    */

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public String toString() {
        return "ProductWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}