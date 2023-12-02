package EfeitosSonoros;

import java.io.File;
import java.util.ArrayList;

public class GerarListaDeEfeitosSonoros {
    private ArrayList<EfeitoSonoro> efeitosSonoros;

    public GerarListaDeEfeitosSonoros() {
        efeitosSonoros = new ArrayList<EfeitoSonoro>();
        File pasta = new File("sons/");
        File[] arquivos = pasta.listFiles();

        try {
            for (File file : arquivos) {
                if (file.isFile()) {
                    EfeitoSonoro efeitoSonoro = new EfeitoSonoro(file.getName(), file.getAbsolutePath(), file);
                    efeitosSonoros.add(efeitoSonoro);
                }
            }
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public ArrayList<EfeitoSonoro> getEfeitosSonoros () {
        return efeitosSonoros;
    }

    public void ExibirListaDeEfeitosSonoros() {
        for (EfeitoSonoro efeitoSonoro : efeitosSonoros) {
            System.out.println(efeitoSonoro.getNome());
        }
    }
}
