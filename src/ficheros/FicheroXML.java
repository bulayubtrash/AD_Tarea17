package ficheros;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import objetos.Alumno;
import objetos.Grupo;

public class FicheroXML implements FicherosPadre {
	

	@Override
	public void AlumnoToFichero(String ruta, ArrayList<Alumno> object) {

		try {
			DocumentBuilderFactory f= DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder b=f.newDocumentBuilder();
			Document d=b.newDocument();
			
			Element rootElement =d.createElement("Alumnos");
			d.appendChild(rootElement);
			
			for (Alumno alumno : object) {
				Element al=d.createElement("Alumno");

				Element nia=d.createElement("NIA");
				nia.appendChild(d.createTextNode(String.valueOf(alumno.getNia())));
				al.appendChild(nia);

				Element nombre=d.createElement("Nombre");
				nombre.appendChild(d.createTextNode(alumno.getNombre()));
				al.appendChild(nombre);

				Element apellido=d.createElement("Apellidos");
				apellido.appendChild(d.createTextNode(alumno.getApellidos()));
				al.appendChild(apellido);

				Element genero=d.createElement("Genero");
				genero.appendChild(d.createTextNode(String.valueOf(alumno.getGenero())));
				al.appendChild(genero);

				if(alumno.getFechaNac()!=null) {
					SimpleDateFormat formato= new SimpleDateFormat("dd/mm/yyyy");
					String fechaM=formato.format(alumno.getFechaNac());

					Element fechaXML=d.createElement("FechaNacimiento");
					fechaXML.appendChild(d.createTextNode(fechaM));
//					fechaXML.appendChild(d.createTextNode(String.valueOf(a1.getFechaNac())));

					al.appendChild(fechaXML);
				}else {
					Element fecha=d.createElement("FechaNacimiento");
					fecha.appendChild(d.createTextNode(""));
					al.appendChild(fecha);
				}


				Element ciclo=d.createElement("Ciclo");
				ciclo.appendChild(d.createTextNode(alumno.getCiclo()));
				al.appendChild(ciclo);

				Element curso=d.createElement("Curso");
				curso.appendChild(d.createTextNode(alumno.getCurso()));
				al.appendChild(curso);

				Element grupo=d.createElement("Grupo");
				grupo.appendChild(d.createTextNode(alumno.getGrupo()));
				al.appendChild(grupo);

				rootElement.appendChild(al);
			}
			TransformerFactory tf= TransformerFactory.newInstance();
			Transformer t=tf.newTransformer();

		     // Configuración para formato legible
	        t.setOutputProperty(OutputKeys.INDENT, "yes");
	        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource source = new DOMSource(d);
			StreamResult result =new StreamResult(new File(ruta));
			t.transform(source, result);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public ArrayList<Alumno> FicheroToAlumno(String ruta) {
	    Alumno a1;
	    ArrayList<Alumno>aLista= new ArrayList<>();
		try {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document documento = builder.parse(new File(ruta));

	        NodeList listaAlumnos = documento.getElementsByTagName("Alumno");

	        for (int i = 0; i < listaAlumnos.getLength(); i++) {
	            Node nodo = listaAlumnos.item(i);

	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Element alumnoElement = (Element) nodo;

	                a1 = new Alumno();

	                // Obtener los valores de los elementos hijos y usarlos con los setters
	                a1.setNia(Integer.parseInt(alumnoElement.getElementsByTagName("NIA").item(0).getTextContent()));
	                a1.setNombre(alumnoElement.getElementsByTagName("Nombre").item(0).getTextContent());
	                a1.setApellidos(alumnoElement.getElementsByTagName("Apellidos").item(0).getTextContent());
	                a1.setGenero(alumnoElement.getElementsByTagName("Genero").item(0).getTextContent().charAt(0));

	                String fechaNacimientoStr = alumnoElement.getElementsByTagName("FechaNacimiento").item(0).getTextContent();
	                if (!fechaNacimientoStr.isEmpty() && !fechaNacimientoStr.equals("null")) {
//	                    SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.ENGLISH);
	                    SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");

	                    a1.setFechaNac(formato.parse(fechaNacimientoStr));
	                } else {
	                    a1.setFechaNac(null);
	                }

	                a1.setCiclo(alumnoElement.getElementsByTagName("Ciclo").item(0).getTextContent());
	                a1.setCurso(alumnoElement.getElementsByTagName("Curso").item(0).getTextContent());
	                a1.setGrupo(alumnoElement.getElementsByTagName("Grupo").item(0).getTextContent());

	                aLista.add(a1);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return aLista;
	}

	@Override
	public void GrupoToFichero(String ruta, ArrayList<Grupo> object) {
		try {
			DocumentBuilderFactory f= DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder b=f.newDocumentBuilder();
			Document d=b.newDocument();
			
			Element rootElement =d.createElement("Grupos");
			d.appendChild(rootElement);
			
			for (Grupo grupo : object) {
				Element gr=d.createElement("Grupo");

				Element nia=d.createElement("ID");
				nia.appendChild(d.createTextNode(String.valueOf(grupo.getId())));
				gr.appendChild(nia);

				Element nombre=d.createElement("Nombre");
				nombre.appendChild(d.createTextNode(grupo.getGrupo()));
				gr.appendChild(nombre);

				rootElement.appendChild(gr);
			}
			TransformerFactory tf= TransformerFactory.newInstance();
			Transformer t=tf.newTransformer();

		     // Configuración para formato legible
	        t.setOutputProperty(OutputKeys.INDENT, "yes");
	        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			DOMSource source = new DOMSource(d);
			StreamResult result =new StreamResult(new File(ruta));
			t.transform(source, result);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public ArrayList<Grupo> FicheroToGrupo(String ruta) {
	    Grupo g1;
	    ArrayList<Grupo>gLista= new ArrayList<>();
		try {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document documento = builder.parse(new File(ruta));

	        NodeList listaGrupos = documento.getElementsByTagName("Grupo");

	        for (int i = 0; i < listaGrupos.getLength(); i++) {
	            Node nodo = listaGrupos.item(i);

	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Element grupoElement = (Element) nodo;

	                g1 = new Grupo();

	                // Obtener los valores de los elementos hijos y usarlos con los setters
	                g1.setId(Integer.parseInt(grupoElement.getElementsByTagName("ID").item(0).getTextContent()));
	                g1.setGrupo(grupoElement.getElementsByTagName("Grupo").item(0).getTextContent());
	                
	                gLista.add(g1);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return gLista;
	}

}
