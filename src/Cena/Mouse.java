package Cena;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;

public class Mouse implements MouseListener {

    // atributos
    private Cena cena;

    public Mouse(Cena cena) {
        this.cena = cena;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        if (cena.mouseHabilitado) {
//            System.out.println("mouse X: "+(float)((e.getX()/5)-100)+" | mouse Y: "+(float)((e.getY()/5)-100));
//            // obtendo valores tratados de x e y do clique na tela
//            cena.mouseX = (float)((e.getX()/5)-100);
//            cena.mouseY = (float)((e.getY()/5)-100)*-1;
//
//            if (cena.mouseX > cena.jogador.getObjSprite().getIntervaloDireita()[0][0]) { // alterna a movimentação da bola palos
//                cena.jogador.getObjSprite().setMovendo(true);
//                cena.jogador.getObjSprite().setDirecao(Direcao.DIREITA);
//            }
//            if (cena.mouseX < cena.jogador.getObjSprite().getIntervaloEsquerda()[0][1]) { // alterna a movimentação da bola palos
//                cena.jogador.getObjSprite().setMovendo(true);
//                cena.jogador.getObjSprite().setDirecao(Direcao.ESQUERDA);
//            }
//            if ((float)((e.getX()/5)-100) >= cena.jogador.getObjSprite().getIntervaloDireita()[0][0] &&
//                    e.getX() <= cena.jogador.getObjSprite().getIntervaloEsquerda()[0][1]) {
//                cena.jogador.getObjSprite().setMovendo(false);
//            }
//        }
//
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (cena.mouseHabilitado) {
            cena.mouseX = (float) ((e.getX() / 5) - cena.largura);
            cena.mouseY = (float) ((e.getY() / 5) - cena.altura) * -1;

            if (cena.jogador.isJogando()) {

                //System.out.println("mouse X: "+(float)((e.getX()/5)-100)+" | mouse Y: "+(float)e.getY());

                //mouse pra direita
                if (cena.mouseX > cena.jogador.getObjSprite().getIntervaloDireita()[0][0]) {
                    cena.jogador.getObjSprite().setMovendo(true);
                    cena.jogador.getObjSprite().setDirecaoX(Direcao.DIREITA);
                }
                // mouse pra esquerda
                if (cena.mouseX < cena.jogador.getObjSprite().getIntervaloEsquerda()[0][1]) {
                    cena.jogador.getObjSprite().setMovendo(true);
                    cena.jogador.getObjSprite().setDirecaoX(Direcao.ESQUERDA);
                }
                // mouse em cima da barra
                if ( cena.mouseX >= cena.jogador.getObjSprite().getIntervaloDireita()[0][0] &&
                        cena.mouseX <= cena.jogador.getObjSprite().getIntervaloEsquerda()[0][1]) {
                    cena.jogador.getObjSprite().setMovendo(false);
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseEvent e) {

    }
}
