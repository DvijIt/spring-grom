package com.lesson5.task1.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
public class Item {
    @Id
    @SequenceGenerator(name = "ITEM_SEQ", sequenceName = "ITEM_PK_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
    private Long id;
    @Column(name = "DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
