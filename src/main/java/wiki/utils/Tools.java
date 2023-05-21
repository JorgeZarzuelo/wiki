package wiki.utils;

import java.util.ArrayList;

import wiki.VO.SolicitudVO;
import wiki.entities.Articulo;
import wiki.entities.Rol.Tipo;
import wiki.entities.User;
import wiki.entities.Wiki;

public class Tools {

	
	public static Tipo getTipoFromString(String test) {

	    for (Tipo c : Tipo.values()) {
	        if (c.name().equals(test)) {
	            return Tipo.valueOf(test);
	        }
	    }

	    return null;
	}
	
	
	public static ArrayList<SolicitudVO> populateSolicitudVO(ArrayList<User> users, ArrayList<Wiki> wikis, ArrayList<Articulo> articulos) {
		ArrayList<SolicitudVO> convertido = new ArrayList<SolicitudVO>();
		
		// buscamos los usuarios con roles pendientes de activar y cubrimos un objeto SolicitudVO con los valores del rol.
		users.forEach(user -> {			
			user.getRoles().forEach(rol -> {
				if (rol.isPendiente() == true) {
					SolicitudVO solicitud = new SolicitudVO();
					solicitud.setUser_id(user.getId());
					solicitud.setUsername(user.getUsername());
					solicitud.setRol_id(rol.getId());
					solicitud.setTipoSolicitud(rol.getTipo());
					if (rol.getWiki_id() != 0) {
						solicitud.setWiki_id(rol.getWiki_id());
						wikis.forEach(wiki -> {
							if(wiki.getId() == rol.getWiki_id()) {
								solicitud.setTopic_wiki(wiki.getTopic());
							}
						});
					}
					if (rol.getArticulo_id() != 0) {
						solicitud.setArticulo_id(rol.getArticulo_id());
						articulos.forEach(articulo -> {
							if(articulo.getId() == rol.getArticulo_id()) {
								solicitud.setTitulo_articulo(articulo.getTitulo());
							}
						});
					}
					convertido.add(solicitud);					
				}
				
			});
		});
		
		
		return convertido;
	}
	
	

}
