package com.taotao.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_role")
public class Role {
    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

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
     * 获取角色id
     *
     * @return role_id - 角色id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色id
     *
     * @param roleId 角色id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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