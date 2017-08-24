package com.taotao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Column(name = "nick_name")
    private String nickName;

    private Integer sex;

    @Column(name = "register_date")
    private Date registerDate;
}