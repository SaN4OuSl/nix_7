package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "income_categories")
public class IncomeCategory extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }
}
