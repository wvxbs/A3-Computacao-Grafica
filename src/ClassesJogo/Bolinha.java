package ClassesJogo;

import objetos.QuadradoSprite;

public class Bolinha {

    // Atributos
    protected QuadradoSprite objSprite;
    protected String skin;

    // getters e setters
    public QuadradoSprite getObjSprite() {return objSprite;}
    public void setObjSprite(QuadradoSprite objSprite) {this.objSprite = objSprite;}

    public String getSkin() {return skin;}
    public void setSkin(String skin) {this.skin = skin;}

    public Bolinha(QuadradoSprite objSprite, String skin) {
        this.objSprite = objSprite;
        this.skin = skin;

    }

    public void trocarSkin(String novaSkin){
        skin = novaSkin;
        objSprite.setImageSrc(skin);
    }


}
