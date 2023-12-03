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
//        for(float i=0 ; i < Math.PI*2; i+= 0.01f) {
//            gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(i)+posx),(float)((tamanho[1]+ raio)*Math.sin(i)+posy),posz);
//
//            if (i >= (float)Math.PI && i<=(float)Math.PI+0.01f) {
//                //System.out.println("check 3.14");
//                gl.glTexCoord2f(0.f+texturaOfset[0]+escala[0],(limite/2)+texturaOfset[1]+escala[1]);
//            }
//            if (i >=(float)Math.PI/2 && i<=(float)(Math.PI/2)+0.01f) {
//                //System.out.println("check 0.78");
//                gl.glTexCoord2f(0.5f+texturaOfset[0]+escala[0], limite+texturaOfset[1]+escala[1]);
//            }
//            if (i >= (float)Math.PI*2 && i<=(float)(Math.PI*2+0.01f)){
//                //System.out.println("check 6.28");
//                gl.glTexCoord2f(limite+texturaOfset[0], (limite/2)+texturaOfset[1]);
//            }
//            if (i >=(float)Math.PI*3/2 && i<=(float)(Math.PI*3/2)+0.01f){
//                //System.out.println("check 2.35");
//                gl.glTexCoord2f((limite/2)+texturaOfset[0], 0.0f+texturaOfset[1]);
//            }
//        }
        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(90)+posx),(float)((tamanho[1]+ raio)*Math.sin(90)+posy),posz);
        gl.glTexCoord2f(0.f+texturaOfset[0]+escala[0],(limite/2)+texturaOfset[1]+escala[1]);

        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(180)+posx),(float)((tamanho[1]+ raio)*Math.sin(180)+posy),posz);
        gl.glTexCoord2f(0.5f+texturaOfset[0]+escala[0], limite+texturaOfset[1]+escala[1]);

        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(270)+posx),(float)((tamanho[1]+ raio)*Math.sin(270)+posy),posz);
        gl.glTexCoord2f(limite+texturaOfset[0], (limite/2)+texturaOfset[1]);

        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(360)+posx),(float)((tamanho[1]+ raio)*Math.sin(360)+posy),posz);
        gl.glTexCoord2f((limite/2)+texturaOfset[0], 0.0f+texturaOfset[1]);


        gl.glEnd();
        //desabilita a textura indicando o índice
        textura.desabilitarTextura(gl, 0);
        gl.glPopMatrix();


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
