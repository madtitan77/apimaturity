package com.example.apimaturity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "components")
public class Component {

    @Id
    @SequenceGenerator(name="components_component_id_seq", sequenceName="components_component_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="components_component_id_seq")
    @Column(name = "component_id")
    private Integer componentId;

    @Column(name = "component_name", nullable = false)
    private String componentName;

    @Column(name = "dimension_id", nullable = false)
    private Integer dimensionId;

    @Lob
    @Column(name = "definition")
    private String definition;

    @Lob
    @Column(name = "explanation")
    private String explanation;

    @ManyToOne
    @JoinColumn(name="dimension_id", insertable = false, updatable = false)
    private Dimension dimension;

    // getters and setters for all these fields

    // Please note that adding a method similar to Python's `to_dict()`
    // is strongly discouraged in Java -- please use an object mapper (Jackson) instead

    
}
