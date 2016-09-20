package com.aspigrow.persistence.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;
import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aspigrow.persistence.dao.UserDao;
import com.aspigrow.persistence.entities.user.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    /**
     * Overridden simply to call the saveUser method. This is happening
     * because saveUser flushes the session and saveObject of BaseDaoHibernate
     * does not.
     *
     * @param user the user to save
     * @return the modified user (with a primary key set if they're new)
     */
    @Override
    public User save(User user)  {
        try{
        	System.out.println("Came Dao implementation layer ==== ");
            if (user == null) {
            	System.out.println("Came null pointer value === ");
                return null;
            }
            System.out.println("Came not null part === ");
            user.setArchived(false);
            user.setDeleted(false);
            user.setCreatedAt(new Date());
            user.setModifiedAt(new Date());
            user.setEnabled(true);
            return this.saveUser(user);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public User saveUser(User user) throws Exception {
        user.setExternalId(UUID.randomUUID().toString());
        getSession().save(user);
        getSession().flush();
        return user;
    }

    /**
     * {@inheritDoc}
     */
    public String getUserPassword(Long userId) throws Exception {
        User user = get(userId);
        if(user == null)
            throw new UserPrincipalNotFoundException("User not found for given principle id "+userId);
        return user.getPassword();
    }

    /**
     * {@inheritDoc}
     */
    public boolean inActiveUser(Long userId) throws Exception {
        User user = get(userId);
        if(user == null)
            return false;
        user.setEnabled(false);
        user.setModifiedAt(new Date());
        update(user);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteUser(Long userId) throws Exception {
        User user = get(userId);
        if(user == null)
            return false;
        user.setDeleted(false);
        user.setModifiedAt(new Date());
        update(user);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        List users = getSession().createCriteria(User.class).add(Restrictions.eq("email", username)).list();
        if (users == null || users.isEmpty()) {
           // throw new UsernameNotFoundException("user '" + username + "' not found...");
            return null;
        } else {
            return (User) users.get(0);
        }
    }    
    
    public User findByEmail(String email) throws UsernameNotFoundException {
    	List users = getSession().createCriteria(User.class).add(Restrictions.eq("email", email)).list();
        if (users == null || users.isEmpty()) {
           // throw new UsernameNotFoundException("user '" + username + "' not found...");
            return null;
        } else {
            return (User) users.get(0);
        }
    }
}
