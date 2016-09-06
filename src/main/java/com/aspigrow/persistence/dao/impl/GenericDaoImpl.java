package com.aspigrow.persistence.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import com.aspigrow.persistence.dao.GenericDao;
import com.aspigrow.persistence.entities.GenericEntity;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic
 * 
 * @author RamaKavanan
 * @param <T>  a type variable
 * @param <PK> the primary key for that type
 */

@Transactional
public abstract class GenericDaoImpl<T extends GenericEntity, PK extends Serializable> implements GenericDao<T, PK> {

    private SessionFactory sessionFactory;

    private Class<T> persistentClass;

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDaoImpl(SessionFactory sessionFactory, Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
    }

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoImpl(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
    
    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);

        if (entity == null) {
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session sess = getSession();
        return sess.createCriteria(persistentClass).list();
    }

    /**
     * {@inheritDoc}
     */
    public T save(T entity) {
        if (entity == null) {
            return null;
        }
        entity.setArchived(false);
        entity.setDeleted(false);
        entity.setCreatedAt(new Date());
        entity.setModifiedAt(new Date());
        getSession().save(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T update(T entity) {
        if (entity == null) {
            return null;
        }
        entity.setModifiedAt(new Date());
        return (T) getSession().merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(T entity) {
        if (entity == null || entity.isDeleted()) {
            return;
        }
        entity.setDeleted(true);
        update(entity);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        if (entity == null || entity.isDeleted()) {
            return;
        }
        entity.setDeleted(true);
        update(entity);
    }
}
