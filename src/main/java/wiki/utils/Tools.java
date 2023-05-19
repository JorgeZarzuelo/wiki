package wiki.utils;

import wiki.entities.Rol.Tipo;

public class Tools {

	
	public static Tipo getTipoFromString(String test) {

	    for (Tipo c : Tipo.values()) {
	        if (c.name().equals(test)) {
	            return Tipo.valueOf(test);
	        }
	    }

	    return null;
	}
}
