package Model;

import Cena.Direcao;
import objetos.CoracaoSprite;
import objetos.QuadradoSprite;

public class Jogador {

    // atributos gerais
    protected String nome = "undefined";
    protected String senha;
    protected int vidas=3,vidasMin=0,vidasMax=3;
    protected int pontos = 0, pontosMin=1, pontosMax=50000;
    protected int fase = 0;
    protected QuadradoSprite objSprite;
    protected String skin;

    // vars de controle
    protected boolean jogando=false, pausado=false, morto=false, cursed=false, ganhou=false;
    protected boolean ganhouFase1=false, ganhouFase2=false,ganhouFase3=false;
    Direcao direcaoAtual;

    // getters e setters
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public int getVidas() {return vidas;}
    public void setVidas(int vidas) {this.vidas = vidas;}

    public int getVidasMin() {return vidasMin;}
    public void setVidasMin(int vidasMin) {this.vidasMin = vidasMin;}

    public int getVidasMax() {return vidasMax;}
    public void setVidasMax(int vidasMax) {this.vidasMax = vidasMax;}

    public int getPontos() {return pontos;}
    public void setPontos(int pontos) {this.pontos = pontos;}

    public int getPontosMin() {return pontosMin;}
    public void setPontosMin(int pontosMin) {this.pontosMin = pontosMin;}

    public int getPontosMax() {return pontosMax;}
    public void setPontosMax(int pontosMax) {this.pontosMax = pontosMax;}

    public int getFase() {return fase;}
    public void setFase(int fase) {this.fase = fase;}

    public QuadradoSprite getObjSprite() {return objSprite;}
    public void setObjSprite(QuadradoSprite objSprite) {this.objSprite = objSprite;}

    public String getSkin() {return skin;}
    public void setSkin(String skin) {this.skin = skin;}

    public boolean isJogando() {return jogando;}
    public void setJogando(boolean jogando) {this.jogando = jogando;}

    public boolean isPausado() {return pausado;}
    public void setPausado(boolean pausado) {this.pausado = pausado;}

    public boolean isMorto() {return morto;}
    public void setMorto(boolean morto) {this.morto = morto;}

    public boolean isCursed() {return cursed;}
    public void setCursed(boolean cursed) {this.cursed = cursed;}

    public boolean isGanhou() {return ganhou;}
    public void setGanhou(boolean ganhou) {this.ganhou = ganhou;}

    public Direcao getDirecaoAtual() {return direcaoAtual;}
    public void setDirecaoAtual(Direcao direcaoAtual) {this.direcaoAtual = direcaoAtual;}

    // construtor
    public Jogador(String nome) {this.nome = nome;}

    public Jogador(String nome, String senha, int vidas,
       int pontos, int fase, QuadradoSprite objSprite, String skin) {
        this.nome = nome;
        this.senha = senha;
        this.vidas = vidas;
        this.pontos = pontos;
        this.fase = fase;
        this.objSprite = objSprite;
        this.skin = skin;
        this.objSprite.setImageSrc(skin);
    }


    // métodos
    public boolean salvar(){ // TODO implementar com banco de dados
        return  true;
    }

    public boolean carregar(){ // TODO implementar com banco de dados
        return  true;
    }

    public void comecar(){
        morto = false;
        pausado = false;
        jogando = true;
    }

    public void recomecar(){
        pontos = 1;
        morto = false;
        pausado = false;
        jogando = true;
        vidas = vidasMax;
    }

    public void perderVida(int qntVidas){
        if ((vidas - qntVidas) <= vidasMin) {
            vidas = vidasMin;
            morto = true;
            pausado = false;
            //chamar a tela de perdeu
        } else {
            vidas -= qntVidas;
        }
    }

    public void ganharVida(int qntVidas){
        if ((vidas + qntVidas) >= vidasMax) {
            vidas = vidasMax;
        } else {vidas -= qntVidas;}
    }

    public void perderPontos(int qntPontos){
        if (pontos-qntPontos <= pontosMin){
            pontos = pontosMin;
        } else{pontos -=qntPontos;}
    }

    public void ganharPontos(int qntPontos){
        if (pontos+qntPontos >= pontosMax){
            pontos = pontosMax;
        } else{pontos += qntPontos;}
    }

    public void trocarSkin(String novaSkin){
        skin = novaSkin;
        objSprite.setImageSrc(skin);
    }

}
