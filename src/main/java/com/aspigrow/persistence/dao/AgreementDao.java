package com.aspigrow.persistence.dao;

import com.aspigrow.persistence.entities.sObject.Agreement;

public interface AgreementDao extends GenericDao<Agreement, Long> {

    /**
     * Saves a agreement's information.
     * 
     * @param agreement the object to be saved
     * @return the persisted Agreement object
     * @throws Exception 
     */
    Agreement saveAgreement(Agreement agreement) throws Exception;

    /**
     * Retrieves the agreement active status
     *
     * @param agreementId the agreement's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean inActiveAgreement(Long agreementId) throws Exception;
    
    /**
     * Delete the agreement's information and update status
     *
     * @param prodId the agreement's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean deleteAgreement(Long agreementId) throws Exception;

	Agreement updateAgreement(Agreement agreement) throws Exception;
	
	Agreement getAgreement(String externalId) throws Exception;
}
