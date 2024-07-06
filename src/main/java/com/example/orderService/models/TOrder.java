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
@Table(name = "t_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TOrder.findAll", query = "SELECT t FROM TOrder t"),
    @NamedQuery(name = "TOrder.findByStrOrderId", query = "SELECT t FROM TOrder t WHERE t.strOrderId = :strOrderId"),
    @NamedQuery(name = "TOrder.findByDbTotalAmount", query = "SELECT t FROM TOrder t WHERE t.dbTotalAmount = :dbTotalAmount"),
    @NamedQuery(name = "TOrder.findByIntItemCount", query = "SELECT t FROM TOrder t WHERE t.intItemCount = :intItemCount"),
    @NamedQuery(name = "TOrder.findByStrBusinessId", query = "SELECT t FROM TOrder t WHERE t.strBusinessId = :strBusinessId"),
    @NamedQuery(name = "TOrder.findByEmOrderStatus", query = "SELECT t FROM TOrder t WHERE t.emOrderStatus = :emOrderStatus"),
    @NamedQuery(name = "TOrder.findByBlComplete", query = "SELECT t FROM TOrder t WHERE t.blComplete = :blComplete"),
    @NamedQuery(name = "TOrder.findByDtOrderDate", query = "SELECT t FROM TOrder t WHERE t.dtOrderDate = :dtOrderDate"),
    @NamedQuery(name = "TOrder.findByDtDeliveryDate", query = "SELECT t FROM TOrder t WHERE t.dtDeliveryDate = :dtDeliveryDate"),
    @NamedQuery(name = "TOrder.findByStrOrderedBy", query = "SELECT t FROM TOrder t WHERE t.strOrderedBy = :strOrderedBy"),
    @NamedQuery(name = "TOrder.findByLgPaymentMethod", query = "SELECT t FROM TOrder t WHERE t.lgPaymentMethod = :lgPaymentMethod"),
    @NamedQuery(name = "TOrder.findByDbAmountPaid", query = "SELECT t FROM TOrder t WHERE t.dbAmountPaid = :dbAmountPaid"),
    @NamedQuery(name = "TOrder.findByStrRefCode", query = "SELECT t FROM TOrder t WHERE t.strRefCode = :strRefCode")})
public class TOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "str_order_id", nullable = false, length = 50)
    private String strOrderId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_total_amount", precision = 22, scale = 0)
    private Double dbTotalAmount;
    @Column(name = "int_item_count")
    private Integer intItemCount;
    @Basic(optional = false)
    @Column(name = "str_business_id", nullable = false, length = 50)
    private String strBusinessId;
    @Basic(optional = false)
    @Column(name = "em_order_status", nullable = false, length = 19)
    private String emOrderStatus;
    @Basic(optional = false)
    @Column(name = "bl_complete", nullable = false)
    private boolean blComplete;
    @Basic(optional = false)
    @Column(name = "dt_order_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtOrderDate;
    @Column(name = "dt_delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDeliveryDate;
    @Basic(optional = false)
    @Column(name = "str_ordered_by", nullable = false, length = 50)
    private String strOrderedBy;
    @Basic(optional = false)
    @Column(name = "lg_payment_method", nullable = false)
    private long lgPaymentMethod;
    @Basic(optional = false)
    @Column(name = "db_amount_paid", nullable = false)
    private double dbAmountPaid;
    @Column(name = "str_ref_code", length = 50)
    private String strRefCode;

    public TOrder() {
    }

    public TOrder(String strOrderId) {
        this.strOrderId = strOrderId;
    }

    public TOrder(String strOrderId, String strBusinessId, String emOrderStatus, boolean blComplete, Date dtOrderDate, String strOrderedBy, long lgPaymentMethod, double dbAmountPaid) {
        this.strOrderId = strOrderId;
        this.strBusinessId = strBusinessId;
        this.emOrderStatus = emOrderStatus;
        this.blComplete = blComplete;
        this.dtOrderDate = dtOrderDate;
        this.strOrderedBy = strOrderedBy;
        this.lgPaymentMethod = lgPaymentMethod;
        this.dbAmountPaid = dbAmountPaid;
    }

    public String getStrOrderId() {
        return strOrderId;
    }

    public void setStrOrderId(String strOrderId) {
        this.strOrderId = strOrderId;
    }

    public Double getDbTotalAmount() {
        return dbTotalAmount;
    }

    public void setDbTotalAmount(Double dbTotalAmount) {
        this.dbTotalAmount = dbTotalAmount;
    }

    public Integer getIntItemCount() {
        return intItemCount;
    }

    public void setIntItemCount(Integer intItemCount) {
        this.intItemCount = intItemCount;
    }

    public String getStrBusinessId() {
        return strBusinessId;
    }

    public void setStrBusinessId(String strBusinessId) {
        this.strBusinessId = strBusinessId;
    }

    public String getEmOrderStatus() {
        return emOrderStatus;
    }

    public void setEmOrderStatus(String emOrderStatus) {
        this.emOrderStatus = emOrderStatus;
    }

    public boolean getBlComplete() {
        return blComplete;
    }

    public void setBlComplete(boolean blComplete) {
        this.blComplete = blComplete;
    }

    public Date getDtOrderDate() {
        return dtOrderDate;
    }

    public void setDtOrderDate(Date dtOrderDate) {
        this.dtOrderDate = dtOrderDate;
    }

    public Date getDtDeliveryDate() {
        return dtDeliveryDate;
    }

    public void setDtDeliveryDate(Date dtDeliveryDate) {
        this.dtDeliveryDate = dtDeliveryDate;
    }

    public String getStrOrderedBy() {
        return strOrderedBy;
    }

    public void setStrOrderedBy(String strOrderedBy) {
        this.strOrderedBy = strOrderedBy;
    }

    public long getLgPaymentMethod() {
        return lgPaymentMethod;
    }

    public void setLgPaymentMethod(long lgPaymentMethod) {
        this.lgPaymentMethod = lgPaymentMethod;
    }

    public double getDbAmountPaid() {
        return dbAmountPaid;
    }

    public void setDbAmountPaid(double dbAmountPaid) {
        this.dbAmountPaid = dbAmountPaid;
    }

    public String getStrRefCode() {
        return strRefCode;
    }

    public void setStrRefCode(String strRefCode) {
        this.strRefCode = strRefCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (strOrderId != null ? strOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TOrder)) {
            return false;
        }
        TOrder other = (TOrder) object;
        if ((this.strOrderId == null && other.strOrderId != null) || (this.strOrderId != null && !this.strOrderId.equals(other.strOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.orderService.models.TOrder[ strOrderId=" + strOrderId + " ]";
    }
    
}
