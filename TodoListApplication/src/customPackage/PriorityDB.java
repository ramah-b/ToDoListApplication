package customPackage;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Priority;
import customTools.DBUtil;

public class PriorityDB {
	
	public static Priority selectPriority(String priority_id){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select p from Priority p where p.priorityId = :priority_id";
		TypedQuery<Priority> q = em.createQuery(qString, Priority.class);
		q.setParameter("priority_id", priority_id);
		Priority priority = null;
		try {
			priority = q.getSingleResult();
		}catch (NoResultException e){
			System.out.println(e);
		}finally{
			em.close();
		}
		return priority;
	}

}
