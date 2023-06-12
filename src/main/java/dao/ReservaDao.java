package dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import modelo.Reserva;

public class ReservaDao {
	
	private EntityManager em;

	public ReservaDao(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Reserva reserva) {
		this.em.persist(reserva);
	}
	
	public void actualizar(Reserva reserva) {
		this.em.merge(reserva);
	}
	
	public void remover(Reserva reserva) {
		reserva = this.em.merge(reserva);
		this.em.remove(reserva);
	}
		
	public List<Reserva> consultarTodo(){
		String jpql = "SELECT P FROM Reserva AS P";
		return em.createQuery(jpql,Reserva.class).getResultList();
	}
	
	public  List<Reserva> consultarPorId(Integer id){
		String jpql = "SELECT P FROM Reserva AS P WHERE P.id=:id";
		return em.createQuery(jpql,Reserva.class).setParameter("id", id).getResultList();
	}

	public List<Reserva> consultarPorParametroR(Integer id, Date fechaEntrada, Date fechaSalida, String valor, String formaPago){
		StringBuilder jpql = new StringBuilder("SELECT p FROM Reserva p WHERE 1=1 ");
			if(id != null && !id.equals(0)) {
				jpql.append("AND p.id =:id ");
			}
			if(fechaEntrada != null) {
				jpql.append("AND p.fechaEntrada =:fechaEntrada ");
			}
			if(fechaSalida != null) {
				jpql.append("AND p.fechaSalida =:fechaSalida ");
			}
			if(valor != null && !valor.trim().isEmpty()) {
				jpql.append("AND p.valor =:valor ");
			}
			if(formaPago != null && !formaPago.trim().isEmpty()) {
				jpql.append("AND p.formaPago =:formaPago");
			}
			TypedQuery<Reserva> query = em.createQuery(jpql.toString(), Reserva.class);
			if(id != null && !id.equals(0)) {
				query.setParameter("id",id);
			}
			if(fechaEntrada != null) {
				query.setParameter("fechaEntrada",fechaEntrada);
			}
			if(fechaSalida != null) {
				query.setParameter("fechaSalida",fechaSalida);
			}
			if(valor != null && !valor.trim().isEmpty()) {
				query.setParameter("valor",valor);
			}
			if(formaPago != null && !formaPago.trim().isEmpty()) {
				query.setParameter("formaPago",formaPago);
			}
			return query.getResultList();
	}
}
