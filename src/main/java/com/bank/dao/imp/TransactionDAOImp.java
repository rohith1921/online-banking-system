package com.bank.dao.imp;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.bank.dao.TransactionDAO;
import com.bank.model.Transaction;
import com.bank.util.JpaUtil;

import net.bytebuddy.asm.Advice.Return;

public class TransactionDAOImp implements TransactionDAO{

	@Override
	public void save(Transaction txn) {
		EntityManager em = JpaUtil.getEntityManager();
		try { 
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.persist(txn);
			transaction.commit();
		} catch(Exception e) {
			e.getStackTrace();
			throw e;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Transaction> findRecentTransactions(Long accountNumber, int limit) {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	            "select t from Transaction t " +
	            "where t.account.accountnumber = :accNo " +
	            "order by t.timestamp desc",
	            Transaction.class
	        )
	        .setParameter("accNo", accountNumber)
	        .setMaxResults(limit)
	        .getResultList();
	    } catch(Exception e) {
	    	e.printStackTrace();
	    	throw e;
	    } finally {
	        em.close();
	    }
	}


	@Override
	public List<Transaction> findByAccountNumber(Long accountNumber) {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	            "select t from Transaction t " +
	            "where t.account.accountnumber = :accNo " +
	            "order by t.timestamp",
	            Transaction.class
	        )
	        .setParameter("accNo", accountNumber)
	        .getResultList();
	    } catch(Exception e) {
	    	e.printStackTrace();
	    	throw e;
	    } finally {
	        em.close();
	    }
	}


	@Override
	public List<Transaction> findByDateRange(
	        Long accountNumber,
	        LocalDateTime start,
	        LocalDateTime end) {

	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	            "select t from Transaction t " +
	            "where t.account.accountnumber = :accNo " +
	            "and t.timestamp between :start and :end " +
	            "order by t.timestamp",
	            Transaction.class
	        )
	        .setParameter("accNo", accountNumber)
	        .setParameter("start", start)
	        .setParameter("end", end)
	        .getResultList();
	    } catch(Exception e) {
	    	throw e;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public List<Transaction> findAllTransactions() {
		EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery("FROM Transaction", Transaction.class)
	                 .getResultList();
	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public long countTransactions() {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	            "SELECT COUNT(t) FROM Transaction t", Long.class
	        ).getSingleResult();
	    } finally {
	        em.close();
	    }
	}


}
