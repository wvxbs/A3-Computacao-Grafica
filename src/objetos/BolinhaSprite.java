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

//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(0)+posx),(float)((tamanho[1]+ raio)*Math.sin(0)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(30)+posx),(float)((tamanho[1]+ raio)*Math.sin(30)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(45)+posx),(float)((tamanho[1]+ raio)*Math.sin(45)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(60)+posx),(float)((tamanho[1]+ raio)*Math.sin(60)+posy),posz);
//
//        //gl.glVertex3f(-tamanho[0]+posx, tamanho[1]+posy, posz);
//
//
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(90)+posx),(float)((tamanho[1]+ raio)*Math.sin(90)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(120)+posx),(float)((tamanho[1]+ raio)*Math.sin(120)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(135)+posx),(float)((tamanho[1]+ raio)*Math.sin(135)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(150)+posx),(float)((tamanho[1]+ raio)*Math.sin(150)+posy),posz);
//
//        //gl.glVertex3f(tamanho[0]+posx, tamanho[1]+posy, posz);
//
//
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(180)+posx),(float)((tamanho[1]+ raio)*Math.sin(180)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(210)+posx),(float)((tamanho[1]+ raio)*Math.sin(210)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(225)+posx),(float)((tamanho[1]+ raio)*Math.sin(225)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(240)+posx),(float)((tamanho[1]+ raio)*Math.sin(240)+posy),posz);
//
//        //gl.glVertex3f(tamanho[0]+posx, -tamanho[1]+posy, posz);
//
//
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(270)+posx),(float)((tamanho[1]+ raio)*Math.sin(270)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(300)+posx),(float)((tamanho[1]+ raio)*Math.sin(300)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(315)+posx),(float)((tamanho[1]+ raio)*Math.sin(315)+posy),posz);
//        gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(330)+posx),(float)((tamanho[1]+ raio)*Math.sin(330)+posy),posz);
//
//        //gl.glVertex3f(-tamanho[0]+posx, -tamanho[1]+posy, posz);
//        //gl.glVertex3f(-tamanho[0]+posx, tamanho[1]+posy, posz);

        // TODO arrumar o mapeamento de textura pra ficar bunitin
        for(float i=0 ; i < limite; i+= 0.1f) {
            gl.glVertex3f((float)((tamanho[0]+ raio)*Math.cos(i)+posx),(float)((tamanho[1]+ raio)*Math.sin(i)+posy),posz);
            gl.glTexCoord2f((float) ((0.0f*Math.cos(i))+texturaOfset[0]), (float) ((limite*Math.sin(i))+texturaOfset[1]));
//            switch ((int) i){
//                case 1:
//                    gl.glTexCoord2f(0.0f+texturaOfset[0]+escala[0], limite+texturaOfset[1]+escala[1]);
//                    break;
//
//                case 2:
//                    gl.glTexCoord2f(limite+texturaOfset[0]+escala[0], limite+texturaOfset[1]+escala[1]);
//                    break;
//
//                case 3:
//                    gl.glTexCoord2f(limite+texturaOfset[0], 0.0f+texturaOfset[1]);
//                    break;
//
//                case 4:
//                    gl.glTexCoord2f(0.0f+texturaOfset[0], 0.0f+texturaOfset[1]);
//                    break;
//
//                default:
//                    break;
//            }
//
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
