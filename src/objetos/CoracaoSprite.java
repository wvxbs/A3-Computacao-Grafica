package objetos;

import Textura.Textura;
import com.jogamp.opengl.GL2;

public class CoracaoSprite extends QuadradoSprite{

    protected String[] imageSrc;
    public int modoCoracao = 0;

    public CoracaoSprite(int totalSprites, int filtro,
                          int wrap, int modo, float limite, float[] tamanho, float[] corRGB, String[] imageSrc, boolean animado) {
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

    public int getModoCoracao() {
        return modoCoracao;
    }

    public void setModoCoracao(int modoCoracao) {
        this.modoCoracao = modoCoracao;
    }

    @Override
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
        textura.gerarTextura(gl, imageSrc[modoCoracao], 0);

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

}
