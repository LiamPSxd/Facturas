package com.uv.factura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class FacturaApplication{
	@Autowired
	private IFactura ifactura;

	public static void main(String[] args){
		SpringApplication.run(FacturaApplication.class, args);
	}

	@RequestMapping(value = "/facturas", method = RequestMethod.GET)
	public String getFacturas(){
		Iterable<Factura> facturas = ifactura.findAll();

		if(facturas != null){
			String result = "{\"status\": \"Success\", \"data\": [";

			for(Factura factura : facturas){
				result = result + "{" + factura.toString() + "}, ";
			}

			result = result + "]}";
			return result;
		}else return "{\"status\": \"Failed\"}";
	}

	@RequestMapping(value = "/facturas/{id}", method = RequestMethod.GET)
	public String getFactura(@PathVariable Integer id){
		Factura factura = ifactura.findById(id).get();

		if(factura != null){
			return "{\"status\": \"Success\", \"data\": {" + factura.toString() + "}}";
		}else return "{\"status\": \"Failed\"}";
	}

	@RequestMapping(value = "/facturas", method = RequestMethod.POST)
	public String postFactura(@RequestBody Factura factura){
		if(factura != null){
			ifactura.save(factura);
			return "{\"status\": \"Success\"}";
		}return "{\"status\": \"Failed\"}";
	}

	@RequestMapping(value = "/facturas/{id}", method = RequestMethod.PUT)
	public String putFactura(@PathVariable Integer id, @RequestBody Factura factura){
		if(factura != null){
			Factura newFactura = ifactura.findById(id).get();

			if(newFactura != null){
				newFactura.setId(factura.getId());
				newFactura.setIdCamion(factura.getIdCamion());
				newFactura.setCarga(factura.getCarga());
				newFactura.setFecha(factura.getFecha());
				newFactura.setHora(factura.getHora());
				newFactura.setNombreChofer(factura.getNombreChofer());
				newFactura.setNombreReceptor(factura.getNombreReceptor());
				ifactura.save(newFactura);

				return "{\"status\": \"Success\", \"data\": {" + factura.toString() + "}}";
			}else return "{\"status\": \"Failed\"}";
		}else return "{\"status\": \"Failed\"}";
	}

	@RequestMapping(value = "/facturas/{id}", method = RequestMethod.DELETE)
	public String deleteFactura(@PathVariable Integer id){
		Factura factura = ifactura.findById(id).get();

		if(factura != null){
			ifactura.delete(factura);

			return "{\"status\": \"Success\"}";
		}else return "{\"status\": \"Failed\"}";
	}
}