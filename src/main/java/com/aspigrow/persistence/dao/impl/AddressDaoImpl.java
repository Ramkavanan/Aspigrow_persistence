package com.aspigrow.persistence.dao.impl;

import java.util.Date;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aspigrow.persistence.dao.AddressDao;
import com.aspigrow.persistence.entities.user.Address;

@Repository("addressDao")
@Transactional
public class AddressDaoImpl extends GenericDaoImpl<Address, Long> implements AddressDao {

    @Autowired
    public AddressDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Address.class);
    }

    /**
     * Overridden simply to call the saveAddress method. This is happening
     * because saveAddress flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param address the address to save
     * @return the modified address (with a primary key set if they're new)
     */
    @Override
    public Address save(Address address)  {
        try{
            if (address == null) {
                return null;
            }
            address.setArchived(false);
            address.setDeleted(false);
            address.setCreatedAt(new Date());
            address.setModifiedAt(new Date());
            return this.saveAddress(address);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Address saveAddress(Address address) throws Exception {
    	address.setExternalId(UUID.randomUUID().toString());
        getSession().save(address);
        getSession().flush();
        return address;
    }

    

    /**
     * {@inheritDoc}
     */
    public boolean inActiveAddress(Long addressId) throws Exception {
        Address address = get(addressId);
        if(address == null)
            return false;
        address.setModifiedAt(new Date());
        update(address);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteAddress(Long addressId) throws Exception {
        Address address = get(addressId);
        if(address == null)
            return false;
        address.setDeleted(false);
        address.setModifiedAt(new Date());
        update(address);
        return true;
    }

	public Address updateAddress(Address address) throws Exception {
		if( address == null)
			return null;
		address.setModifiedAt(new Date());
		address = update(address);
		return address;
	}
	
	public Address getAddress(String externalId) throws Exception {
		if( externalId == null)
			return null;
		return (Address) getSession().createCriteria(Address.class)
                .add(getAddressExample(externalId))
                .uniqueResult();
		
	}

	private static Example getAddressExample(String externalId) {
		Address address = new Address();
        address.setDeleted(false);
		address.setExternalId(externalId);
        	return Example.create(address);
    	}
}
