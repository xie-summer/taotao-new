package com.taotao.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "location")
public class Location {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 车辆id
     */
    private String cid;

    /**
     * 经度
     */
    private String lat;

    /**
     * 纬度
     */
    private String lng;

    /**
     * 类型:1 车头 0 车尾
     */
    private String type;

    /**
     * 接收时间
     */
    private Date time;

    /**
     * 系统时间
     */
    private Date systime;

    /**
     * 备注
     */
    private String comment;

}