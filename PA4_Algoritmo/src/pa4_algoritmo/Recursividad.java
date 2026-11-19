
package pa4_algoritmo;


public class Recursividad {
    public int contarEspaciosAccesibles(ZonaCampus zona){
        if(zona == null){
            return 0;
        }
        if(zona.getSubzonas().isEmpty()){
            if(zona.isAccesible()&& zona.isDisponible()){
                return 1;
            } else{
                return 0;
            }
        }
        int total = 0;
        for(ZonaCampus sub : zona.getSubzonas()){
            total = total + contarEspaciosAccesibles(sub);
        }
        return total;
    }
}
