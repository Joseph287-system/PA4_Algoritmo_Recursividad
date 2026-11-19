/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pa4_algoritmo;

import java.util.List;

/**
 *
 * @author fongj
 */
public class PA4_Algoritmo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ZonaCampus aula1 = new ZonaCampus("Lab Redes 1", true, true);
        ZonaCampus aula2 = new ZonaCampus("Aula 201", false, true);
        ZonaCampus aula3 = new ZonaCampus("Lab IA", true, false);

        ZonaCampus pabellonA = new ZonaCampus("Pabellón A", true, true);
        pabellonA.agregarSubzona(aula1);
        pabellonA.agregarSubzona(aula2);
        pabellonA.agregarSubzona(aula3);

        Recursividad rec = new Recursividad();
        int total = rec.contarEspaciosAccesibles(pabellonA);

        System.out.println("Espacios accesibles y disponibles en Pabellón A: " + total);
    }
}