package com.aspigrow.persistence.entities.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aspigrow.persistence.entities.BaseObject;
import com.aspigrow.persistence.entities.GenericEntity;

import java.util.Date;

@Entity
@Table(name="address")
public class Address  extends BaseObject implements GenericEntity<Integer> {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="isDeleted", nullable=false)
    private Boolean isDeleted;

    @Column(name="isArchived", nullable=false)
    private Boolean isArchived;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdAt", nullable=false, updatable=false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modifiedAt", nullable=false)
    private Date modifiedAt;

    @Column(name="addressline1", nullable=false)
    private String addressLine1;

    @Column(name="addressline2", nullable=false)
    private String addressLine2;
    
    @Column(name="externalid", unique = true)
    private String externalId;

    @Column(name="city", nullable=false)
    private String city;

    @Column(name="state", nullable=false)
    private String state;

    @Column(name="pincode", nullable=false)
    private Integer pincode;
    
    @OneToOne(mappedBy = "shippingAddress")
    private User user;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public Boolean isArchived() {
        return isArchived;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }
    
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
   
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("id=").append(id);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", isArchived=").append(isArchived);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", modifiedAt=").append(modifiedAt);
        sb.append(", addressLine1='").append(addressLine1).append('\'');
        sb.append(", addressLine2='").append(addressLine2).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", pincode='").append(pincode).append('\'');
        sb.append('}');
        return sb.toString();
    }

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
}
