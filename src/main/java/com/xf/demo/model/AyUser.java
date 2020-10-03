package com.xf.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author xfgg
 */
@Entity
@Data
@Table(name = "ay_user")
public class AyUser implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String phoneNumber;
}
