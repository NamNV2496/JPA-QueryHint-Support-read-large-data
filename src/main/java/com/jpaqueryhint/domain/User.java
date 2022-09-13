package com.jpaqueryhint.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "User")
public class User {
    @Id
    private Long id;
    private String name;
}
