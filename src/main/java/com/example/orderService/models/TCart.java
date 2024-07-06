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
@Table(name = "t_cart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TCart.findAll", query = "SELECT t FROM TCart t"),
    @NamedQuery(name = "TCart.findByLgId", query = "SELECT t FROM TCart t WHERE t.lgId = :lgId"),
    @NamedQuery(name = "TCart.findByStrUserId", query = "SELECT t FROM TCart t WHERE t.strUserId = :strUserId"),
    @NamedQuery(name = "TCart.findByDtCreated", query = "SELECT t FROM TCart t WHERE t.dtCreated = :dtCreated")})
public class TCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lg_id", nullable = false)
    private Long lgId;
    @Basic(optional = false)
    @Column(name = "str_user_id", nullable = false, length = 50)
    private String strUserId;
    @Column(name = "dt_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCreated;

    public TCart() {
    }

    public TCart(Long lgId) {
        this.lgId = lgId;
    }

    public TCart(Long lgId, String strUserId) {
        this.lgId = lgId;
        this.strUserId = strUserId;
    }

    public Long getLgId() {
        return lgId;
    }

    public void setLgId(Long lgId) {
        this.lgId = lgId;
    }

    public String getStrUserId() {
        return strUserId;
    }

    public void setStrUserId(String strUserId) {
        this.strUserId = strUserId;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(Date dtCreated) {
        this.dtCreated = dtCreated;
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
        if (!(object instanceof TCart)) {
            return false;
        }
        TCart other = (TCart) object;
        if ((this.lgId == null && other.lgId != null) || (this.lgId != null && !this.lgId.equals(other.lgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.orderService.models.TCart[ lgId=" + lgId + " ]";
    }
    
}
