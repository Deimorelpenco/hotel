package modelo;

import java.sql.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="huespedes")
public class Huesped {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Embedded
	private DatosPersonales datosPersonales;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "reserva_id", referencedColumnName = "id")
	private Reserva reserva;
	
	
	public Huesped() {	
	
	}
	
	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Reserva reserva) {		
		
		this.datosPersonales = new DatosPersonales(nombre, apellido, fechaNacimiento, nacionalidad, telefono);	
		this.reserva = reserva;
	}	

	public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono, Reserva reserva) {		
		this.id = id;
		this.datosPersonales = new DatosPersonales(nombre, apellido, fechaNacimiento, nacionalidad, telefono);
		this.reserva = reserva;
	}

	public Huesped(Integer id) {
		this.id = id;
	}	

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return datosPersonales.getNombre();
	}

	public void setNombre(String nombre) {
		this.datosPersonales.setNombre(nombre);
	}

	public String getApellido() {
		return datosPersonales.getApellido();
	}

	public void setApellido(String apellido) {
		this.datosPersonales.setApellido(apellido);
	}

	public Date getFechaNacimiento() {
		return datosPersonales.getFechaNacimiento();
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.datosPersonales.setFechaNacimiento(fechaNacimiento);
	}

	public String getNacionalidad() {
		return datosPersonales.getNacionalidad();
	}

	public void setNacionalidad(String nacionalidad) {
		this.datosPersonales.setNacionalidad(nacionalidad);
	}

	public String getTelefono() {
		return datosPersonales.getTelefono();
	}

	public void setTelefono(String telefono) {
		this.datosPersonales.setTelefono(telefono);
	}

	

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	
}
