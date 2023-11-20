package objetos;

import Cena.Direcao;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import Textura.Textura;


public class QuadradoSprite {

    // atributos
    private float posx, posy, posz; // atr de posicionamento
    private float velx=0, vely=0, velz=0; // atr de movimentação
    private boolean movendo = false;
    private Direcao direcao;
    private float[] tamanho = new float[2]; // tamanho x,y
    private float[][] intervaloCima, intervaloBaixo, intervaloDireita, intervaloEsquerda, intervaloTotal;
    private float[] corRGB = new float[3]; // cores r,g,b
    private float alfa=1;
    private String imageSrc; // url ou path da imagem
    private float imageIndice;
    private boolean animado = false;
    private boolean invisivel = false;
    private Textura textura;
    private int totalSprites;
    private int filtro, wrap, modo;
    private float limite;
    private float[] texturaOfset = {0,0};
    private float[] escala = {0,0};
    float contadorImg = 0f;


    // getters e setters

    public float[][] getIntervaloTotal() {return intervaloTotal;}
    public void setIntervaloTotal(float[][] intervaloTotal) {this.intervaloTotal = intervaloTotal;}

    public boolean isAnimado() {return animado;}
    public void setAnimado(boolean animado) {this.animado = animado;}

    public float[] getEscala() {return escala;}
    public void setEscala(float[] escala) {this.escala = escala;}

    public float[] getTexturaOfset() {return texturaOfset;}
    public void setTexturaOfset(float[] texturaOfset) {this.texturaOfset = texturaOfset;}

    public Direcao getDirecao() {return direcao;}
    public void setDirecao(Direcao direcao) {this.direcao = direcao;}

    public float getAlfa() {return alfa;}
    public void setAlfa(float alfa) {this.alfa = alfa;}

    public boolean isInvisivel() {return invisivel;}
    public void setInvisivel(boolean invisivel) {this.invisivel = invisivel;}

    public boolean isMovendo() {return movendo;}
    public void setMovendo(boolean movendo) {this.movendo = movendo;}

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

    public float getImageIndice() {return imageIndice;}
    public void setImageIndice(float imageIndice) {this.imageIndice = imageIndice;}

    public Textura getTextura() {return textura;}
    public void setTextura(Textura textura) {this.textura = textura;}

    public int getTotalSprites() {return totalSprites;}
    public void setTotalSprites(int totalSprites) {this.totalSprites = totalSprites;}

    public int getFiltro() {return filtro;}
    public void setFiltro(int filtro) {this.filtro = filtro;}

    public int getWrap() {return wrap;}
    public void setWrap(int wrap) {this.wrap = wrap;}

    public int getModo() {return modo;}
    public void setModo(int modo) {this.modo = modo;}


    public float getLimite() {return limite;}

    public void setLimite(float limite) {this.limite = limite;}

    public float[][] getIntervaloEsquerda() {return intervaloEsquerda;}
    public void setIntervaloEsquerda(float[][] intervaloEsquerda) {this.intervaloEsquerda = intervaloEsquerda;}

    public float[][] getIntervaloDireita() {return intervaloDireita;}
    public void setIntervaloDireita(float[][] intervaloDireita) {this.intervaloDireita = intervaloDireita;}

    public float[][] getIntervaloBaixo() {return intervaloBaixo;}
    public void setIntervaloBaixo(float[][] intervaloBaixo) {this.intervaloBaixo = intervaloBaixo;}

    public float[][] getIntervaloCima() {return intervaloCima;}
    public void setIntervaloCima(float[][] intervaloCima) {this.intervaloCima = intervaloCima;}


    // construtores
    public QuadradoSprite(){}

    public QuadradoSprite(float[] tamanho){
        this.tamanho[0] = tamanho[0]/2;
        this.tamanho[1] = tamanho[1]/2;
    }

    public QuadradoSprite(int totalSprites, int filtro, int wrap, int modo, float limite){
        this.totalSprites = totalSprites;
        this.limite = limite;
        this.filtro = filtro;
        this.wrap = wrap;
        this.modo = modo;
        this.textura = new Textura(totalSprites);
    }

    public QuadradoSprite(int totalSprites, int filtro,
                          int wrap, int modo, float limite, float[] tamanho, float[] corRGB, String imageSrc, boolean animado) {
        this.tamanho[0] = tamanho[0]/2;
        this.tamanho[1] = tamanho[1]/2;
        this.corRGB = corRGB;
        this.imageSrc = imageSrc;
        this.totalSprites = totalSprites;
        this.filtro = filtro;
        this.wrap = wrap;
        this.modo = modo;
        this.limite = limite;
        this.textura = new Textura(totalSprites);
        this.animado = animado;
        if (totalSprites>1){
            this.escala[0] = 0;
            this.escala[1] = limite-(limite/ totalSprites);
        }
    }


