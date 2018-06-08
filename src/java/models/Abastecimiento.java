package models;

public class Abastecimiento {
    
    private int id;
    private String observacion;
    private int estado_abastecimiento_id;
    private int local_id_origen;
    private int local_id_destino;

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
}