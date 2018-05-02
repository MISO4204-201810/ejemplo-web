package ejemplo.web.presentacion;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import ejemplo.web.servicio.ServicioClima;

@Model
public class Index {

	@Inject
	ServicioClima servicioClima;
	
	public String getPrediccion() {
		return this.servicioClima.prediccion();
	}
	
}
