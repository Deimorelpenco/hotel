package hotel;

import java.util.List;

import javax.persistence.EntityManager;

import dao.ReservaDao;
import modelo.Reserva;
import utils.JPAUtil;

public class PruebaReserva {
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();
		ReservaDao reservaDao = new ReservaDao(em);

		List<Reserva> resultado = reservaDao.consultarPorId(1);
//		for(Reserva i: resultado) {
//			System.out.println(i.getId()+ " "+ i.getFechaEntrada()+ " "+ i.getFechaSalida()+ " "+ i.getFormaPago()+ " "+ i.getValor());
//		}
	
		for(Reserva i: resultado){
			System.out.println(i.getId()+ " "+ i.getFechaEntrada()+ " "+ i.getFechaSalida()+ " "+ i.getFormaPago()+ " "+ i.getValor());
			
		}
	}

}
