package edu.cczu.tmall.admin.property.domain;

public class Property {

    private String id;

    private String cid;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}