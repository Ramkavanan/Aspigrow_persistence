package com.aspigrow.persistence.dao;

import java.util.List;

import com.aspigrow.persistence.entities.sObject.QuestionriesProposalHeader;

public interface QuestionriesProposalHeaderDao extends GenericDao<QuestionriesProposalHeader, Long> {

    /**
     * Saves a qpheader's information.
     * 
     * @param qpheader the object to be saved
     * @return the persisted QuestionriesProposalHeader object
     * @throws Exception 
     */
    QuestionriesProposalHeader saveQuestionriesProposalHeader(QuestionriesProposalHeader qpheader) throws Exception;

    /**
     * Retrieves the qpheader active status
     *
     * @param qpheaderId the qpheader's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean inActiveQuestionriesProposalHeader(Long qpheaderId) throws Exception;
    
    /**
     * Delete the qpheader's information and update status
     *
     * @param prodId the qpheader's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean deleteQuestionriesProposalHeader(Long qpheaderId) throws Exception;

	QuestionriesProposalHeader updateQuestionriesProposalHeader(QuestionriesProposalHeader qpheader) throws Exception;
	
	QuestionriesProposalHeader getQuestionriesProposalHeader(String externalId) throws Exception;
	
	List<QuestionriesProposalHeader> getQuestionriesProposalHeaderByContactId(String contactId) throws Exception;
}
