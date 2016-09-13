package com.aspigrow.persistence.dao;

import com.aspigrow.persistence.entities.sObject.Opportunity;

public interface OpportunityDao extends GenericDao<Opportunity, Long> {

    /**
     * Saves a opportunity's information.
     * 
     * @param opportunity the object to be saved
     * @return the persisted Opportunity object
     * @throws Exception 
     */
    Opportunity saveOpportunity(Opportunity opportunity) throws Exception;

    /**
     * Retrieves the opportunity active status
     *
     * @param opportunityId the opportunity's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean inActiveOpportunity(Long opportunityId) throws Exception;
    
    /**
     * Delete the opportunity's information and update status
     *
     * @param prodId the opportunity's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean deleteOpportunity(Long opportunityId) throws Exception;

	Opportunity updateOpportunity(Opportunity opportunity) throws Exception;
	
	Opportunity getOpportunity(String externalId) throws Exception;
}
