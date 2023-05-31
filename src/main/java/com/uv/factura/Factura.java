package com.uv.factura;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.json.JSONObject;

//Clase Factura que sirve de apoyo para guardar los datos de las entidades que se registrarán.
@Entity
public class Factura{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idCamion;
    private String carga;
    private String fecha;
    private String hora;
    private String nombreChofer;
    private String nombreReceptor;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getIdCamion(){
        return idCamion;
    }

    public void setIdCamion(Integer idCamion){
        this.idCamion = idCamion;
    }

    public String getCarga(){
        return carga;
    }

    public void setCarga(String carga){
        this.carga = carga;
    }

    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public String getHora(){
        return hora;
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public String getNombreChofer(){
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer){
        this.nombreChofer = nombreChofer;
    }

    public String getNombreReceptor(){
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor){
        this.nombreReceptor = nombreReceptor;
    }

    public String toString(){
        return "\"id\": \"" + this.getId() + "\", \"idCamion\": \"" + this.getIdCamion() + "\", \"carga\": \"" + this.getCarga() + "\", \"fecha\": \"" + this.getFecha() + "\", \"hora\": \"" + this.getHora() + "\", \"nombreChofer\": \"" + this.getNombreChofer() + "\", \"nombreReceptor\": \"" + this.getNombreReceptor() + "\"";
    }

    public JSONObject toJson(){
        JSONObject stringJson = new JSONObject();
        stringJson.put("id", this.getId());
         stringJson.put("idCamion",this.getIdCamion());
         stringJson.put("carga",this.getCarga());
         stringJson.put("fecha", this.getFecha());
         stringJson.put("hora", this.getHora());
         stringJson.put("nombreChofer", this.getNombreChofer());
         stringJson.put("nombreReceptor",this.getNombreReceptor());
         return stringJson;
    }
}