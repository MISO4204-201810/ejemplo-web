package ejemplo.web.servicio;

import javax.ejb.Stateless;
import javax.ws.rs.GET;

@Stateless
public class ServicioClima {

	@GET
	public String prediccion() {
		return "soleado";
	}
	
}
