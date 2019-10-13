package Vista;

import java.util.ArrayList;

/**
 *
 * @author 2dama
 */
public class VistaImpl implements Vista{
    
    private ArrayList <String> listaUrls = new ArrayList<>();
    private ArrayList <String> listaArchivos = new ArrayList<>();
    private String[] columnasCsv;
    private String rutaArchivoCsv;
    private String archivoBinario;
    private String archivoXML;
    
    //Para poder ampliar este proyecto a archivos y urls q el usuario indica bastaria con un bucle que
    //pide al usuario la url y despues la ruta del nuevo archivo de forma que los ira almacenando en los array 
    //para que posteriormente el controlador los mande de golpe al modelo que se encargara de crear los archivos 
    //que el usuario desea descragar
    public VistaImpl() {
        
        listaUrls.add("https://www.santacruzdetenerife.es/opendata/dataset/72d40b02-d97d-470a-abde-a823966b08a4/resource/6f1f90bd-5dd4-496b-bbc2-792c5e0aac55/download/cont_rspc.csv");
        listaUrls.add("https://www.santacruzdetenerife.es/opendata/dataset/643d3bec-31bb-463b-9e87-88abe4c3aeec/resource/277f2d4a-2f30-496b-9053-4295c0fb05a6/download/cont_rsev.csv");
        listaUrls.add("https://www.santacruzdetenerife.es/opendata/dataset/09353d93-77d0-429d-9fcb-d13679052185/resource/afe59017-eff0-45a4-ae14-9b7a1998b025/download/cont_rsel.csv");
        listaUrls.add("https://www.santacruzdetenerife.es/opendata/dataset/e0fcf65c-410f-4a30-838b-745cc9a06128/resource/312fa089-c6bb-4db6-96b9-faed47278439/download/cont_raee.csv");
        listaUrls.add("https://www.santacruzdetenerife.es/opendata/dataset/79123de3-8096-483b-a6ba-708633dacd3c/resource/c66bede7-6883-43c0-a3af-9961880c3413/download/cont_rrsu.csv");
        
        listaArchivos.add("src\\main\\java\\datosDescargados\\Papel_Carton.csv");//src\main\java
        listaArchivos.add("src\\main\\java\\datosDescargados\\Vidrio.csv");
        listaArchivos.add("src\\main\\java\\datosDescargados\\Envases.csv");
        listaArchivos.add("src\\main\\java\\datosDescargados\\Electricos.csv");
        listaArchivos.add("src\\main\\java\\datosDescargados\\Solidos.csv");
 
        this.columnasCsv = new String[]{"CODCALLE", "TEXTO", "CAPACIDAD", "UTM_X", "UTM_Y", "NOTAS", "TIPO"};
        
        rutaArchivoCsv = "src\\main\\java\\datosTratados\\Contenedores.csv";
        archivoBinario = "src\\main\\java\\datosTratados\\Contenedores.dat";
        archivoXML = "src\\main\\java\\datosTratados\\Contenedores.xml";
        
    }

    @Override
    public ArrayList<String> getListaUrls() {
        return listaUrls;
    }

    @Override
    public ArrayList<String> getListaArchivos() {
        return listaArchivos;
    }

    @Override
    public String[] getColumnasCsv() {
        return columnasCsv;
    }

    @Override
    public String getRutaArchivoCsv() {
        return rutaArchivoCsv;
    }

    @Override
    public String getArchivoBinario() {
        return archivoBinario;
    }

    @Override
    public String getArchivoXML() {
        return archivoXML;
    }
    
    

    
    
    
}
