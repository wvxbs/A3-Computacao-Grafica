package objetos;

import com.jogamp.opengl.GL2;

public class BolinhaSprite extends QuadradoSprite{

    protected float raio;

    //construtor
    public BolinhaSprite(int totalSprites, int filtro, int wrap, int modo,
      float limite, float[] tamanho, float raio, float[] corRGB, String imageSrc, boolean animado) {

        super(totalSprites, filtro, wrap, modo, limite, tamanho, corRGB, imageSrc, animado);
        this.raio = raio;
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
        gl.glBegin (GL2.GL_POLYGON);
        //coordenadas da Textura

        // TODO arrumar o mapeamento de textura pra ficar bunitin
        for(float i=0 ; i < Math.PI*2; i+= 0.01f) {
            gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(i)+posx),(float)((tamanho[1]+ raio)*Math.sin(i)+posy),posz);
            //System.out.println(i);
            if (i >= 3.19f && i<=3.20f) {
                //System.out.println("check 3.14");
                gl.glTexCoord2f(0.f+texturaOfset[0]+escala[0],(limite/2)+texturaOfset[1]+escala[1]);
            }
            if (i >= 0.78f && i<=0.79f) {
                //System.out.println("check 0.78");
                gl.glTexCoord2f(0.5f+texturaOfset[0]+escala[0], limite+texturaOfset[1]+escala[1]);
            }
            if (i >= 6.27f && i<=6.28f){
                //System.out.println("check 6.28");
                gl.glTexCoord2f(limite+texturaOfset[0], (limite/2)+texturaOfset[1]);
            }
            if (i >= 2.38f && i<=2.39f){
                //System.out.println("check 2.35");
                gl.glTexCoord2f((limite/2)+texturaOfset[0], 0.0f+texturaOfset[1]);
            }


        }


        gl.glEnd();
        //desabilita a textura indicando o índice

        textura.desabilitarTextura(gl, 0);
        gl.glPopMatrix();

        //gl.glDisable(GL.GL_BLEND);
        atualizarIntervalos();
    }

    @Override
    protected void atualizarIntervalos(){
        intervaloEsquerda = new float[][]{
                {-(tamanho[0]+raio) + posx, -(tamanho[0]+raio) + posx}, //(-5+movimentação, -5+movimentação) em X (não muda)
                {-(tamanho[1]+raio) + posy, (tamanho[1]+raio) + posy} //(-5+movimentação, 5+movimentação) em Y (muda)
        };
        intervaloDireita = new float[][]{
                {(tamanho[0]+raio)+posx, (tamanho[0]+raio)+posx}, //(5+movimentação, 5+movimentação) em X (não muda)
                {-(tamanho[1]+raio)+posy, (tamanho[1]+raio)+posy} //(-5+movimentação, 5+movimentação) em Y (muda)
        };
        intervaloBaixo = new float[][]{
                {-(tamanho[0]+raio)+posx, (tamanho[0]+raio)+posx}, //(-5+movimentação, 5+movimentação) em X (muda)
                {-(tamanho[1]+raio)+posy, -(tamanho[1]+raio)+posy} //(-5+movimentação, -5+movimentação) em Y (não muda)
        };
        intervaloCima = new float[][]{
                {-(tamanho[0]+raio)+posx, (tamanho[0]+raio)+posx}, //(-5+movimentação, 5+movimentação) em X (muda)
                {(tamanho[1]+raio)+posy, (tamanho[1]+raio)+posy} //(5+movimentação, 5+movimentação) em Y (não muda)
        };
        intervaloTotal = new float[][]{
                {-(tamanho[0]+raio)+posx, (tamanho[0]+raio)+posx},
                {-(tamanho[1]+raio)+posy, (tamanho[1]+raio)+posy}
        };
    }


}
