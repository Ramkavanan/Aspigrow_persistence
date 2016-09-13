package com.aspigrow.persistence.dao.impl;

import java.util.Date;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aspigrow.persistence.dao.AgreementDao;
import com.aspigrow.persistence.entities.sObject.Agreement;

@Repository("agreementDao")
@Transactional
public class AgreementDaoImpl extends GenericDaoImpl<Agreement, Long> implements AgreementDao {

    @Autowired
    public AgreementDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Agreement.class);
    }

    /**
     * Overridden simply to call the saveAgreement method. This is happening
     * because saveAgreement flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param agreement the agreement to save
     * @return the modified agreement (with a primary key set if they're new)
     */
    @Override
    public Agreement save(Agreement agreement)  {
        try{
            if (agreement == null) {
                return null;
            }
            agreement.setArchived(false);
            agreement.setDeleted(false);
            agreement.setCreatedAt(new Date());
            agreement.setModifiedAt(new Date());
            return this.saveAgreement(agreement);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Agreement saveAgreement(Agreement agreement) throws Exception {
    	agreement.setExternalId(UUID.randomUUID().toString());
        getSession().save(agreement);
        getSession().flush();
        return agreement;
    }

    

    /**
     * {@inheritDoc}
     */
    public boolean inActiveAgreement(Long agreementId) throws Exception {
        Agreement agreement = get(agreementId);
        if(agreement == null)
            return false;
        agreement.setModifiedAt(new Date());
        update(agreement);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteAgreement(Long agreementId) throws Exception {
        Agreement agreement = get(agreementId);
        if(agreement == null)
            return false;
        agreement.setDeleted(false);
        agreement.setModifiedAt(new Date());
        update(agreement);
        return true;
    }

	public Agreement updateAgreement(Agreement agreement) throws Exception {
		if( agreement == null)
			return null;
		agreement.setModifiedAt(new Date());
		agreement = update(agreement);
		return agreement;
	}
	
	public Agreement getAgreement(String externalId) throws Exception {
		if( externalId == null)
			return null;
		return (Agreement) getSession().createCriteria(Agreement.class)
                .add(getAgreementExample(externalId))
                .uniqueResult();
		
	}

	private static Example getAgreementExample(String externalId) {
		Agreement agreement = new Agreement();
        agreement.setDeleted(false);
		agreement.setExternalId(externalId);
        	return Example.create(agreement);
    	}
}
