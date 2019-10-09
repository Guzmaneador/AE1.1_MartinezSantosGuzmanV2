package Vista;

import java.util.ArrayList;

/**
 *
 * @author 2dama
 */
public interface Vista {
    
    public ArrayList<String> getListaUrls();
    
    public ArrayList<String> getListaArchivos();
    
    public String[] getColumnasCsv();
    
    public String getArchivoEscritura();
    
    public String getArchivoBinario();
    
}
