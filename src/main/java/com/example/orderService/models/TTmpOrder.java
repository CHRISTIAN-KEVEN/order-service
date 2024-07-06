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
@Table(name = "t_tmp_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTmpOrder.findAll", query = "SELECT t FROM TTmpOrder t"),
    @NamedQuery(name = "TTmpOrder.findByLgId", query = "SELECT t FROM TTmpOrder t WHERE t.lgId = :lgId"),
    @NamedQuery(name = "TTmpOrder.findByStrRef", query = "SELECT t FROM TTmpOrder t WHERE t.strRef = :strRef"),
    @NamedQuery(name = "TTmpOrder.findByStrItemsToCheckout", query = "SELECT t FROM TTmpOrder t WHERE t.strItemsToCheckout = :strItemsToCheckout"),
    @NamedQuery(name = "TTmpOrder.findByDtCreated", query = "SELECT t FROM TTmpOrder t WHERE t.dtCreated = :dtCreated")})
public class TTmpOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lg_id", nullable = false)
    private Long lgId;
    @Basic(optional = false)
    @Column(name = "str_ref", nullable = false, length = 50)
    private String strRef;
    @Basic(optional = false)
    @Column(name = "str_items_to_checkout", nullable = false, length = 200)
    private String strItemsToCheckout;
    @Column(name = "dt_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCreated;
    @Basic(optional = false)
    @Lob
    @Column(name = "str_order_obj", nullable = false, length = 2147483647)
    private String strOrderObj;

    public TTmpOrder() {
    }

    public TTmpOrder(Long lgId) {
        this.lgId = lgId;
    }

    public TTmpOrder(Long lgId, String strRef, String strItemsToCheckout, String strOrderObj) {
        this.lgId = lgId;
        this.strRef = strRef;
        this.strItemsToCheckout = strItemsToCheckout;
        this.strOrderObj = strOrderObj;
    }

    public Long getLgId() {
        return lgId;
    }

    public void setLgId(Long lgId) {
        this.lgId = lgId;
    }

    public String getStrRef() {
        return strRef;
    }

    public void setStrRef(String strRef) {
        this.strRef = strRef;
    }

    public String getStrItemsToCheckout() {
        return strItemsToCheckout;
    }

    public void setStrItemsToCheckout(String strItemsToCheckout) {
        this.strItemsToCheckout = strItemsToCheckout;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(Date dtCreated) {
        this.dtCreated = dtCreated;
    }

    public String getStrOrderObj() {
        return strOrderObj;
    }

    public void setStrOrderObj(String strOrderObj) {
        this.strOrderObj = strOrderObj;
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
        if (!(object instanceof TTmpOrder)) {
            return false;
        }
        TTmpOrder other = (TTmpOrder) object;
        if ((this.lgId == null && other.lgId != null) || (this.lgId != null && !this.lgId.equals(other.lgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.orderService.models.TTmpOrder[ lgId=" + lgId + " ]";
    }
    
}
