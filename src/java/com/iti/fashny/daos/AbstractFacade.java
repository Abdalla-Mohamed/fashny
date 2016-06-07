/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iti.fashny.daos;

import com.iti.fashny.entities.Company;
import com.iti.fashny.entities.Place;
import com.iti.fashny.entities.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Hosam
 * @param <T>
 */
public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;
    private EntityManager entityManager;

    public AbstractFacade(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;

    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
//        getEntityManager().close();
//        getEntityManager().getTransaction().commit();
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }
    public T refreshObj(T entity) {
        return getEntityManager().merge(entity);
    }
    public void refresh(T entity) {
        getEntityManager().refresh(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    //---------------------------------------------------------------------
    public List<T> findByExample(T exampleObj) throws Exception {
        Session session = (Session) getEntityManager().getDelegate();
        Example example = Example.create(exampleObj).enableLike();

        Criteria c = session.createCriteria(exampleObj.getClass()).add(example);
        addAssociationExample(c, exampleObj);
        return c.list();
    }

    

    protected void addAssociationExample(Criteria c, T mainExample) {
    }

    protected void addTagConditionOnExample(Criteria c, List<Tag> tags, String listVariableNameInEntity) {

        List<Integer> wrappedParameter;
        wrappedParameter = new ArrayList<>();
        c.createAlias(listVariableNameInEntity, "tag");
        for (Tag tag : tags) {
            wrappedParameter.add(tag.getId());
        }
        c.add(Restrictions.in("tag.id", wrappedParameter));
    }

    protected void addPlaceConditionOnExample(Criteria c, List<Place> places, String listVariableNameInEntity) {

        List<Integer> wrappedParameter;
        wrappedParameter = new ArrayList<>();
        c.createAlias(listVariableNameInEntity, "place");
        for (Place place : places) {
            wrappedParameter.add(place.getId());
        }
        c.add(Restrictions.in("place.id", wrappedParameter));
    }

    protected void addCompanyConditionOnExample(Criteria c, Company company, String listVariableNameInEntity) {

        c.createAlias(listVariableNameInEntity, "company");

        c.add(Restrictions.eq("company.id", company.getId()));
    }

}
