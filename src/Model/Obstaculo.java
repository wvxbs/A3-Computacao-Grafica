package Model;

import objetos.QuadradoSprite;

public class Obstaculo {
    protected QuadradoSprite objSprite;
    protected String skin;

    // getters e setters
    public QuadradoSprite getObjSprite() {return objSprite;}
    public void setObjSprite(QuadradoSprite objSprite) {this.objSprite = objSprite;}

    public String getSkin() {return skin;}
    public void setSkin(String skin) {this.skin = skin;}

    public Obstaculo(QuadradoSprite objSprite, String skin) {
        this.objSprite = objSprite;
        this.skin = skin;

    }
}
