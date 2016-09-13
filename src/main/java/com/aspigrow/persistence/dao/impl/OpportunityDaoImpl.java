package com.aspigrow.persistence.dao.impl;

import java.util.Date;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aspigrow.persistence.dao.OpportunityDao;
import com.aspigrow.persistence.entities.sObject.Opportunity;

@Repository("opportunityDao")
@Transactional
public class OpportunityDaoImpl extends GenericDaoImpl<Opportunity, Long> implements OpportunityDao {

    @Autowired
    public OpportunityDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Opportunity.class);
    }

    /**
     * Overridden simply to call the saveOpportunity method. This is happening
     * because saveOpportunity flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param opportunity the opportunity to save
     * @return the modified opportunity (with a primary key set if they're new)
     */
    @Override
    public Opportunity save(Opportunity opportunity)  {
        try{
            if (opportunity == null) {
                return null;
            }
            opportunity.setArchived(false);
            opportunity.setDeleted(false);
            opportunity.setCreatedAt(new Date());
            opportunity.setModifiedAt(new Date());
            return this.saveOpportunity(opportunity);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Opportunity saveOpportunity(Opportunity opportunity) throws Exception {
    	opportunity.setExternalId(UUID.randomUUID().toString());
        getSession().save(opportunity);
        getSession().flush();
        return opportunity;
    }

    

    /**
     * {@inheritDoc}
     */
    public boolean inActiveOpportunity(Long opportunityId) throws Exception {
        Opportunity opportunity = get(opportunityId);
        if(opportunity == null)
            return false;
        opportunity.setModifiedAt(new Date());
        update(opportunity);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteOpportunity(Long opportunityId) throws Exception {
        Opportunity opportunity = get(opportunityId);
        if(opportunity == null)
            return false;
        opportunity.setDeleted(false);
        opportunity.setModifiedAt(new Date());
        update(opportunity);
        return true;
    }

	public Opportunity updateOpportunity(Opportunity opportunity) throws Exception {
		if( opportunity == null)
			return null;
		opportunity.setModifiedAt(new Date());
		opportunity = update(opportunity);
		return opportunity;
	}
	
	public Opportunity getOpportunity(String externalId) throws Exception {
		if( externalId == null)
			return null;
		return (Opportunity) getSession().createCriteria(Opportunity.class)
                .add(getOpportunityExample(externalId))
                .uniqueResult();
		
	}

	private static Example getOpportunityExample(String externalId) {
		Opportunity opportunity = new Opportunity();
        opportunity.setDeleted(false);
		opportunity.setExternalId(externalId);
        	return Example.create(opportunity);
    	}
}
