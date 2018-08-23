package project.dblearning.objects;

public class Courses {
    private String nombre;
    private String descripcion;
    private String icono;
    private String key;

    public Courses() {
    }

    public Courses(String nombre, String descripcion, String icono, String key) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.icono = icono;
        this.key = key;
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

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