    // métodos
    public void desenhar(GL2 gl){
        // atualizando sprite
        atualizarSprite();

        // indo pro desenho
        //gl.glEnable(gl.GL_BLEND);
        //gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);

        gl.glPushMatrix();
        gl.glClearColor(0, 0, 0, 0);
        gl.glColor4f(corRGB[0], corRGB[1], corRGB[2], alfa);

        textura.setFiltro(filtro);
        textura.setModo(modo);
        textura.setWrap(wrap);

        //cria a textura indicando o local da imagem e o índice
        textura.gerarTextura(gl, imageSrc, 0);

        // Face FRONTAL
        gl.glBegin (GL2.GL_QUADS);
        //coordenadas da Textura
        gl.glTexCoord2f(0.0f+texturaOfset[0]+escala[0], limite+texturaOfset[1]+escala[1]);
        gl.glVertex3f(-tamanho[0]+posx, tamanho[1]+posy, posz);

        gl.glTexCoord2f(limite+texturaOfset[0]+escala[0], limite+texturaOfset[1]+escala[1]);
        gl.glVertex3f(tamanho[0]+posx, tamanho[1]+posy, posz);

        gl.glTexCoord2f(limite+texturaOfset[0], 0.0f+texturaOfset[1]);
        gl.glVertex3f(tamanho[0]+posx, -tamanho[1]+posy, posz);

        gl.glTexCoord2f(0.0f+texturaOfset[0], 0.0f+texturaOfset[1]);
        gl.glVertex3f(-tamanho[0]+posx, -tamanho[1]+posy, posz);

        //gl.glVertex3f(-tamanho[0]+posx, tamanho[1]+posy, posz);

        gl.glEnd();
        //desabilita a textura indicando o índice

        textura.desabilitarTextura(gl, 0);
        gl.glPopMatrix();

        //gl.glDisable(GL.GL_BLEND);
        atualizarIntervalos();
    }

    private void atualizarSprite(){
        if (animado){

            contadorImg = (contadorImg >= totalSprites) ? 0 : (contadorImg + 0.15f);
            imageIndice = (int)contadorImg;

            texturaOfset[1] = imageIndice/totalSprites;
            //System.out.println(texturaOfset[1]);
        }
    }

    private void atualizarIntervalos(){
        // TODO checar desempenho de re-declarar vs modificar posições do array de intervalo
        intervaloEsquerda = new float[][]{
                {-(tamanho[0]) + posx, -(tamanho[0]) + posx}, //(-5+movimentação, -5+movimentação) em X (não muda)
                {-(tamanho[1]) + posy, (tamanho[1]) + posy} //(-5+movimentação, 5+movimentação) em Y (muda)
        };
        intervaloDireita = new float[][]{
                {(tamanho[0])+posx, (tamanho[0])+posx}, //(5+movimentação, 5+movimentação) em X (não muda)
                {-(tamanho[1])+posy, (tamanho[1])+posy} //(-5+movimentação, 5+movimentação) em Y (muda)
        };
        intervaloBaixo = new float[][]{
                {-(tamanho[0])+posx, (tamanho[0])+posx}, //(-5+movimentação, 5+movimentação) em X (muda)
                {-(tamanho[1])+posy, -(tamanho[1])+posy} //(-5+movimentação, -5+movimentação) em Y (não muda)
        };
        intervaloCima = new float[][]{
                {-(tamanho[0])+posx, (tamanho[0])+posx}, //(-5+movimentação, 5+movimentação) em X (muda)
                {(tamanho[1])+posy, (tamanho[1])+posy} //(5+movimentação, 5+movimentação) em Y (não muda)
        };
        intervaloTotal = new float[][]{
                {-(tamanho[0])+posx, (tamanho[0])+posx},
                {-(tamanho[1])+posy, (tamanho[1])+posy}
        };
    }

    public void mover(){
        posx+=velx;
        posy+=vely;
        atualizarIntervalos();
    }

    public void mover(float velx, float vely){
        posx+=velx;
        posy+=vely;
        atualizarIntervalos();
    }

    public boolean isColiding(float[] intervaloX, float[] intervaloY){
        float[] intervaloQx = {(-tamanho[0])+posx, (tamanho[0])+posx};
        float[] intervaloQy = {(-tamanho[1])+posy, (tamanho[1])+posy};
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
