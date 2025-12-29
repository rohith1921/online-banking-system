package com.bank.dao.imp;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;
import com.bank.model.AccountType;
import com.bank.util.JpaUtil;

public class AccountDAOImp implements AccountDAO{

	@Override 
	public void save(Account account) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(account);
			transaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		
	}

	@Override
	public void update(Account account) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.merge(account);
			transaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		
	}

	@Override
	public Account findByAccountNumber(Long accountNumber) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			return em.find(Account.class, accountNumber);
		} finally {
			em.close();
		}
		
	}

	@Override
	public List<Account> findAccountByUserId(Integer userId) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			return em.createQuery("select account from Account account where account.user.userid=?1", Account.class)
			.setParameter(1, userId)
			.getResultList(); 
		} finally {
			em.close();
		}
	}

	@Override
	public List<Account> findAllAccounts() {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			return em.createQuery("select account from Account account", Account.class)
			.getResultList(); 
		} finally {
			em.close();
		}
	}

	@Override
	public boolean existsByUserAndType(Integer userId, AccountType accountType) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			Long count = em.createQuery(
					"select count(a) from Account a "+
					"where a.user.userid = :uid and a.accountType = :type",
					Long.class
			).setParameter("uid", userId)
		     .setParameter("type", accountType)
		     .getSingleResult();
			
			return count > 0;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Account> findPendingAccounts() {
		EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	                "FROM Account a WHERE a.status = :status", Account.class)
	                .setParameter("status", "PENDING")
	                .getResultList();
	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public long countAccounts() {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	            "SELECT COUNT(a) FROM Account a", Long.class
	        ).getSingleResult();
	    } finally {
	        em.close();
	    }
	}

	@Override
	public long countPendingAccounts() {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	            "SELECT COUNT(a) FROM Account a WHERE a.status = 'PENDING'",
	            Long.class
	        ).getSingleResult();
	    } finally {
	        em.close();
	    }
	}

}
