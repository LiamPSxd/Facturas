package com.uv.factura;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
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
		JSONObject respuesta = new JSONObject();
		List<JSONObject> data = new ArrayList<>();

		try{
			Iterable<Factura> facturas = ifactura.findAll();

			if(facturas != null){
				respuesta.put("status", "Success");
				for(Factura factura : facturas)
					data.add(factura.toJson());
				respuesta.put("data", data);
			}
		}catch(Exception e){
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}
	
	@RequestMapping(value = "/facturas/{id}", method = RequestMethod.GET)
	public String getFactura(@PathVariable Integer id){
		JSONObject respuesta = new JSONObject();
		try{
			Factura factura = ifactura.findById(id).get();
			
			if(factura != null){
				respuesta.put("status", "Success");
				respuesta.put("data", factura.toJson());
			}
		}catch(Exception e){
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}
	
	@RequestMapping(value = "/facturas", method = RequestMethod.POST)
	public String postFactura(@RequestBody Factura factura){
		JSONObject respuesta = new JSONObject();
		try{
			if(factura != null){
				ifactura.save(factura);
				respuesta.put("status", "Success");
			}
		}catch(Exception e){
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}

	@RequestMapping(value = "/facturas/{id}", method = RequestMethod.PUT)
	public String putFactura(@PathVariable Integer id, @RequestBody Factura factura){
		JSONObject respuesta = new JSONObject();
		//try{
			if(factura != null){
				Factura newFactura = ifactura.findById(id).get();
	
				if(newFactura != null){
					newFactura.setIdCamion(factura.getIdCamion());
					newFactura.setCarga(factura.getCarga());
					newFactura.setFecha(factura.getFecha());
					newFactura.setHora(factura.getHora());
					newFactura.setNombreChofer(factura.getNombreChofer());
					newFactura.setNombreReceptor(factura.getNombreReceptor());
					ifactura.save(newFactura);
					respuesta.put("status", "Success");
					respuesta.put("data", factura.toJson());
				}
			}
		//}catch(Exception e){
		//	return respuesta.toString();
		//}
		return respuesta.toString();
	}

	@RequestMapping(value = "/facturas/{id}", method = RequestMethod.DELETE)
	public String deleteFactura(@PathVariable Integer id){
		JSONObject respuesta = new JSONObject();
		try{
			Factura factura = ifactura.findById(id).get();

			if(factura != null){
				ifactura.delete(factura);
				respuesta.put("status", "Success");
			}
		}catch(Exception e){
			respuesta.put("status", "Failed");
		}
		return respuesta.toString();
	}
}