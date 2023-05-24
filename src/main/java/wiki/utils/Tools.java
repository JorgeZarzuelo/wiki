package wiki.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import wiki.VO.RolVO;
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
	
	@SuppressWarnings({ "null", "unchecked", "unused" })
	public static ArrayList<SolicitudVO> populateSolicitudVO(User _user,ArrayList<User> users, ArrayList<Wiki> wikis, ArrayList<Articulo> articulos) {
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
		
				
		ArrayList<SolicitudVO> soloSupervisor = null;
		// sacamos del array las solicitudes que no sean de supervision
		soloSupervisor =  (ArrayList<SolicitudVO>) convertido.stream().filter( solicitud -> solicitud.getTipoSolicitud() == Tipo.SUPERVISOR).collect(Collectors.toList());
		
				
		
		if(soloSupervisor == null) {
			return null;
		}
	
		// array con las wikis del coordinador
		ArrayList<Integer> wikisCoordinador = new ArrayList<Integer>();
		_user.getRoles().forEach( rol -> {
			if(rol.getWiki_id() != 0) {
			   wikisCoordinador.add(rol.getWiki_id());
			}
		});		
			
		// sacamos del array las solicitudes que no se correspondan con wikis del coordinador
		ArrayList<SolicitudVO> wikisSupervisor = null;
		wikisSupervisor = (ArrayList<SolicitudVO>) soloSupervisor.stream().filter( solicitud -> wikisCoordinador.contains(solicitud.getWiki_id()) ).collect(Collectors.toList());
		
		
		
		// array con los articulos de las wikis del coordinador
		ArrayList<Integer> articulosCoordinador = new ArrayList<Integer>();
		wikis.forEach(wiki -> {
		    if(wikisCoordinador.contains(wiki.getId())) {
		    	wiki.getArticulos().forEach( articulo -> {
		    		articulosCoordinador.add(articulo.getId());
		    	});
		    }
		});	
		
		ArrayList<SolicitudVO> articulosSupervisor = null;
		// sacamos del array las solicitudes que no se correspondan con articulos de las wikis del coordinador
		articulosSupervisor = (ArrayList<SolicitudVO>) soloSupervisor.stream().filter( solicitud -> articulosCoordinador.contains(solicitud.getArticulo_id()) ).collect(Collectors.toList());
		
		
		ArrayList<SolicitudVO> allCoordinador = new ArrayList<SolicitudVO>();
		
		if (articulosSupervisor != null) {
			allCoordinador.addAll(articulosSupervisor);
		}
		if (wikisSupervisor != null) {
			allCoordinador.addAll(wikisSupervisor);
		}
		
		
		return allCoordinador;
	}
	
	public static ArrayList<RolVO> populateUserRolVO(User user, ArrayList<Wiki> wikis, ArrayList<Articulo> articulos){
		ArrayList<RolVO> userRoles = new ArrayList<RolVO>();		
	    user.getRoles().forEach(rol -> {
	    	RolVO currentRol = new RolVO();
	    		currentRol.setUsername(user.getUsername());
	    		currentRol.setPendiente(rol.isPendiente());
	    		currentRol.setRol_id(rol.getId());
	    		currentRol.setTipoSolicitud(rol.getTipo());
	    		if (rol.getArticulo_id() != 0) {
	    			currentRol.setArticulo_id(rol.getArticulo_id());
	    			articulos.forEach(articulo -> {
						if(articulo.getId() == rol.getArticulo_id()) {
							currentRol.setTitulo_articulo(articulo.getTitulo());
						}
					});
	    		}
	    		if (rol.getWiki_id() != 0) {
	    			currentRol.setWiki_id(rol.getWiki_id());
	    			wikis.forEach(wiki -> {
						if(wiki.getId() == rol.getWiki_id()) {
							currentRol.setTopic_wiki(wiki.getTopic());
						}
					});
	    		}
	    		userRoles.add(currentRol);
	    });
		
		
		return userRoles;
	}
	
	

}
