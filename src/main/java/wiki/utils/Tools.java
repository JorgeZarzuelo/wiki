package wiki.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.http.Part;

import wiki.VO.ConcreteSupervisionVO;
import wiki.VO.RolVO;
import wiki.VO.SolicitudVO;
import wiki.VO.SupervisionVO;
import wiki.entities.Articulo;
import wiki.entities.Revision;
import wiki.entities.Rol;
import wiki.entities.Rol.Tipo;
import wiki.entities.User;
import wiki.entities.Wiki;
import wiki.services.HTMLBlockOperationData;
import wiki.services.HTMLComparatorService;

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
	
	
	
	public static String getFileName(Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename"))
	            return content.substring(content.indexOf("=") + 2, content.length() - 1);
	        }
	    return "edited.html";
	}
	
	public static ArrayList<SupervisionVO> populateSupervisionVO(User currentUser, ArrayList<Wiki> wikis, ArrayList<Revision> revisiones, ArrayList<User> users, ArrayList<Articulo> articulos){
		
		ArrayList<SupervisionVO> supervisiones = new ArrayList<SupervisionVO>();
		
		ArrayList<Integer> articulosSupervisados = new ArrayList<Integer>();
		
		System.out.println("TAMAÑO roles: " + currentUser.getRoles().size() + " username " + currentUser.getUsername());
		//recorremos los roles para sacar los ids de articulos y si son wikis, los de todos los articulos de esa wiki.
		currentUser.getRoles().forEach( rol -> {
			//solo supervisores aprobados
			if (rol.getTipo().equals(Tipo.SUPERVISOR)) {
				
				if (rol.getArticulo_id() != 0) {
					articulosSupervisados.add(rol.getArticulo_id());
				}
				if (rol.getWiki_id() != 0) {
					wikis.forEach( wiki ->{
						if (rol.getWiki_id() == wiki.getId()) {
							wiki.getArticulos().forEach(articulo -> {
								articulosSupervisados.add(articulo.getId());
							});
						}
					});
				}
			}
		});
		
		System.out.println("TAMAÑO articulos supervisados: " + articulosSupervisados.size());
		
		// recorremos las revisiones quedandonos solo con las que son para este usuario gracias 
		// a que tenemos ya la lista de articulos supervisados.
		revisiones.forEach( revision -> {
			if (articulosSupervisados.contains(revision.getArticulo_id())){
				SupervisionVO supervision = new SupervisionVO();
				
				supervision.setArticulo_id(revision.getArticulo_id());
				supervision.setId(revision.getId());
				//poblamos la info del usuario
				users.forEach(user -> {
					user.getRevisiones().forEach(userRev ->{
						if (userRev.getId() == revision.getId()) {
							supervision.setUsername(user.getUsername());
							supervision.setUser_id(user.getId());
						}
					});
				});
				//poblamos la info del articulo
				articulos.forEach(articulo ->{
					if (revision.getArticulo_id() == articulo.getId()) {
						   supervision.setTitulo_articulo(articulo.getTitulo());
					}
				});
				
				supervisiones.add(supervision);
			}
		});
		
		return supervisiones;
	}


	public static ConcreteSupervisionVO populateConcreteSupervisionVO(
			User currentUser, 
			Revision currentRevision, 
			Articulo currentArticulo, 
			HTMLComparatorService comparator			
			) {
		
		ConcreteSupervisionVO concrete = new ConcreteSupervisionVO();
		
		concrete.setId(currentRevision.getId());
		concrete.setUsername(currentUser.getUsername());
		
		concrete.setOperaciones(comparator.getListaOperaciones());
		concrete.setOriginal(currentArticulo.getContenido());
		concrete.setPropuesta(currentRevision.getPropuesta());		
		
		concrete.setArticulo_id(currentRevision.getId());
		concrete.setTitulo_articulo(currentArticulo.getTitulo());
		
		
		return concrete;
	}

}
