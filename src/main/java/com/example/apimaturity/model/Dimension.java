package com.example.apimaturity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dimensions")
public class Dimension {

    @Id
    @SequenceGenerator(name="dimensions_dimension_id_seq", sequenceName="dimensions_dimension_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="dimensions_dimension_id_seq")
    @Column(name = "dimension_id")
    private Integer dimensionId;

    @Column(name = "dimension_name", nullable = false)
    private String dimensionName;

    // getters and setters for all these fields

    // Please note that adding a method similar to Python's `to_dict()`
    // is strongly discouraged in Java -- please use an object mapper (Jackson) instead
}