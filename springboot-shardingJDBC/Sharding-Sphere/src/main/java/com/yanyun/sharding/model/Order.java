package com.yanyun.sharding.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户信息实体
 * @author 菜头君
 * @date 2018年5月19日
 */
@Table(name = "t_order")
@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column
    private String name;


}
