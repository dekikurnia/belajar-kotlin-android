package com.dekikurnia.belajarkotlin.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by server02 on 02/06/2017.
 */

public class Crud extends RealmObject {
    @PrimaryKey
    private String id;
    private String title;
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
