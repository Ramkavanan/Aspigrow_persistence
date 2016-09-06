package com.aspigrow.persistence.dao;

import com.aspigrow.persistence.entities.user.Address;

public interface AddressDao extends GenericDao<Address, Long> {

    /**
     * Saves a address's information.
     * 
     * @param address the object to be saved
     * @return the persisted Address object
     * @throws Exception 
     */
    Address saveAddress(Address address) throws Exception;

    /**
     * Retrieves the address active status
     *
     * @param addressId the address's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean inActiveAddress(Long addressId) throws Exception;
    
    /**
     * Delete the address's information and update status
     *
     * @param prodId the address's id
     * @return the status in Boolean format
     * @throws Exception
     */
    boolean deleteAddress(Long addressId) throws Exception;

	Address updateAddress(Address address) throws Exception;
	
	Address getAddress(String externalId) throws Exception;
}
