package modelo;


import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date fechaEntrada;
	
	private Date fechaSalida;
	
	private String valor;
	
	private String formaPago;	
	
	
	@OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
	private List<Huesped> huespedes;
	
	public Reserva() {
		
	}
	
	public Reserva(List<Huesped> huesped) {		
		this.huespedes = huesped;
	}

	public Reserva(Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;		
	}
	
	public Reserva(Integer id, Date fechaEntrada, Date fechaSalida, String valor, String formaPago) {	
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;		
	}
	

	public Reserva(Integer filaReserva) {
		this.id = filaReserva;
	}

	public Integer getId() {
		return id;
	}
	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public List<Huesped> getHuesped() {
		return huespedes;
	}

	public void setHuesped(List<Huesped> huesped) {
		this.huespedes = huesped;
	}
		

}
