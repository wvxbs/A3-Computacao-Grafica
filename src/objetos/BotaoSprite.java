package objetos;

import com.jogamp.opengl.GL2;

public class BotaoSprite extends QuadradoSprite{

    public BotaoSprite(int totalSprites, int filtro, int wrap, int modo,
                       float limite, float[] tamanho, float[] corRGB, String imageSrc, boolean animado) {

        super(totalSprites, filtro, wrap, modo, limite, tamanho, corRGB, imageSrc, animado);
    }


    // métodos
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
        textura.gerarTextura(gl, imageSrc, 0);

        // Face FRONTAL
        gl.glBegin (GL2.GL_QUADS);

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
        //atualizarIntervalos();
    }

    //@Override
    /*protected void atualizarIntervalos(){
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
    }*/


}
