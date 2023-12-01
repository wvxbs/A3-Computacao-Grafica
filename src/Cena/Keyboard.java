package Cena;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class Keyboard implements KeyListener {
    private Cena cena;

    public Keyboard(Cena cena){
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.exit(0);

        // textura
        if(e.getKeyChar() == '1'){ // alterna a movimentação da bola palos
            cena.bolinha.getObjSprite().setMovendo(!cena.bolinha.getObjSprite().isMovendo());
        }
        if(e.getKeyChar() == '2'){ // alterna a animação da bola palos
            cena.bolinha.getObjSprite().setAnimado(!cena.bolinha.getObjSprite().isAnimado());
        }
        if(e.getKeyChar() == '3'){ // diminui a escala vertical da imagem
            float[] novoTamanho = {
                    (cena.bolinha.getObjSprite().getEscala()[0]),
                    (cena.bolinha.getObjSprite().getEscala()[1]+0.25f)
            };
            cena.bolinha.getObjSprite().setEscala(novoTamanho);
        }
        if(e.getKeyChar() == '4'){ // aumenta a escala vertical da imagem
            float[] novoTamanho = {
                    (cena.bolinha.getObjSprite().getEscala()[0]),
                    (cena.bolinha.getObjSprite().getEscala()[1]-0.25f)
            };
            cena.bolinha.getObjSprite().setEscala(novoTamanho);
        }
        if(e.getKeyChar() == '5'){ // alterna a utilização do mouse
            cena.mouseHabilitado = !cena.mouseHabilitado;
        }

        // movimenta a textura
        if(e.getKeyCode() == 149){ // seta esquerda
            float[] novoOfsetTextura = {
                cena.bolinha.getObjSprite().getTexturaOfset()[0]-0.9f,
                cena.bolinha.getObjSprite().getTexturaOfset()[1]
            };
            cena.bolinha.getObjSprite().setTexturaOfset(novoOfsetTextura);
            System.out.println("textura offset: "+cena.bolinha.getObjSprite().getTexturaOfset()[0]);
        }
        if(e.getKeyCode() == 150){ // seta cima
            float[] novoOfsetTextura = {
                cena.bolinha.getObjSprite().getTexturaOfset()[0],
                cena.bolinha.getObjSprite().getTexturaOfset()[1]+0.25f
            };
            cena.bolinha.getObjSprite().setTexturaOfset(novoOfsetTextura);
            System.out.println("textura offset: "+cena.bolinha.getObjSprite().getTexturaOfset()[0]);
        }
        if(e.getKeyCode() == 151){ // seta direita
            float[] novoOfsetTextura = {
                cena.bolinha.getObjSprite().getTexturaOfset()[0]+0.9f,
                cena.bolinha.getObjSprite().getTexturaOfset()[1]
            };
            cena.bolinha.getObjSprite().setTexturaOfset(novoOfsetTextura);
            System.out.println("textura offset: "+cena.bolinha.getObjSprite().getTexturaOfset()[0]);
        }
        if(e.getKeyCode() == 152){ // seta baixo
            float[] novoOfsetTextura = {
                cena.bolinha.getObjSprite().getTexturaOfset()[0],
                cena.bolinha.getObjSprite().getTexturaOfset()[1]-0.25f
            };
            cena.bolinha.getObjSprite().setTexturaOfset(novoOfsetTextura);
            System.out.println("textura offset: "+cena.bolinha.getObjSprite().getTexturaOfset()[0]);
        }

        // TODO |MELHORAR MOVIMENTAÇÃO DA BARRA (TA TRAVANDO DPOIS DE ANDAR UM POUCO)
        // TODO |possível motivo: o código não é chamado todo frame, ele fica esperando um input do jogador
        if (!cena.mouseHabilitado) {
            if (e.getKeyChar() == 'a') { // mexe a barra pra esquerda
                cena.jogador.getObjSprite().setMovendo(true);
                cena.jogador.getObjSprite().setDirecao(Direcao.ESQUERDA);

            } else if (e.getKeyChar() == 'd') { // mexe a barra pra direita
                cena.jogador.getObjSprite().setMovendo(true);
                cena.jogador.getObjSprite().setDirecao(Direcao.DIREITA);
            }
//            cena.nPosX = (cena.jogador.getObjSprite().getIntervaloEsquerda()[0][0])
//                    +cena.jogador.getObjSprite().getVelx();
//
//            cena.nPosX2 = (cena.jogador.getObjSprite().getIntervaloDireita()[0][1])
//                    +cena.jogador.getObjSprite().getVelx();
        }
        if(e.getKeyChar() == 'c'){ // diminui a transparência
            cena.bolinha.getObjSprite().setAlfa(cena.bolinha.getObjSprite().getAlfa()-01f);
        } // mexe a barra pra direita

        if(e.getKeyChar() == 'v'){ // aumenta a transparência
            cena.bolinha.getObjSprite().setAlfa(cena.bolinha.getObjSprite().getAlfa()+0.1f);
        }
        if(e.getKeyChar() == 'x'){ // aumenta a transparência
            cena.jogador.setPausado(!cena.jogador.isPausado());
        }
        if(e.getKeyChar() == 'p'){ // inicia o jogo
            cena.jogador.setFase(1);
            cena.iter_telas = cena.jogador.getFase();
        }
        if(e.getKeyChar() == 's'){ // aumenta a transparência
            cena.jogador.setFase(cena.jogador.getFase()+1);
            cena.iter_telas = cena.jogador.getFase();
        }
        if(e.getKeyChar() == 'm'){
            cena.AlternarSom();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!cena.mouseHabilitado) {
            if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') cena.jogador.getObjSprite().setMovendo(false);
        }
    }
}
