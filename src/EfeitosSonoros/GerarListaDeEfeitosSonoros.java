package EfeitosSonoros;

import java.io.File;
import java.util.ArrayList;

public class GerarListaDeEfeitosSonoros {
    private ArrayList<Som> efeitosSonoros;

    public GerarListaDeEfeitosSonoros() {
        efeitosSonoros = new ArrayList<Som>();
        File pasta = new File("sons/");
        File[] arquivos = pasta.listFiles();

        try {
            for (File file : arquivos) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    System.out.println(file.getAbsolutePath());
                    Som som = new Som(file.getName(), file.getAbsolutePath(), file);
                    efeitosSonoros.add(som);
                }
            }
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public ArrayList<Som> getEfeitosSonoros () {
        return efeitosSonoros;
    }

    public void ExibirListaDeEfeitosSonoros() {
        for (Som som : efeitosSonoros) {
            System.out.println(som.getNome());
        }
    }
}
