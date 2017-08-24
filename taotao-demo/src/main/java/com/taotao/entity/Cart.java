package com.taotao.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_cart")
public class Cart {
    @Id
    private Long pid;

    /**
     * 车辆编码
     */
    @Column(name = "cart_id")
    private String cartId;

    /**
     * 车牌号码
     */
    @Column(name = "cart_brand_code")
    private String cartBrandCode;

    /**
     * 车辆名称
     */
    @Column(name = "cart_name")
    private String cartName;

    /**
     * 车辆类型
     */
    @Column(name = "cart_type")
    private String cartType;

    /**
     * 状态 1:正常  2:停用 3:修改线路中
     */
    private Integer status;

    /**
     * 驾驶员编码
     */
    @Column(name = "driver_id")
    private String driverId;

    /**
     * 路线编码
     */
    @Column(name = "way_id")
    private String wayId;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 最后更新时间
     */
    @Column(name = "last_updated_at")
    private Date lastUpdatedAt;

    /**
     * @return pid
     */
    public Long getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取车辆编码
     *
     * @return cart_id - 车辆编码
     */
    public String getCartId() {
        return cartId;
    }

    /**
     * 设置车辆编码
     *
     * @param cartId 车辆编码
     */
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    /**
     * 获取车牌号码
     *
     * @return cart_brand_code - 车牌号码
     */
    public String getCartBrandCode() {
        return cartBrandCode;
    }

    /**
     * 设置车牌号码
     *
     * @param cartBrandCode 车牌号码
     */
    public void setCartBrandCode(String cartBrandCode) {
        this.cartBrandCode = cartBrandCode;
    }

    /**
     * 获取车辆名称
     *
     * @return cart_name - 车辆名称
     */
    public String getCartName() {
        return cartName;
    }

    /**
     * 设置车辆名称
     *
     * @param cartName 车辆名称
     */
    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    /**
     * 获取车辆类型
     *
     * @return cart_type - 车辆类型
     */
    public String getCartType() {
        return cartType;
    }

    /**
     * 设置车辆类型
     *
     * @param cartType 车辆类型
     */
    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    /**
     * 获取状态 1:正常  2:停用 3:修改线路中
     *
     * @return status - 状态 1:正常  2:停用 3:修改线路中
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 1:正常  2:停用 3:修改线路中
     *
     * @param status 状态 1:正常  2:停用 3:修改线路中
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取驾驶员编码
     *
     * @return driver_id - 驾驶员编码
     */
    public String getDriverId() {
        return driverId;
    }

    /**
     * 设置驾驶员编码
     *
     * @param driverId 驾驶员编码
     */
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    /**
     * 获取路线编码
     *
     * @return way_id - 路线编码
     */
    public String getWayId() {
        return wayId;
    }

    /**
     * 设置路线编码
     *
     * @param wayId 路线编码
     */
    public void setWayId(String wayId) {
        this.wayId = wayId;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新人
     *
     * @return updated_by - 更新人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取最后更新时间
     *
     * @return last_updated_at - 最后更新时间
     */
    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    /**
     * 设置最后更新时间
     *
     * @param lastUpdatedAt 最后更新时间
     */
    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}