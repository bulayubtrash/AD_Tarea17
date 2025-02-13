package ficheros;

import java.util.ArrayList;

public interface FicherosPadre {
	
	void objectToFichero(String ruta, ArrayList<Alumno> object);
	
	void FicheroToObject(String ruta);
	
	

}
