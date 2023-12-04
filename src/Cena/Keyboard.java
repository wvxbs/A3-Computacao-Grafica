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
        if(e.getKeyChar() == 'b'){ // alterna a movimentação da bola palos
            //cena.ReproduzirEfeitoSonoro("coin");
            cena.bolinha.getObjSprite().setMovendo(true);
            cena.jogador.setJogando(true);
        }

        switch (cena.iter_telas){
            case 0: // menu
                if(e.getKeyChar() == 'p'){ // playk
                    cena.jogador.setFase(1);
                    cena.iter_telas = cena.jogador.getFase();
                }
                if(e.getKeyChar() == '1'){ // fase 1
                    cena.jogador.setFase(1);
                    cena.iter_telas = cena.jogador.getFase();
                }
                if(e.getKeyChar() == '2'){ // fase 2
                    cena.jogador.setFase(2);
                    cena.iter_telas = cena.jogador.getFase();
                }
                if(e.getKeyChar() == '3'){ // fase 3
                    cena.jogador.setFase(3);
                    cena.iter_telas = cena.jogador.getFase();
                }
                break;

            case 1: // fase 1
            case 2: // fase 2
            case 3: // fase 3
                if(e.getKeyChar() == 'x'){ // pause
                    cena.iter_telas = cena.jogador.getFase();
                    if (!cena.jogador.isPausado()){
                        cena.jogador.setPausado(true);
                        cena.iter_telas = 4; //se a tela não estiver pausada, chamamos a tela de pause
                    } else {
                        cena.jogador.setPausado(false);
                        //cena.jogador.setFase(faseAtual); //se a tela estiver pausada, voltamos pra fase ?? n consigo fazer voltar
                        cena.iter_telas = cena.jogador.getFase();
                    }
                }
                break;
            case 4: // pause
                if(e.getKeyChar() == 'x'){ // pause
                    cena.iter_telas = cena.jogador.getFase();
                    if (!cena.jogador.isPausado()){
                        cena.jogador.setPausado(true);
                        cena.iter_telas = 4; //se a tela não estiver pausada, chamamos a tela de pause
                    } else {
                        cena.jogador.setPausado(false);
                        //cena.jogador.setFase(faseAtual); //se a tela estiver pausada, voltamos pra fase ?? n consigo fazer voltar
                        cena.iter_telas = cena.jogador.getFase();
                    }
                }
                if(e.getKeyChar() == 'k'){ // voltar ao menu
                    cena.jogador.setFase(0);
                    cena.iter_telas = cena.jogador.getFase();
                    cena.jogador.recomecar();
                }

                break;
            case 5: // ganhou
            case 6: // perdeu
                if(e.getKeyChar() == 'k'){ // voltar ao menu
                    cena.jogador.setFase(0);
                    cena.iter_telas = cena.jogador.getFase();
                    cena.jogador.recomecar();
                }

                break;
            case 7: // passou
                if(e.getKeyChar() == 'c'){ // continue
                    if(cena.iter_telas == 7){
                        cena.jogador.setFase(cena.jogador.getFase()+1);
                        cena.iter_telas = cena.jogador.getFase();
                        cena.b1.setPosx(0);
                        cena.b1.setPosy(1);
                        cena.jogador.recomecar();
                    }
                }
                if(e.getKeyChar() == 'k'){ // voltar ao menu
                    cena.jogador.setFase(0);
                    cena.iter_telas = cena.jogador.getFase();
                    cena.jogador.recomecar();
                }

                break;

            default:
                break;
        }


        if (!cena.mouseHabilitado) {
            if (e.getKeyChar() == 'a') { // mexe a barra pra esquerda
                cena.jogador.getObjSprite().setMovendo(true);
                cena.jogador.getObjSprite().setDirecaoX(Direcao.ESQUERDA);

            } else if (e.getKeyChar() == 'd') { // mexe a barra pra direita
                cena.jogador.getObjSprite().setMovendo(true);
                cena.jogador.getObjSprite().setDirecaoX(Direcao.DIREITA);
            }
        }

        if(e.getKeyChar() == 'm'){
            cena.AlternarEfeitoSonoroEmLoop();
        }
        //0 menu
        //1 fase 1
        //2 fase 2
        //3 fase 3
        //4 pause
        //5 ganhou
        //6 perdeu
        //7 passou

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!cena.mouseHabilitado) {
            if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd') cena.jogador.getObjSprite().setMovendo(false);
        }
    }
}
