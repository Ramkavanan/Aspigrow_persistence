package com.aspigrow.persistence.dao;

import com.aspigrow.persistence.entities.sObject.QuestionriesProposalLineItem;

public interface QuestionriesProposalLineItemDao extends GenericDao<QuestionriesProposalLineItem, Long> {

    /**
     * Saves a qplItem's information.
     * 
     * @param qplItem the object to be saved
     * @return the persisted QuestionriesProposalLineItem object
     * @throws Exception 
     */
    QuestionriesProposalLineItem saveQuestionriesProposalLineItem(QuestionriesProposalLineItem qplItem) throws Exception;

    /**
     * Retrieves the qplItem active status
     *
     * @param qplItemId the qplItem's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean inActiveQuestionriesProposalLineItem(Long qplItemId) throws Exception;
    
    /**
     * Delete the qplItem's information and update status
     *
     * @param prodId the qplItem's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean deleteQuestionriesProposalLineItem(Long qplItemId) throws Exception;

	QuestionriesProposalLineItem updateQuestionriesProposalLineItem(QuestionriesProposalLineItem qplItem) throws Exception;
	
	QuestionriesProposalLineItem getQuestionriesProposalLineItem(String externalId) throws Exception;
}
