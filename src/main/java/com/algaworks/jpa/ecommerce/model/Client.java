package com.algaworks.jpa.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "client")
@Entity
public class Client {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Transient
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @PostLoad
    public void setUpFirstName() {
        if (name != null && !name.isBlank()) {
            int index = name.indexOf(" ");
            if (index > -1) {
                firstName = name.substring(0, index);
            }
        }
    }

}
