/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.orderService.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kreys
 */
@Entity
@Table(name = "t_cart_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCartItem.findAll", query = "SELECT t FROM TCartItem t"),
    @NamedQuery(name = "TCartItem.findByLgId", query = "SELECT t FROM TCartItem t WHERE t.lgId = :lgId"),
    @NamedQuery(name = "TCartItem.findByLgItemId", query = "SELECT t FROM TCartItem t WHERE t.lgItemId = :lgItemId"),
    @NamedQuery(name = "TCartItem.findByStrOrderId", query = "SELECT t FROM TCartItem t WHERE t.strOrderId = :strOrderId"),
    @NamedQuery(name = "TCartItem.findByLgCartId", query = "SELECT t FROM TCartItem t WHERE t.lgCartId = :lgCartId"),
    @NamedQuery(name = "TCartItem.findByEmStatus", query = "SELECT t FROM TCartItem t WHERE t.emStatus = :emStatus"),
    @NamedQuery(name = "TCartItem.findByIntQty", query = "SELECT t FROM TCartItem t WHERE t.intQty = :intQty"),
    @NamedQuery(name = "TCartItem.findByDbPrice", query = "SELECT t FROM TCartItem t WHERE t.dbPrice = :dbPrice"),
    @NamedQuery(name = "TCartItem.findByDtDateCreated", query = "SELECT t FROM TCartItem t WHERE t.dtDateCreated = :dtDateCreated"),
    @NamedQuery(name = "TCartItem.findByStrBusinessId", query = "SELECT t FROM TCartItem t WHERE t.strBusinessId = :strBusinessId"),
    @NamedQuery(name = "TCartItem.findByBlDeleted", query = "SELECT t FROM TCartItem t WHERE t.blDeleted = :blDeleted")})
public class TCartItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lg_id", nullable = false)
    private Long lgId;
    @Basic(optional = false)
    @Column(name = "lg_item_id", nullable = false)
    private long lgItemId;
    @Column(name = "str_order_id", length = 50)
    private String strOrderId;
    @Basic(optional = false)
    @Column(name = "lg_cart_id", nullable = false)
    private long lgCartId;
    @Basic(optional = false)
    @Column(name = "em_status", nullable = false, length = 7)
    private String emStatus;
    @Basic(optional = false)
    @Column(name = "int_qty", nullable = false)
    private int intQty;
    @Basic(optional = false)
    @Column(name = "db_price", nullable = false)
    private double dbPrice;
    @Lob
    @Column(name = "str_discounts", length = 65535)
    private String strDiscounts;
    @Column(name = "dt_date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDateCreated;
    @Basic(optional = false)
    @Column(name = "str_business_id", nullable = false, length = 50)
    private String strBusinessId;
    @Basic(optional = false)
    @Column(name = "bl_deleted", nullable = false)
    private boolean blDeleted;

    public TCartItem() {
    }

    public TCartItem(Long lgId) {
        this.lgId = lgId;
    }

    public TCartItem(Long lgId, long lgItemId, long lgCartId, String emStatus, int intQty, double dbPrice, String strBusinessId, boolean blDeleted) {
        this.lgId = lgId;
        this.lgItemId = lgItemId;
        this.lgCartId = lgCartId;
        this.emStatus = emStatus;
        this.intQty = intQty;
        this.dbPrice = dbPrice;
        this.strBusinessId = strBusinessId;
        this.blDeleted = blDeleted;
    }

    public Long getLgId() {
        return lgId;
    }

    public void setLgId(Long lgId) {
        this.lgId = lgId;
    }

    public long getLgItemId() {
        return lgItemId;
    }

    public void setLgItemId(long lgItemId) {
        this.lgItemId = lgItemId;
    }

    public String getStrOrderId() {
        return strOrderId;
    }

    public void setStrOrderId(String strOrderId) {
        this.strOrderId = strOrderId;
    }

    public long getLgCartId() {
        return lgCartId;
    }

    public void setLgCartId(long lgCartId) {
        this.lgCartId = lgCartId;
    }

    public String getEmStatus() {
        return emStatus;
    }

    public void setEmStatus(String emStatus) {
        this.emStatus = emStatus;
    }

    public int getIntQty() {
        return intQty;
    }

    public void setIntQty(int intQty) {
        this.intQty = intQty;
    }

    public double getDbPrice() {
        return dbPrice;
    }

    public void setDbPrice(double dbPrice) {
        this.dbPrice = dbPrice;
    }

    public String getStrDiscounts() {
        return strDiscounts;
    }

    public void setStrDiscounts(String strDiscounts) {
        this.strDiscounts = strDiscounts;
    }

    public Date getDtDateCreated() {
        return dtDateCreated;
    }

    public void setDtDateCreated(Date dtDateCreated) {
        this.dtDateCreated = dtDateCreated;
    }

    public String getStrBusinessId() {
        return strBusinessId;
    }

    public void setStrBusinessId(String strBusinessId) {
        this.strBusinessId = strBusinessId;
    }

    public boolean getBlDeleted() {
        return blDeleted;
    }

    public void setBlDeleted(boolean blDeleted) {
        this.blDeleted = blDeleted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgId != null ? lgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCartItem)) {
            return false;
        }
        TCartItem other = (TCartItem) object;
        if ((this.lgId == null && other.lgId != null) || (this.lgId != null && !this.lgId.equals(other.lgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.orderService.models.TCartItem[ lgId=" + lgId + " ]";
    }
    
}
