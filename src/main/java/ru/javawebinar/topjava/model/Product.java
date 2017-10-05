package ru.javawebinar.topjava.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column (name = "dateTime")
    @NotNull
    //mb NotBlank тк реальней тогда
    private LocalDateTime dateTime;


    @Column (name = "description")
    @NotNull
    private String description;

    @Column (name = "calories")
    @NotNull
    private int calories;





    @Column (name = "type1")
    private String type1;
    @Column (name = "type2")
    private String type2;
    @Column (name = "cod")
    private Integer cod;
    @Column (name = "naimenovanie")
    private String naimenovanie;
    @Column (name = "proizvoditel")
    private String proizvoditel;
    @Column (name = "edizmereniya")
    private String edizmereniya;
    @Column (name = "kolvo")
    private Integer kolvo;
    @Column (name = "cena")
    private Integer cena;
    @Column (name = "primechanie")
    private String primechanie;
    @Column (name = "picture")
    private String picture;
    @Column (name = "articul")
    private String articul;

    //Параметр расчёта
    @Column (name = "fullprice")
    private Integer fullprice;


    //Общие стоимости (Всем продуктам присвоить)
    private Integer fullPriceOfType;

    public Integer getFullPriceOfType() {
        return fullPriceOfType;
    }

    public void setFullPriceOfType(Integer fullPriceOfType) {
        this.fullPriceOfType = fullPriceOfType;
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNaimenovanie() {
        return naimenovanie;
    }

    public void setNaimenovanie(String naimenovanie) {
        this.naimenovanie = naimenovanie;
    }

    public String getProizvoditel() {
        return proizvoditel;
    }

    public void setProizvoditel(String proizvoditel) {
        this.proizvoditel = proizvoditel;
    }

    public String getEdizmereniya() {
        return edizmereniya;
    }

    public void setEdizmereniya(String edizmereniya) {
        this.edizmereniya = edizmereniya;
    }

    public Integer getKolvo() {
        return kolvo;
    }

    public void setKolvo(Integer kolvo) {
        this.kolvo = kolvo;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public String getPrimechanie() {
        return primechanie;
    }

    public void setPrimechanie(String primechanie) {
        this.primechanie = primechanie;
    }



    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    //Мб отдельное место в отдельной таблице.
    //@Column (name = "ckritaya prodazhi")
    //



    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Product() {
    }

    public Product(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Product(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Product(Integer id, LocalDateTime dateTime, String description, int calories, String type1, String type2, int cod, String naimenovanie, String proizvoditel, String edizmereniya, int kolvo, int cena, String primechanie, String picture, String articul) {

        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;

        this.type1 = type1;
        this.type2 = type2;
        this.cod = cod;
        this.naimenovanie = naimenovanie;
        this.proizvoditel = proizvoditel;
        this.edizmereniya = edizmereniya;
        this.kolvo = kolvo;
        this.cena = cena;
        this.primechanie = primechanie;
        this.picture = picture;
        this.articul = articul;
        //базово почемуто пусто null пришлось добавить в геттер сэттер
        this.fullprice = kolvo*cena;
    }

    public Integer getFullprice() {
        return kolvo*cena;
    }

    public void setFullprice(Integer fullprice) {
        this.fullprice = fullprice;
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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Product{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", cod=" + cod +
                ", naimenovanie='" + naimenovanie + '\'' +
                ", proizvoditel='" + proizvoditel + '\'' +
                ", edizmereniya='" + edizmereniya + '\'' +
                ", kolvo=" + kolvo +
                ", cena=" + cena +
                ", primechanie='" + primechanie + '\'' +
                ", picture='" + picture + '\'' +
                ", articul='" + articul + '\'' +
                ", fullprice=" + fullprice +
                ", user=" + user +
                '}';
    }
}
