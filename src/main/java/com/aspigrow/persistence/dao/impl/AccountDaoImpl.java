package com.aspigrow.persistence.dao.impl;

import java.util.Date;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aspigrow.persistence.dao.AccountDao;
import com.aspigrow.persistence.entities.sObject.Account;

@Repository("accountDao")
@Transactional
public class AccountDaoImpl extends GenericDaoImpl<Account, Long> implements AccountDao {

    @Autowired
    public AccountDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Account.class);
    }

    /**
     * Overridden simply to call the saveAccount method. This is happening
     * because saveAccount flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param account the account to save
     * @return the modified account (with a primary key set if they're new)
     */
    @Override
    public Account save(Account account)  {
        try{
            if (account == null) {
                return null;
            }
            account.setArchived(false);
            account.setDeleted(false);
            account.setCreatedAt(new Date());
            account.setModifiedAt(new Date());
            return this.saveAccount(account);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Account saveAccount(Account account) throws Exception {
    	account.setExternalId(UUID.randomUUID().toString());
        getSession().save(account);
        getSession().flush();
        return account;
    }

    

    /**
     * {@inheritDoc}
     */
    public boolean inActiveAccount(Long accountId) throws Exception {
        Account account = get(accountId);
        if(account == null)
            return false;
        account.setModifiedAt(new Date());
        update(account);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteAccount(Long accountId) throws Exception {
        Account account = get(accountId);
        if(account == null)
            return false;
        account.setDeleted(false);
        account.setModifiedAt(new Date());
        update(account);
        return true;
    }

	public Account updateAccount(Account account) throws Exception {
		if( account == null)
			return null;
		account.setModifiedAt(new Date());
		account = update(account);
		return account;
	}
	
	public Account getAccount(String externalId) throws Exception {
		if( externalId == null)
			return null;
		return (Account) getSession().createCriteria(Account.class)
                .add(getAccountExample(externalId))
                .uniqueResult();
		
	}

	private static Example getAccountExample(String externalId) {
		Account account = new Account();
        account.setDeleted(false);
		account.setExternalId(externalId);
        	return Example.create(account);
    	}
}
