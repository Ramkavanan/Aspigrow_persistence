package com.aspigrow.persistence.dao.impl;

import java.util.Date;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aspigrow.persistence.dao.QuestionriesProposalLineItemDao;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalLineItem;

@Repository("quesProplLineItemDao")
@Transactional
public class QuestionriesProposalLineItemDaoImpl extends GenericDaoImpl<QuestionriesProposalLineItem, Long> implements QuestionriesProposalLineItemDao {

    @Autowired
    public QuestionriesProposalLineItemDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, QuestionriesProposalLineItem.class);
    }

    /**
     * Overridden simply to call the saveQuestionriesProposalLineItem method. This is happening
     * because saveQuestionriesProposalLineItem flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param quesProplLineItem the quesProplLineItem to save
     * @return the modified quesProplLineItem (with a primary key set if they're new)
     */
    @Override
    public QuestionriesProposalLineItem save(QuestionriesProposalLineItem quesProplLineItem)  {
        try{
            if (quesProplLineItem == null) {
                return null;
            }
            quesProplLineItem.setArchived(false);
            quesProplLineItem.setDeleted(false);
            quesProplLineItem.setCreatedAt(new Date());
            quesProplLineItem.setModifiedAt(new Date());
            return this.saveQuestionriesProposalLineItem(quesProplLineItem);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public QuestionriesProposalLineItem saveQuestionriesProposalLineItem(QuestionriesProposalLineItem quesProplLineItem) throws Exception {
    	quesProplLineItem.setExternalId(UUID.randomUUID().toString());
        getSession().save(quesProplLineItem);
        getSession().flush();
        return quesProplLineItem;
    }

    

    /**
     * {@inheritDoc}
     */
    public boolean inActiveQuestionriesProposalLineItem(Long quesProplLineItemId) throws Exception {
        QuestionriesProposalLineItem quesProplLineItem = get(quesProplLineItemId);
        if(quesProplLineItem == null)
            return false;
        quesProplLineItem.setModifiedAt(new Date());
        update(quesProplLineItem);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteQuestionriesProposalLineItem(Long quesProplLineItemId) throws Exception {
        QuestionriesProposalLineItem quesProplLineItem = get(quesProplLineItemId);
        if(quesProplLineItem == null)
            return false;
        quesProplLineItem.setDeleted(false);
        quesProplLineItem.setModifiedAt(new Date());
        update(quesProplLineItem);
        return true;
    }

	public QuestionriesProposalLineItem updateQuestionriesProposalLineItem(QuestionriesProposalLineItem quesProplLineItem) throws Exception {
		if( quesProplLineItem == null)
			return null;
		quesProplLineItem.setModifiedAt(new Date());
		quesProplLineItem = update(quesProplLineItem);
		return quesProplLineItem;
	}
	
	public QuestionriesProposalLineItem getQuestionriesProposalLineItem(String externalId) throws Exception {
		if( externalId == null)
			return null;
		return (QuestionriesProposalLineItem) getSession().createCriteria(QuestionriesProposalLineItem.class)
                .add(getQuestionriesProposalLineItemExample(externalId))
                .uniqueResult();
		
	}

	private static Example getQuestionriesProposalLineItemExample(String externalId) {
		QuestionriesProposalLineItem quesProplLineItem = new QuestionriesProposalLineItem();
        quesProplLineItem.setDeleted(false);
		quesProplLineItem.setExternalId(externalId);
        	return Example.create(quesProplLineItem);
    	}
}
