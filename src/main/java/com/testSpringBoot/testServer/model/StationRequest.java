package com.testSpringBoot.testServer.model;

/**
 * @author Serhii_Udaltsov on 8/29/2020
 */
public class StationRequest {
    private String name;
    private String nameRu;

    public StationRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }
}
