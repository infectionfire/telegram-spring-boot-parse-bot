package com.example.model;



import javax.persistence.*;

@Entity
@Table(name = "parse_values")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "features")
    private String features;
    @Column(name = "urls")
    private String urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public Data() {
    }

    public Data( String features, String urls) {
        this.features = features;
        this.urls = urls;
    }

    @Override
    public String toString() {
        return features;
    }
}
