package com.aspigrow.persistence.entities.sObject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aspigrow.persistence.entities.BaseObject;
import com.aspigrow.persistence.entities.GenericEntity;

@Entity
@Table(name="account")
public class Account  extends BaseObject implements GenericEntity<Integer> {

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

    @Column(name="name", unique = true)
    private String name;
    
    @Column(name="accountNumber")
    private String acctNumber;
	
    @Column(name="salesforceId", unique = true)
    private String salesforceId;
    
    @Column(name="site")
    private String site;

    @OneToMany(mappedBy="account",cascade = CascadeType.ALL)
    private Set<Contact>  contacts = new HashSet<Contact>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "opportunity", referencedColumnName = "id", insertable = true, updatable = true)
    private Opportunity opportunity;
    
	public String getSalesforceId() {
		return salesforceId;
	}

	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Opportunity getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
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
