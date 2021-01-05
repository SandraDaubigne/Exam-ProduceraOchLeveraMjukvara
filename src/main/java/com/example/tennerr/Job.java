package com.example.tennerr;

import javax.persistence.*;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String jobdescription;
    private String titel;
    private int price;
    private boolean beauty;
    private boolean cleaning;
    private boolean vvs;
    private boolean health;
    private boolean painting;

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    public Job() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitel() {
        return titel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public boolean isBeauty() {
        return beauty;
    }

    public void setBeauty(boolean beauty) {
        this.beauty = beauty;
    }

    public boolean isCleaning() {
        return cleaning;
    }

    public void setCleaning(boolean cleaning) {
        this.cleaning = cleaning;
    }

    public boolean isVvs() {
        return vvs;
    }

    public void setVvs(boolean vvs) {
        this.vvs = vvs;
    }

    public boolean isHealth() {
        return health;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    public boolean isPainting() {
        return painting;
    }

    public void setPainting(boolean painting) {
        this.painting = painting;
    }






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }
}
