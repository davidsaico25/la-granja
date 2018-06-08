package models;

public class Abastecimiento_Has_Item {
    
    private int id;
    private int abastecimiento_id;
    private int item_id;
    private double cantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAbastecimiento_id() {
        return abastecimiento_id;
    }

    public void setAbastecimiento_id(int abastecimiento_id) {
        this.abastecimiento_id = abastecimiento_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}