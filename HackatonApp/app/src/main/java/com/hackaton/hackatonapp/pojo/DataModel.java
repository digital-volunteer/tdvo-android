package com.hackaton.hackatonapp.pojo;

public class DataModel {

    String name;
    String type;
    String version_number;
    String feature;
    Integer id;

    public DataModel(String name, String type, String version_number, String feature,int id ) {
        this.name=name;
        this.type=type;
        this.version_number=version_number;
        this.feature=feature;
        this.id = id;

    }

    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public String getVersion_number() {
        return version_number;
    }
    
    public String getFeature() {
        return feature;
    }
    
}