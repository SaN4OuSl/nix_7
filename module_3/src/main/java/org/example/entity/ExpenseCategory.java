package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "expense_categories")
public class ExpenseCategory extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }
}
