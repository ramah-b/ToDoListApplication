package customPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Tasker;
import customTools.DBUtil;

public class TaskersDB {
	public static void insert(Tasker tasker) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(tasker);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Tasker tasker) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(tasker);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Tasker tasker) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(tasker));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static boolean usernameExists(String username) {
		Tasker t = selectTaskerByUsername(username);
		return t != null;
	}

	public static boolean emailExists(String email) {
		Tasker t = selectTaskerByEmail(email);
		return t != null;
	}

	public static Tasker selectTaskerByUsername(String username) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		username = username.toLowerCase();
		String qString = "select t from Tasker t where t.username = :username";
		TypedQuery<Tasker> q = em.createQuery(qString, Tasker.class);
		q.setParameter("username", username);
		Tasker tasker = null;
		try {
			tasker = q.getSingleResult();
		}catch (NoResultException e){
			System.out.println(e);
		}finally{
			em.close();
		}
		return tasker;
	}
	
	public static Tasker selectTaskerByEmail(String email){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		email = email.toLowerCase();
		String qString = "select t from Tasker t where t.email = :email";
		TypedQuery<Tasker> q = em.createQuery(qString, Tasker.class);
		q.setParameter("email", email);
		Tasker tasker = null;
		try {
			tasker = q.getSingleResult();
		}catch (NoResultException e){
			System.out.println(e);
		}finally{
			em.close();
		}
		return tasker;
	}

	public static Tasker selectATasker(String username, String password) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		username = username.toLowerCase();
		String qString = "select t from Tasker t where t.username = :username and t.password= :password";
		TypedQuery<Tasker> q = em.createQuery(qString, Tasker.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		Tasker tasker = null;
		try {
			tasker = q.getSingleResult();
		}catch (NoResultException e){
			System.out.println(e);
		}finally{
			em.close();
		}
		return tasker;
	}

}
