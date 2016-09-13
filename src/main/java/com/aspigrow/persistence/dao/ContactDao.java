package com.aspigrow.persistence.dao;

import com.aspigrow.persistence.entities.sObject.Contact;

public interface ContactDao extends GenericDao<Contact, Long> {

    /**
     * Saves a contact's information.
     * 
     * @param contact the object to be saved
     * @return the persisted Contact object
     * @throws Exception 
     */
    Contact saveContact(Contact contact) throws Exception;

    /**
     * Retrieves the contact active status
     *
     * @param contactId the contact's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean inActiveContact(Long contactId) throws Exception;
    
    /**
     * Delete the contact's information and update status
     *
     * @param prodId the contact's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean deleteContact(Long contactId) throws Exception;

	Contact updateContact(Contact contact) throws Exception;
	
	Contact getContact(String externalId) throws Exception;
}
