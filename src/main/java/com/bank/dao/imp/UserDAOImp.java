package com.bank.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bank.dao.UserDAO;
import com.bank.model.User;
import com.bank.util.JpaUtil;

public class UserDAOImp implements UserDAO{

	@Override 
	public void save(User user) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(user);
			transaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			em.close();
		}
	}

	@Override
	public void update(User user) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.merge(user);
			transaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public User findById(Integer userId) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			return em.find(User.class, userId);
		} finally {
			em.close();
		}
	}

	@Override
	public User findByEmail(String email) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			return em.createQuery("select user from User user where user.email=?1", User.class)
			.setParameter(1, email)
			.getResultList()
			.stream()
			.findFirst()
			.orElse(null);
		} finally {
			em.close();
		}
	}

	@Override
	public User findByMobile(long mobile) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			return  em.createQuery("select user from User user where user.mobilenumber=?1", User.class)
			.setParameter(1, mobile)
			.getResultList()
			.stream()
			.findFirst()
			.orElse(null); 
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(User user) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(user);
			transaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close(); 
		}
	}

	@Override
	public List<User> findAllUsers() {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	            "select distinct u from User u left join fetch u.accounts",
	            User.class
	        ).getResultList();
	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public long countUsers() {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	            "SELECT COUNT(u) FROM User u", Long.class
	        ).getSingleResult();
	    } finally {
	        em.close();
	    }
	}



}
