package org.example.entity;

public abstract class AbstractEntity {
    private Integer id;
    private Boolean available;

    public AbstractEntity() {
        this.available = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
