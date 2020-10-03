package com.xf.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xfgg
 */
@Entity
@Data
@Table(name = "ay_product")
public class AyProduct implements Serializable {
    @Id
    private Integer id;
    private String productImpg;
    private String name;
    private Integer number;
    private Date startTime;
    private Date endTime;
    private Date createTime;
}
