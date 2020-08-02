package Logico;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.jpl7.Atom;
import org.jpl7.Query;
import org.jpl7.Term;

public class Casa {

	Path path = Paths.get("Smart_house.pl");
	File file = new File("src/Smart_house.pl");
	
	public Casa()
	{
		Query q1 = new Query("consult", new Term[] {new Atom("src/Smart_house.pl")} );
		Query q2 = new Query("nuevo_miembro", new Term[] {new Atom("pepe")});
		Query q3 = new Query("miembro(X)");
		
		Map<String, Term>[] res = q3.allSolutions();
		
		for(int i = 0; i < res.length; i++)
		{
			System.out.println(res[i]);
		}		
		
		/*String consultaInicial = String.format("consult('%s')", "src/Smart_house.pl");
		Query q = new Query(consultaInicial); //Query a ejecutar
		Query q2 =  new Query("nuevo_miembro", new Term[] {new Atom("pepe")} );
		*/
		//System.out.println(q2.hasSolution());
	}
	
	
}
