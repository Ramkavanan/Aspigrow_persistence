package com.aspigrow.persistence.entities.sObject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aspigrow.persistence.entities.BaseObject;
import com.aspigrow.persistence.entities.GenericEntity;

@Entity
@Table(name="contact")
public class Contact  extends BaseObject implements GenericEntity<Integer> {

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

      
    @Column(name="externalid", unique = true)
    private String externalId;

    @Column(name="firstName")
    private String firstName;
    
    @Column(name="lastName")
    private String lastName;
    
    @Column(name="Email")
    private String Email;
    
    @Column(name="Phone")
    private String Phone;
  
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
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
