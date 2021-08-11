package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public abstract class BaseEntity {

    protected int id;
    protected Date created;
    protected boolean available;

    public BaseEntity() {
        this.created = new Date();
    }
}
