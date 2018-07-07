package models;

import java.util.List;

public class Abastecimiento {
    
    private int id;
    private String observacion;
    private String fecha_recepcion;
    private String fecha_creacion;
    private int estado_abastecimiento_id;
    private int local_id_origen;
    private int local_id_destino;
    private Local local_origen;
    private Local local_destino;
    private EstadoAbastecimiento estado_abastecimiento;
    private List<Abastecimiento_Has_Item> listAbastecimientoHasItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(String fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getEstado_abastecimiento_id() {
        return estado_abastecimiento_id;
    }

    public void setEstado_abastecimiento_id(int estado_abastecimiento_id) {
        this.estado_abastecimiento_id = estado_abastecimiento_id;
    }

    public int getLocal_id_origen() {
        return local_id_origen;
    }

    public void setLocal_id_origen(int local_id_origen) {
        this.local_id_origen = local_id_origen;
    }

    public int getLocal_id_destino() {
        return local_id_destino;
    }

    public void setLocal_id_destino(int local_id_destino) {
        this.local_id_destino = local_id_destino;
    }

    public Local getLocal_origen() {
        return local_origen;
    }

    public void setLocal_origen(Local local_origen) {
        this.local_origen = local_origen;
    }

    public Local getLocal_destino() {
        return local_destino;
    }

    public void setLocal_destino(Local local_destino) {
        this.local_destino = local_destino;
    }

    public EstadoAbastecimiento getEstado_abastecimiento() {
        return estado_abastecimiento;
    }

    public void setEstado_abastecimiento(EstadoAbastecimiento estado_abastecimiento) {
        this.estado_abastecimiento = estado_abastecimiento;
    }

    public List<Abastecimiento_Has_Item> getListAbastecimientoHasItem() {
        return listAbastecimientoHasItem;
    }

    public void setListAbastecimientoHasItem(List<Abastecimiento_Has_Item> listAbastecimientoHasItem) {
        this.listAbastecimientoHasItem = listAbastecimientoHasItem;
    }
}