package customPackage;


	import javax.persistence.EntityManager;
	import javax.persistence.NoResultException;
	import javax.persistence.TypedQuery;

	import model.Status;
	import customTools.DBUtil;


	public class StatusDB {
		public static Status selectStatus(String status_id){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select s from Status s where s.statusId = :status_id";
			TypedQuery<Status> q = em.createQuery(qString, Status.class);
			q.setParameter("status_id", status_id);
			Status status = null;
			try {
				status = q.getSingleResult();
			}catch (NoResultException e){
				System.out.println(e);
			}finally{
				em.close();
			}
			return status;
		}

	}



