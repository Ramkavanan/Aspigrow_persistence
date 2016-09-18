package com.aspigrow.persistence.entities.sObject;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aspigrow.persistence.entities.BaseObject;
import com.aspigrow.persistence.entities.GenericEntity;

@Entity
@Table(name="quesProposalLineItem")
public class QuestionriesProposalLineItem  extends BaseObject implements GenericEntity<Integer> {

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
   
    @Column(name="name")
    private String name;
    
    @Column(name="answer")
    private String answer;
    
    @Column(name="comment")
    private String comment;
    
    @Column(name="helpText")
    private String helpText;
    
    @Column(name="pickListOpts")
    private String pickListOpts;
    
    @Column(name="question")
    private String question;
    
    @Column(name="type")
    private String type;
    
    @Column(name="questItemId")
    private String questItemId;

    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "quesProcessHeader", referencedColumnName = "id", insertable = true, updatable = true)
    private QuestionriesProposalHeader quesProcessHeader;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getHelpText() {
		return helpText;
	}

	public void setHelpText(String helpText) {
		this.helpText = helpText;
	}

	public String getPickListOpts() {
		return pickListOpts;
	}

	public void setPickListOpts(String pickListOpts) {
		this.pickListOpts = pickListOpts;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
