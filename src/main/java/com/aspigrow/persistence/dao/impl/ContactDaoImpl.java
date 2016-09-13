package com.aspigrow.persistence.dao.impl;

import java.util.Date;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aspigrow.persistence.dao.ContactDao;
import com.aspigrow.persistence.entities.sObject.Contact;

@Repository("contactDao")
@Transactional
public class ContactDaoImpl extends GenericDaoImpl<Contact, Long> implements ContactDao {

    @Autowired
    public ContactDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Contact.class);
    }

    /**
     * Overridden simply to call the saveContact method. This is happening
     * because saveContact flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param contact the contact to save
     * @return the modified contact (with a primary key set if they're new)
     */
    @Override
    public Contact save(Contact contact)  {
        try{
            if (contact == null) {
                return null;
            }
            contact.setArchived(false);
            contact.setDeleted(false);
            contact.setCreatedAt(new Date());
            contact.setModifiedAt(new Date());
            return this.saveContact(contact);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Contact saveContact(Contact contact) throws Exception {
    	contact.setExternalId(UUID.randomUUID().toString());
        getSession().save(contact);
        getSession().flush();
        return contact;
    }

    

    /**
     * {@inheritDoc}
     */
    public boolean inActiveContact(Long contactId) throws Exception {
        Contact contact = get(contactId);
        if(contact == null)
            return false;
        contact.setModifiedAt(new Date());
        update(contact);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteContact(Long contactId) throws Exception {
        Contact contact = get(contactId);
        if(contact == null)
            return false;
        contact.setDeleted(false);
        contact.setModifiedAt(new Date());
        update(contact);
        return true;
    }

	public Contact updateContact(Contact contact) throws Exception {
		if( contact == null)
			return null;
		contact.setModifiedAt(new Date());
		contact = update(contact);
		return contact;
	}
	
	public Contact getContact(String externalId) throws Exception {
		if( externalId == null)
			return null;
		return (Contact) getSession().createCriteria(Contact.class)
                .add(getContactExample(externalId))
                .uniqueResult();
		
	}

	private static Example getContactExample(String externalId) {
		Contact contact = new Contact();
        contact.setDeleted(false);
		contact.setExternalId(externalId);
        	return Example.create(contact);
    	}
}
