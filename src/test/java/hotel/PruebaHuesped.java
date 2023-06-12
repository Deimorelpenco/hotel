package hotel;

import java.util.List;

import javax.persistence.EntityManager;

import dao.HuestedDao;

import modelo.Huesped;
import utils.JPAUtil;

public class PruebaHuesped {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		HuestedDao huestedDao = new HuestedDao(em);
		List<Huesped> resultado = huestedDao.consultarPorApellido("Morel Penco");
		
		for(Huesped i: resultado) {
			System.out.println(i.getId()+" "+i.getNombre()+" "+i.getApellido()+" "+i.getNacionalidad()+""+i.getTelefono()+" "+i.getReserva().getId());
		}
	
	}
	
	
		
	

}
