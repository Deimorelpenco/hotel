package dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import modelo.Huesped;
import modelo.Reserva;


public class HuestedDao {
	
	private EntityManager em;

	public HuestedDao(EntityManager em) {	
		this.em = em;
	}
	
	public void guardar(Huesped huesped) {
		this.em.persist(huesped);
	}
	
	public void actualizar(Huesped huesped) {
		this.em.merge(huesped);
	}
	
	public void remover(Huesped huesped) {
		huesped = this.em.merge(huesped);
		this.em.remove(huesped);
	}
	
	public List<Huesped> consultarTodo(){
		String jpql = "SELECT P FROM Huesped AS P";
		return em.createQuery(jpql,Huesped.class).getResultList();
	}
	
	public  List<Huesped> consultarPorApellido(String apellido){
		String jpql = "SELECT P FROM Huesped P WHERE P.datosPersonales.apellido=:apellido";
		return em.createQuery(jpql,Huesped.class).setParameter("apellido", apellido).getResultList();
	}
	
	public List<Huesped> consultarPorParametroH(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Reserva reserva){
		StringBuilder jpql = new StringBuilder("SELECT p FROM Huesped p WHERE 1=1 ");
		if(id != null && !id.equals(0)) {
			jpql.append("AND p.id =:id ");
		}
		if(nombre != null && !nombre.trim().isEmpty()) {
			jpql.append("AND p.datosPersonales.nombre =:nombre ");
		}
		if(apellido != null && !apellido.trim().isEmpty()) {
			jpql.append("AND p.datosPersonales.apellido =:apellido ");
		}
		if(fechaNacimiento != null) {
			jpql.append("AND p.datosPersonales.fechaNacimiento =:fechaNacimiento ");
		}
		if(nacionalidad != null && !nacionalidad.trim().isEmpty()) {
			jpql.append("AND p.datosPersonales.nacionalidad =:nacionalidad ");
		}
		if(telefono != null && !telefono.trim().isEmpty()) {
			jpql.append("AND p.datosPersonales.telefono =:telefono ");
		}if(reserva != null) {
			jpql.append("AND p.reserva =:reserva ");
		}
		TypedQuery<Huesped> query = em.createQuery(jpql.toString(), Huesped.class);
		if(id != null && !id.equals(0)) {
			query.setParameter("id",id);
		}
		if(nombre != null && !nombre.trim().isEmpty()) {
			query.setParameter("nombre",nombre);
		}
		if(apellido != null && !apellido.trim().isEmpty()) {
			query.setParameter("apellido",apellido);
		}
		if(fechaNacimiento != null) {
			query.setParameter("fechaNacimiento",fechaNacimiento);
		}
		if(nacionalidad != null && !nacionalidad.trim().isEmpty()) {
			query.setParameter("nacionalidad",nacionalidad);
		}
		if(telefono != null && !telefono.trim().isEmpty()) {
			query.setParameter("telefono",telefono);
		}
		if(reserva != null) {
			query.setParameter("reserva",reserva);
		}
		return query.getResultList();
	}

}
