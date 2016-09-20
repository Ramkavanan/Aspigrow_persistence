package com.aspigrow.persistence.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aspigrow.persistence.dao.QuestionriesProposalHeaderDao;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalHeader;

@Repository("quesProplHeaderDao")
@Transactional
public class QuestionriesProposalHeaderDaoImpl extends GenericDaoImpl<QuestionriesProposalHeader, Long> implements QuestionriesProposalHeaderDao {

    @Autowired
    public QuestionriesProposalHeaderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, QuestionriesProposalHeader.class);
    }

    /**
     * Overridden simply to call the saveQuestionriesProposalHeader method. This is happening
     * because saveQuestionriesProposalHeader flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param quesProplHeader the quesProplHeader to save
     * @return the modified quesProplHeader (with a primary key set if they're new)
     */
    @Override
    public QuestionriesProposalHeader save(QuestionriesProposalHeader quesProplHeader)  {
        try{
            if (quesProplHeader == null) {
                return null;
            }
            quesProplHeader.setArchived(false);
            quesProplHeader.setDeleted(false);
            quesProplHeader.setCreatedAt(new Date());
            quesProplHeader.setModifiedAt(new Date());
            System.out.println("Came Here At ------   "+quesProplHeader.getCreatedAt());
            return this.saveQuestionriesProposalHeader(quesProplHeader);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public QuestionriesProposalHeader saveQuestionriesProposalHeader(QuestionriesProposalHeader quesProplHeader) throws Exception {
    	quesProplHeader.setExternalId(UUID.randomUUID().toString());
        getSession().save(quesProplHeader);
        getSession().flush();
        return quesProplHeader;
    }

    

    /**
     * {@inheritDoc}
     */
    public boolean inActiveQuestionriesProposalHeader(Long quesProplHeaderId) throws Exception {
        QuestionriesProposalHeader quesProplHeader = get(quesProplHeaderId);
        if(quesProplHeader == null)
            return false;
        quesProplHeader.setModifiedAt(new Date());
        update(quesProplHeader);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteQuestionriesProposalHeader(Long quesProplHeaderId) throws Exception {
        QuestionriesProposalHeader quesProplHeader = get(quesProplHeaderId);
        if(quesProplHeader == null)
            return false;
        quesProplHeader.setDeleted(false);
        quesProplHeader.setModifiedAt(new Date());
        update(quesProplHeader);
        return true;
    }

	public QuestionriesProposalHeader updateQuestionriesProposalHeader(QuestionriesProposalHeader quesProplHeader) throws Exception {
		if( quesProplHeader == null)
			return null;
		quesProplHeader.setModifiedAt(new Date());
		quesProplHeader = update(quesProplHeader);
		return quesProplHeader;
	}
	
	public QuestionriesProposalHeader getQuestionriesProposalHeader(String externalId) throws Exception {
		if( externalId == null)
			return null;
		return (QuestionriesProposalHeader) getSession().createCriteria(QuestionriesProposalHeader.class)
                .add(getQuestionriesProposalHeaderExample(externalId))
                .uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<QuestionriesProposalHeader> getQuestionriesProposalHeaderByContactId(String contactId) throws Exception {
		if( contactId == null)
			return null;
		return (List<QuestionriesProposalHeader>) getSession().createCriteria(QuestionriesProposalHeader.class)
                .add(getQuesProcessHeaderContactExample(contactId)).list();
	}

	private static Example getQuestionriesProposalHeaderExample(String externalId) {
		QuestionriesProposalHeader quesProplHeader = new QuestionriesProposalHeader();
        quesProplHeader.setDeleted(false);
		quesProplHeader.setExternalId(externalId);
        return Example.create(quesProplHeader);
    }
	
	private static Example getQuesProcessHeaderContactExample(String contactId) {
		QuestionriesProposalHeader quesProplHeader = new QuestionriesProposalHeader();
        quesProplHeader.setDeleted(false);
		quesProplHeader.setContact(contactId);
        return Example.create(quesProplHeader);
    }
}
