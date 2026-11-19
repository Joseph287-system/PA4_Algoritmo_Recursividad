/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pa4_algoritmo;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author fongj
 */
public class ZonaCampus {

    private String nombre;
    private boolean accesible;
    private boolean disponible;
    private List<ZonaCampus> subzonas;

    public ZonaCampus(String nombre, boolean accesible, boolean disponible) {
        this.nombre = nombre;
        this.accesible = accesible;
        this.disponible = disponible;
        this.subzonas = new ArrayList<>();
    }

    public void agregarSubzona(ZonaCampus subzona) {
        this.subzonas.add(subzona);
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAccesible() {
        return accesible;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public List<ZonaCampus> getSubzonas() {
        return subzonas;
    }
}