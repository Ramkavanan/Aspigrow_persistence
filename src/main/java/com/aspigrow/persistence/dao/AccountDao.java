package com.aspigrow.persistence.dao;

import com.aspigrow.persistence.entities.sObject.Account;

public interface AccountDao extends GenericDao<Account, Long> {

    /**
     * Saves a account's information.
     * 
     * @param account the object to be saved
     * @return the persisted Account object
     * @throws Exception 
     */
    Account saveAccount(Account account) throws Exception;

    /**
     * Retrieves the account active status
     *
     * @param accountId the account's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean inActiveAccount(Long accountId) throws Exception;
    
    /**
     * Delete the account's information and update status
     *
     * @param prodId the account's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean deleteAccount(Long accountId) throws Exception;

	Account updateAccount(Account account) throws Exception;
	
	Account getAccount(String externalId) throws Exception;
}
