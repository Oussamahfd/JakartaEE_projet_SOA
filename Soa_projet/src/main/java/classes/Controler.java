package classes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.sql.ResultSet;     
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.mysql.cj.protocol.Resultset;

import javax.json.bind.*;
import DAO.etudao;

@Path("/services")
public class Controler {

	etudao et = new etudao();
	
		@GET
		@Path("/test")
		@Produces(MediaType.TEXT_PLAIN)
		public String test() {
			return "test";}
		
		@GET
		@Path("/login")
		@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response login(@QueryParam("email") String email,@QueryParam("password") String pswd) {
			ResultSet rs = et.findemail(email);
			
			
			
			if (rs != null) {
			    List<Map<String, Object>> resultsList = new ArrayList<>();
			    try {
			        while(rs.next()) {
			            Map<String, Object> result = new HashMap<>();
			            result.put("mdp", rs.getString("mdp"));
			            result.put("mail", rs.getString("mail"));
			            result.put("role", rs.getString("role"));
			            result.put("id", rs.getInt("id"));
			            resultsList.add(result);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			    
			    // Convertir la liste en JSON
			    Jsonb jsonb = JsonbBuilder.create();
			    String json = jsonb.toJson(resultsList);
			    // Retourner la chaîne JSON en réponse
			    return Response.status(Response.Status.OK).entity(json).build();
			} else {
			    // Si l'utilisateur n'existe pas, retourner une réponse 404 Not Found
			    return Response.status(Response.Status.NOT_FOUND).entity("Failed to add Classe !").build();
			}
			}
		
		@Context
	    private HttpServletRequest httpRequest;
		
		
		@GET
		@Path("/editprof")
//		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces(MediaType.TEXT_PLAIN)
		public String editprof(
			    @QueryParam("Nom") String nom,
			    @QueryParam("Prenom") String prenom,
			    @QueryParam("Mail") String mail,
			    @QueryParam("Phone") String phone,
			    @QueryParam("Password") String password,
			    @QueryParam("Salaire") Float salaire,
			    @QueryParam("Matiere") String matiere,
			    @QueryParam("Niveau") String niveau
			) {
			    // Votre logique de traitement ici
			Etud prof = new Etud(nom, prenom, mail, phone, password, "Prof");
		    
		    httpRequest.getSession().setAttribute("nom", nom);
		    httpRequest.getSession().setAttribute("prenom", prenom);
		    httpRequest.getSession().setAttribute("phone", mail);
		    httpRequest.getSession().setAttribute("password", password);
		    httpRequest.getSession().setAttribute("salaire", salaire);
		    httpRequest.getSession().setAttribute("matiere", matiere);
		    httpRequest.getSession().setAttribute("niveau", niveau);
		    httpRequest.getSession().setAttribute("prof", prof);
		    // Mise à jour de l'utilisateur dans la base de données
		    
		    // Supposons que "et" est votre objet d'accès aux données
			int userId =0;
			try (ResultSet resultSet = et.finduserid(mail)) {
		        if (resultSet.next()) {
		            // Assuming the result you want is in the first column of the ResultSet
		        	userId = resultSet.getInt(1);
		        	
		        }
		    } catch (SQLException e) {
		        // Handle any potential SQL exceptions here
		        e.printStackTrace();
		    }
			
			return "salm "+userId;
//			httpRequest.getSession().setAttribute("userId", userId);
//			prof = (Etud) httpRequest.getSession().getAttribute("prof");
//            int nb = et.UpdUserp(prof, userId);
//            
//            nom = (String) httpRequest.getSession().getAttribute("nom");
//            prenom = (String) httpRequest.getSession().getAttribute("prenom");
//            mail = (String) httpRequest.getSession().getAttribute("phone");
//            password = (String) httpRequest.getSession().getAttribute("password");
//            salaire = (Float) httpRequest.getSession().getAttribute("salaire");
//            matiere = (String) httpRequest.getSession().getAttribute("matiere");
//            niveau = (String) httpRequest.getSession().getAttribute("niveau");
//            Prof pr=new Prof(nom,prenom,mail,phone,password,"Prof",matiere,salaire,niveau,userId);
////		    // Mise à jour de l'objet Prof dans la session
////            
//            et.updprofus(pr);
////            
//            matiere = (String) httpRequest.getSession().getAttribute("matiere");
//            userId = (int) httpRequest.getSession().getAttribute("userId");
//		    // Mise à jour spécifique selon la matière enseignée
//		    switch (matiere) {
//		        case "MATH":
//		            nb = et.updprofmat(userId, 1);
//		            break;
//		        case "PHYSIQUE":
//		        	nb = et.updprofmat(userId, 2);
//		            break;
//		        case "SVT":
//		        	nb = et.updprofmat(userId, 3);
//		            break;
//		        case "COMPTABILITE":
//		        	nb = et.updprofmat(userId, 4);
//		            break;
//		        case "PHILOSOPHIE":
//		        	nb = et.updprofmat(userId, 5);
//		            break;
//		        case "ANGLAIS":
//		        	nb = et.updprofmat(userId, 6);
//		            break;
//		        default:
//		            // Traitement pour les autres matières
//		            break;
//		    }
		  
//		    return Response.status(Response.Status.OK).entity("Etudiant edit successfully").build();
			}
		
		
		@DELETE
		@Path("/deleteprof/{id}")
		public Response removeEtudiant (@PathParam("id") int id_prof) {
			
			httpRequest.getSession().setAttribute("ideditp", id_prof);
			et.delusr(id_prof);
		
			Integer user_id = (Integer) httpRequest.getSession().getAttribute("ideditp");
	        int userId = user_id.intValue();
	        et.delprf(userId);
	        
	        user_id = (Integer) httpRequest.getSession().getAttribute("ideditp");
	        userId = user_id.intValue();
	        et.deletdabs(userId);
	        
	        user_id = (Integer) httpRequest.getSession().getAttribute("ideditp");
	        userId = user_id.intValue();
	        et.deletdpm(userId);
	        
	        user_id = (Integer) httpRequest.getSession().getAttribute("ideditp");
	        userId = user_id.intValue();
	        int nb = et.deletdmatp(userId);
	        
	        if (nb == 0) {
		        return Response.status(Response.Status.NOT_FOUND)
		                .entity("No Prof found with the specified ID.")
		                .build();
		    } else if (nb < 0) {
		        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
		                .entity("Failed to remove Prof. Please try again later.")
		                .build();
		    } else {
		        return Response.status(Response.Status.OK)
		                .entity("Prof removed successfully.")
		                .build();
		    }
		
		}
		
		
		
		
		
//		 @GET
//		    @Path("/login")
//		    @Produces(MediaType.APPLICATION_JSON)
//		    public String login(@QueryParam("email") String email, @QueryParam("password") String pswd) {
//		        ResultSet rs = et.findemail(email);
//
//		        if (rs != null) {
//		            List<Map<String, Object>> resultsList = new ArrayList<>();
//		            try {
//		                while (rs.next()) {
//		                    Map<String, Object> result = new HashMap<>();
//		                    result.put("mdp", rs.getString("mdp"));
//		                    result.put("mail", rs.getString("mail"));
//		                    result.put("role", rs.getString("role"));
//		                    result.put("id", rs.getInt("id"));
//		                    resultsList.add(result);
//		                }
//		            } catch (SQLException e) {
//		                e.printStackTrace();
//		            }
//
//		            // Convertir la liste en JSON
//		            Jsonb jsonb = JsonbBuilder.create();
//		            String json = jsonb.toJson(resultsList);
//		            // Retourner la chaîne JSON en réponse
//		            return json;
//		        } else {
//		            // Si l'utilisateur n'existe pas, retourner une chaîne vide
//		            return "";
//		        }
//		    }
		
		
		@GET
		@Path("/editetudiant")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response editetudiant(
			    @QueryParam("Nom") String nom,
			    @QueryParam("Prenom") String prenom,
			    @QueryParam("Mail") String mail,
			    @QueryParam("Phone") String phone,
			    @QueryParam("Password") String password
			) {
			
			
			    // Votre logique de traitement ici
			Etud prof = new Etud(nom, prenom, mail, phone, password, "Prof");
		    
		    httpRequest.getSession().setAttribute("nom", nom);
		    httpRequest.getSession().setAttribute("prenom", prenom);
		    httpRequest.getSession().setAttribute("phone", mail);
		    httpRequest.getSession().setAttribute("password", password);
		    httpRequest.getSession().setAttribute("phone", phone);
		    
		    int userId =0;
			try (ResultSet resultSet = et.finduserid(mail)) {
		        if (resultSet.next()) {
		            // Assuming the result you want is in the first column of the ResultSet
		        	userId = resultSet.getInt(1);
		        	
		        }
		    } catch (SQLException e) {
		        // Handle any potential SQL exceptions here
		        e.printStackTrace();
		    }
			
			httpRequest.getSession().setAttribute("userId", userId);
		    
//		    Integer user_id2 = (Integer) httpRequest.getSession().getAttribute("ideditp");
//	        
//	        int userId2 = user_id2.intValue();
	        et.Updet(userId,nom,prenom,mail,phone,password);
		   
	        Integer user_ids = (Integer) httpRequest.getSession().getAttribute("userId");
	        int userIds = user_ids.intValue();//
	        
	        
	        nom = (String) httpRequest.getSession().getAttribute("nom");
            prenom = (String) httpRequest.getSession().getAttribute("prenom");
            mail = (String) httpRequest.getSession().getAttribute("phone");
            password = (String) httpRequest.getSession().getAttribute("password");
            String emails = (String) httpRequest.getSession().getAttribute("mail");
            
            int nb = et.upet(userIds,nom,prenom,emails,phone,password);
		    if (nb == 1) {
		    return Response.status(Response.Status.OK).entity("Etudiant edit successfully").build();}
		    
		    return Response.status(Response.Status.OK).entity("Etudiant edit successfully").build();
		    
		}
		
		@GET
		@Path("/test1")
		@Produces(MediaType.TEXT_PLAIN)
		public String test1() throws SQLException {
			  int result = 0;
			try (ResultSet resultSet = et.numet()) {
		        if (resultSet.next()) {
		            // Assuming the result you want is in the first column of the ResultSet
		            result = resultSet.getInt(1);
		        }
		    } catch (SQLException e) {
		        // Handle any potential SQL exceptions here
		        e.printStackTrace();
		    }
			return "result"+result;}
		
		
		@DELETE
		@Path("/deleteetudiant/{id}")
		public Response deleteetudiant (@PathParam("id") int id_etud) {
			
			httpRequest.getSession().setAttribute("ideditp", id_etud);
			et.delusr(id_etud);
		
			Integer user_id = (Integer) httpRequest.getSession().getAttribute("ideditp");
	        int userId = user_id.intValue();
	  		et.deletd(userId);
	        
	  		Integer user_id1 = (Integer) httpRequest.getSession().getAttribute("ideditp");
	        int userId1 = user_id1.intValue();
	  		et.deletdabs(userId1);
	  		
	  		
	  		Integer user_id2 = (Integer) httpRequest.getSession().getAttribute("ideditp");
	          int userId2 = user_id2.intValue();
	  		et.deletdpm(userId2);
	  		
	  		Integer user_id3 = (Integer) httpRequest.getSession().getAttribute("ideditp");
	          int userId3 = user_id3.intValue();
	  		et.deletdmat(userId3);
	  		
	  		Integer user_id4 = (Integer) httpRequest.getSession().getAttribute("ideditp");
	  		int userId4 = user_id4.intValue();
	  		int nb = et.deletdgrp(userId4);
	        
	        
		        return Response.status(Response.Status.OK)
		                .entity("student removed successfully.")
		                .build();
		    
		
		}
		
		
		
		@GET
		@Path("/ajtprfpaim")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response ajtprfpaim(
			    @QueryParam("id") int id,
			    @QueryParam("moi") String moi
			) {


			httpRequest.getSession().setAttribute("idf", id);
			if(et.ajtpai(id,moi)==1) {
	        	
				return Response.status(Response.Status.OK).entity("paiment a ete ajouter pour le prof").build();
			
			}
		    return Response.status(Response.Status.OK).entity("paiment a ete ajouter pour le prof").build();
			}
		
		
		@GET
		@Path("/ajtetdpaim")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response ajtetdpaim(
			    @QueryParam("id") int id,
			    @QueryParam("moi") String moi
			) {


			httpRequest.getSession().setAttribute("idf", id);
			if(et.ajtpai(id,moi)==1) {
	        	
				return Response.status(Response.Status.OK).entity("paiment a ete ajouter pour l etudiant").build();
			
			}
		    return Response.status(Response.Status.OK).entity("paiment a ete ajouter pour l etudiant").build();
			}
		
		
		
		@GET
		@Path("/absenceprof")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response absenceprof(
				@QueryParam("id") int id,
			    @QueryParam("dateAbsenceString") String dateAbsenceString
			    
			) {
			
			httpRequest.getSession().setAttribute("idf", id);
			LocalDate dateAbsence = LocalDate.parse(dateAbsenceString);
	        Month moisAbsence = dateAbsence.getMonth();
	        switch (moisAbsence) {
	        case JANUARY:
	        	et.ajtabs(id,"janvier");
	            break;
	        case FEBRUARY:
	        	et.ajtabs(id,"fevrier");
	            break;
	        case MARCH:
	        	et.ajtabs(id,"mars");
	            break;
	        case APRIL:
	        	et.ajtabs(id,"avril");
	            break;
	        case MAY:
	        	et.ajtabs(id,"mai");
	            break;
	        case JUNE:
	        	et.ajtabs(id,"juin");
	            break;
	        case OCTOBER:
	        	et.ajtabs(id,"octorbe");
	            break;
	        case NOVEMBER:
	        	et.ajtabs(id,"novembre");
	            break;
	        case DECEMBER:
	        	et.ajtabs(id,"decembre");
	            break;
	        default:
	        	break;
	    }
			
				    return Response.status(Response.Status.OK).entity("absence ajouter").build();
			}
		
		
		@GET
		@Path("/absenceetud")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response absenceetud(
				@QueryParam("id") int id,
			    @QueryParam("dateAbsenceString") String dateAbsenceString
			    
			) {
			
			httpRequest.getSession().setAttribute("idf", id);
			LocalDate dateAbsence = LocalDate.parse(dateAbsenceString);
	        Month moisAbsence = dateAbsence.getMonth();
	        switch (moisAbsence) {
	        case JANUARY:
	        	et.ajtabs(id,"janvier");
	            break;
	        case FEBRUARY:
	        	et.ajtabs(id,"fevrier");
	            break;
	        case MARCH:
	        	et.ajtabs(id,"mars");
	            break;
	        case APRIL:
	        	et.ajtabs(id,"avril");
	            break;
	        case MAY:
	        	et.ajtabs(id,"mai");
	            break;
	        case JUNE:
	        	et.ajtabs(id,"juin");
	            break;
	        case OCTOBER:
	        	et.ajtabs(id,"octorbe");
	            break;
	        case NOVEMBER:
	        	et.ajtabs(id,"novembre");
	            break;
	        case DECEMBER:
	        	et.ajtabs(id,"decembre");
	            break;
	        default:
	        	break;
	    }
			
				    return Response.status(Response.Status.OK).entity("absence ajouter").build();
			}
		
		
		@GET
		@Path("/ajouteradmannc")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response ajouteradmannc(
				@QueryParam("id") int id,
			    @QueryParam("formation") String formation,
			    @QueryParam("nom") String nom,
			    @QueryParam("prenom") String prenom
			    
			) {
			
			httpRequest.getSession().setAttribute("idf", id);
			et.addanc(id,formation,nom,prenom);
	        		
				    return Response.status(Response.Status.OK).entity("annonce a ete ajouté").build();
			}
		
		
		@GET
		@Path("/ajouterprofannc")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response ajouterprofannc(
				@QueryParam("id") int id,
			    @QueryParam("formation") String formation,
			    @QueryParam("nom") String nom,
			    @QueryParam("prenom") String prenom
			    
			) {
			
			httpRequest.getSession().setAttribute("idf", id);
			et.addanc(id,formation,nom,prenom);
	        		
				    return Response.status(Response.Status.OK).entity("annonce a ete ajouté").build();
			}
		
		
		@GET
		@Path("/searchprof")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response searchprof(
				@QueryParam("grp") int grp,
			    @QueryParam("niveau") String niv
			    
			) {
			
			ResultSet rs = et.allprofsrch(grp, niv);
			
			httpRequest.getSession().setAttribute("rs", rs);
			
	        		
				    return Response.status(Response.Status.OK).entity("annonce a ete ajouté").build();
			}
		
		
		@GET
		@Path("/ajouterprof")
		@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		public Response ajouterprof(
				@QueryParam("nom") String nom,
			    @QueryParam("prenom") String prenom,
			    @QueryParam("password") String password,
			    @QueryParam("mail") String mail,
			    @QueryParam("phone") String phone,
			    @QueryParam("niveau") String niveau,
			    @QueryParam("matiere") String matiere,
			    @QueryParam("salaire") float salaire
			    		    
			) {
			
			Etud prof = new Etud(nom, prenom, mail, phone, password, "Prof");
			
			
			httpRequest.getSession().setAttribute("nom", nom);
			httpRequest.getSession().setAttribute("prenom", prenom);
			httpRequest.getSession().setAttribute("mail", mail);
			httpRequest.getSession().setAttribute("phone", phone);
			httpRequest.getSession().setAttribute("password", password);
			httpRequest.getSession().setAttribute("salaire", salaire);
			httpRequest.getSession().setAttribute("matiere", matiere);
			httpRequest.getSession().setAttribute("niveau", niveau);
			
			int nb = 0;
		    nb = et.addUserp(prof);
		    
		    String mails = (String) httpRequest.getSession().getAttribute("mail");
		    
		    
		    if (nb == 1) {
		        ResultSet rs = et.findemail(mails);
		        try {
		            while (rs.next()) {
		                int n = rs.getInt("id");
		                httpRequest.getSession().setAttribute("id", n);
		                
		                String noms = (String) httpRequest.getSession().getAttribute("nom");
		                
		                String prenoms = (String) httpRequest.getSession().getAttribute("prenom");
		                
		                String emails = (String) httpRequest.getSession().getAttribute("mail");
		                
		                String phones = (String) httpRequest.getSession().getAttribute("phone");
		                
		                String passwords = (String) httpRequest.getSession().getAttribute("password");
		                
		                float salaires = (float) httpRequest.getSession().getAttribute("salaire");
		                
		                String matieres = (String) httpRequest.getSession().getAttribute("matiere");
		                
		                
		                String niveaus = (String) httpRequest.getSession().getAttribute("niveau");
		                
		                Prof pr=new Prof(noms,prenoms,emails,phones,passwords,"Prof",matieres,salaires,niveaus,n);
		                
		                int v =0;v= et.addprof(pr);
		                if (v == 1)
		                	et.addpai(n);
	               	 et.addabs(n);
	               	String matieress = (String) httpRequest.getSession().getAttribute("matiere");
	               	switch (matieress) {
	                case "MATH":
	                    et.addpmat(n, 1);
	                    break;

	                case "PHYSIQUE":
	               	 et.addpmat(n, 2);
	                    break;

	                case "SVT":
	               	 et.addpmat(n, 3);
	                    break;

	                case "COMPTABILITE":
	               	 et.addpmat(n, 4);
	                    break;

	                case "PHILOSOPHIE":
	               	 et.addpmat(n, 5);
	                    break;

	                case "ANGLAIS":
	               	 et.addpmat(n, 6);
	                    break;

	                default:
	                    
	                    break;
	            }
		                
	                
		               
		            }

		        } catch (SQLException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		    }
			
	        		
				    return Response.status(Response.Status.OK).entity("prof a ete ajouté").build();
			}
		
		

}
