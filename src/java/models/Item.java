package models;

public class Item {
    
    private int id;
    private String nombre;
    private String descripcion;
    private String unidad_medida_id;
    private String marca_item_id;
    private String grupo_item_id;
    
    private UnidadMedida unidad_medida;
    private MarcaItem marca_item;
    private GrupoItem grupo_item;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad_medida_id() {
        return unidad_medida_id;
    }

    public void setUnidad_medida_id(String unidad_medida_id) {
        this.unidad_medida_id = unidad_medida_id;
    }

    public String getMarca_item_id() {
        return marca_item_id;
    }

    public void setMarca_item_id(String marca_item_id) {
        this.marca_item_id = marca_item_id;
    }

    public String getGrupo_item_id() {
        return grupo_item_id;
    }

    public void setGrupo_item_id(String grupo_item_id) {
        this.grupo_item_id = grupo_item_id;
    }

    public UnidadMedida getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(UnidadMedida unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public MarcaItem getMarca_item() {
        return marca_item;
    }

    public void setMarca_item(MarcaItem marca_item) {
        this.marca_item = marca_item;
    }

    public GrupoItem getGrupo_item() {
        return grupo_item;
    }

    public void setGrupo_item(GrupoItem grupo_item) {
        this.grupo_item = grupo_item;
    }
}