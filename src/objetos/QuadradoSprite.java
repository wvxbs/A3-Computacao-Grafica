package objetos;

import com.jogamp.opengl.GL2;
import Textura.Textura;

public class QuadradoSprite {


    // atributos
    private float posx, posy, posz; // atr de posicionamento
    private float velx=0, vely=0, velz=0; // atr de movimentação
    private float[] tamanho = new float[2]; // tamanho x,y
    private float[] corRGB = new float[3]; // cores r,g,b
    private String imageSrc; // url ou path da imagem
    private int imageIndice;
    private Textura textura;
    private int totalTexturas;
    private int filtro, wrap, modo;
    private float limite;


    // getters e setters
    public float getVelx() {return velx;}
    public void setVelx(float velx) {this.velx = velx;}

    public float getVely() {return vely;}
    public void setVely(float vely) {this.vely = vely;}

    public float getVelz() {return velz;}
    public void setVelz(float velz) {this.velz = velz;}

    public float getPosx() {return posx;}
    public void setPosx(float posx) {this.posx = posx;}

    public float getPosy() {return posy;}
    public void setPosy(float posy) {this.posy = posy;}

    public float getPosz() {return posz;}
    public void setPosz(float posz) {this.posz = posz;}

    public float[] getTamanho() {return tamanho;}
    public void setTamanho(float[] tamanho) {this.tamanho = tamanho;}

    public float[] getCorRGB() {return corRGB;}
    public void setCorRGB(float[] corRGB) {this.corRGB = corRGB;}

    public String getImageSrc() {return imageSrc;}
    public void setImageSrc(String imageSrc) {this.imageSrc = imageSrc;}

    public int getImageIndice() {return imageIndice;}
    public void setImageIndice(int imageIndice) {this.imageIndice = imageIndice;}

    public Textura getTextura() {return textura;}
    public void setTextura(Textura textura) {this.textura = textura;}

    public int getTotalTexturas() {return totalTexturas;}
    public void setTotalTexturas(int totalTexturas) {this.totalTexturas = totalTexturas;}

    public int getFiltro() {return filtro;}
    public void setFiltro(int filtro) {this.filtro = filtro;}

    public int getWrap() {return wrap;}
    public void setWrap(int wrap) {this.wrap = wrap;}

    public int getModo() {return modo;}
    public void setModo(int modo) {this.modo = modo;}


    public float getLimite() {return limite;}

    public void setLimite(float limite) {this.limite = limite;}


    // construtores
    public QuadradoSprite(){}

    public QuadradoSprite(int totalTexturas){
        this.totalTexturas = totalTexturas;
        this.textura = new Textura(totalTexturas);
    }

    public QuadradoSprite(int totalTexturas, int filtro, int wrap, int modo, float limite){
        this.totalTexturas = totalTexturas;
        this.limite = limite;
        this.filtro = filtro;
        this.wrap = wrap;
        this.modo = modo;
        this.textura = new Textura(totalTexturas);
    }

    public QuadradoSprite(int totalTexturas, int filtro,
                          int wrap, int modo, float limite, float[] tamanho, float[] corRGB, String imageSrc) {
        this.tamanho = tamanho;
        this.corRGB = corRGB;
        this.imageSrc = imageSrc;
        this.totalTexturas = totalTexturas;
        this.filtro = filtro;
        this.wrap = wrap;
        this.modo = modo;
        this.limite = limite;
        this.textura = new Textura(totalTexturas);
    }


    // métodos
    public void desenhar(GL2 gl){
        gl.glPushMatrix();
        gl.glColor3f(corRGB[0], corRGB[1], corRGB[2]);

        textura.setFiltro(filtro);
        textura.setModo(modo);
        textura.setWrap(wrap);

        //cria a textura indicando o local da imagem e o índice
        textura.gerarTextura(gl, imageSrc, imageIndice);

        // Face FRONTAL
        gl.glBegin (GL2.GL_QUADS);
        //coordenadas da Textura
        gl.glTexCoord2f(0.0f, limite); gl.glVertex3f(-tamanho[0]+posx, tamanho[1]+posy, posz);
        gl.glTexCoord2f(limite, limite);  gl.glVertex3f(tamanho[0]+posx, tamanho[1]+posy, posz);
        gl.glTexCoord2f(limite, 0.0f);  gl.glVertex3f(tamanho[0]+posx, -tamanho[1]+posy, posz);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-tamanho[0]+posx, -tamanho[1]+posy, posz);
        gl.glVertex3f(-tamanho[0]+posx, tamanho[1]+posy, posz);
        gl.glEnd();
        //desabilita a textura indicando o índice
        textura.desabilitarTextura(gl, imageIndice);
        gl.glPopMatrix();
    }

    public void mover(){
        posx+=velx;
        posy+=vely;
    }
    public void mover(float velx, float vely){
        posx+=velx;
        posy+=vely;
    }

    public boolean isColiding(float[] intervaloX, float[] intervaloY){
        float[] intervaloQx = {(-tamanho[0]+posx)/2, (tamanho[0]+posx)/2};
        float[] intervaloQy = {(-tamanho[1]+posy)/2, (tamanho[1]+posy)/2};
        //System.out.println("intervalo Xquadrado: "+intervaloQx[0]+"|"+intervaloQx[1]);
        //System.out.println("intervalo Yquadrado: "+intervaloQy[0]+"|"+intervaloQy[1]);

        boolean toqueX=false, toqueY=false;

        if ( ((intervaloX[0] >= intervaloQx[0] && intervaloX[0] <= intervaloQx[1]) ||
            (intervaloX[1] >= intervaloQx[0] && intervaloX[1] <= intervaloQx[1]))
                ||
            ((intervaloQx[0] >= intervaloX[0] && intervaloQx[0] <= intervaloX[1]) ||
            (intervaloQx[1] >= intervaloX[0] && intervaloQx[1] <= intervaloX[1])) )
        {
            toqueX = true;
            //System.out.println("ToqueX");
        }

        if (((intervaloY[0] >= intervaloQy[0] && intervaloY[0] <= intervaloQy[1]) ||
            (intervaloY[1] >= intervaloQy[0] && intervaloY[1] <= intervaloQy[1]))
                ||
            ((intervaloQy[0] >= intervaloY[0] && intervaloQy[0] <= intervaloY[1]) ||
            (intervaloQy[1] >= intervaloY[0] && intervaloQy[1] <= intervaloY[1])))
        {
            toqueY = true;
            //System.out.println("ToqueY");
        }


        if (toqueX && toqueY){return true;}
        return false;

    }
}
