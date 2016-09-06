package com.aspigrow.persistence.entities.user;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.UpdateTimestamp;

import com.aspigrow.persistence.entities.BaseObject;
import com.aspigrow.persistence.entities.GenericEntity;

@Entity
@Table(name="user")
public class User extends BaseObject implements GenericEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name="isDeleted", nullable=false)
    @Generated(GenerationTime.INSERT)
    @ColumnDefault("false")
    protected Boolean isDeleted;

    @Column(name="isArchived", nullable=false)
    @Generated(GenerationTime.INSERT)
    @ColumnDefault("false")
    protected Boolean isArchived;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="createdat")
    protected Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="modifiedat")
    protected Date modifiedAt;

    @Column(name="isActive")
    @ColumnDefault("true")
    private Boolean isActive;

    @Column(name="firstname")
    private String firstName;

    @Column(name="middlename")
    private String middleName;

    @Column(name="lastname")
    private String lastName;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="phone", nullable=false)
    private String phone;

    @Column(name="imageurl")
    private String imageUrl;

    @Column(name="externalid", unique = true)
    private String externalId;

    @ColumnDefault("false")
    @Generated(GenerationTime.INSERT)
    @Column(name="phoneVerified", nullable=false)
    private Boolean phoneVerified;
    
    @ColumnDefault("false")
    @Generated(GenerationTime.INSERT)
    @Column(name="emailVerified", nullable=false)
    private Boolean emailVerified;
    
    @Column(name="emailCode")
    private String emailCode;
    
    public User() { }

	public User(String phone, String email) {
        this.phone = phone;
        this.userName = phone;
        this.password = phone;
        this.email = email;
    }
	
    public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	public Boolean getIsEnabled() {
        return this.isActive;
    }

    public void setEnabled(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean isArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setphoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public Boolean getPhoneVerified() {
        return phoneVerified;
    }

    
    public void setPhoneVerified(Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }
    
    /**
     * Returns the full name.
     *
     * @return firstName + ' ' + lastName
     */
    @Transient
    public String getFullName() {
        return firstName + ' ' + lastName;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User user = (User) o;

        return !( userName != null ? !userName.equals(user.getUserName()) : user.getUserName() != null);
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (userName != null ? userName.hashCode() : 0);
    }

	/**
     * {@inheritDoc}
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id=").append(id);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", isArchived=").append(isArchived);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", modifiedAt=").append(modifiedAt);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", externalid='").append(externalId).append('\'');
        sb.append(", phoneVerified='").append(phoneVerified).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
