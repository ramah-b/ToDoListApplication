package customPackage;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import customTools.DBUtil;
import model.Item;

public class ItemsDB {
	public static void insert(Item item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(item);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Item item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(item);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Item item) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(item));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static List<Item> getItems(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select i from Item i";
		TypedQuery<Item> q = em.createQuery(qString, Item.class);
		List<Item> item = null;
		try {
			item = q.getResultList();
		}catch (NoResultException e){
			System.out.println(e);
		}finally{
			em.close();
		}
		return item;
	}

	public static Item getItem(String item_id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select i from Item i where i.itemId = :item_id";
		TypedQuery<Item> q = em.createQuery(qString, Item.class);
		q.setParameter("item_id", item_id);
		Item item = null;
		try {
			item = q.getSingleResult();
		}catch (NoResultException e){
			System.out.println(e);
		}finally{
			em.close();
		}
		return item;
	}

}
