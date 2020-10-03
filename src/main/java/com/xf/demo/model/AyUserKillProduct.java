package com.xf.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xfgg
 */
@Entity
@Data
@Table(name = "ay_user_kill_product")
public class AyUserKillProduct implements Serializable {
    @Id
    /**
     * 采用数据库自增长的方式
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private Integer userId;
    private Integer state;
    private Date createTime;

}
