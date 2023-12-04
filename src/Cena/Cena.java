package Cena;

import Model.*;
import EfeitosSonoros.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import objetos.*;
import java.util.Random;

public class Cena implements GLEventListener {

    // atributos gerais
    private GL2 gl;
    private GLU glu;
    private GLUT glut;

    int largura=1920, altura=1080;

    //atributos de jogo
    public int pontos=0;
    public int iter_telas = 0;

    // atributos dos quads
    public QuadradoSprite q1, q3, q6, q7, q8, q9, q10, btn1, btn2, btn3, btn4, btn5;
    public CoracaoSprite cor1, cor2, cor3;
    public BolinhaSprite b1;
    public float ticksAtuais;
    public Jogador jogador;
    public Bolinha bolinha;
    public Background backgroundMenu,backgroundf1, backgroundf2, backgroundf3;
    public Obstaculo obstaculo1, obsFredy, obsFox, obsChica, obsBunny;
    public boolean mouseHabilitado = false;
    public float mouseX=0;
    public float mouseY=0;
    Random rand = new Random();

    // atributos textura
    public float limite;
    public int filtro = GL2.GL_LINEAR; ////GL_NEAREST ou GL_LINEAR
    public int wrap = GL2.GL_REPEAT;  //GL.GL_REPEAT ou GL.GL_CLAMP
    public int modo = GL2.GL_DECAL; ////GL.GL_MODULATE ou GL.GL_DECAL ou GL.GL_BLEND
    public static ReproduzirEfeitoSonoro reproduzirEfeitoSonoro;


    @Override
    public void init(GLAutoDrawable drawable) {
        // coisas opengl
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();

        limite = 1;

        gl.glEnable(GL2.GL_DEPTH_TEST);

        String face1 = "imagens/barra_guarda.png";
        String face3 = "imagens/BackgorundMenuFrederico.png";
        String face5 = "imagens/BolinhaChica.png";
        String face6 = "imagens/brick.jpg";

        String face7 = "imagens/bonnie.png";
        String face8 = "imagens/chica.png";
        String face9 = "imagens/freddy.png";
        String face10 = "imagens/foxy.png";


        String faceBtn1 = "imagens/btnPlay.png";
        String faceBtn2 = "imagens/btnContinue.png";
        String faceBtn3 = "imagens/btnExit.png";
        String faceBtn4 = "imagens/btnResume.png";
        String faceBtn5 = "imagens/btnLevel1.png";

        String[] faceCor = {"imagens/coracao.png", "imagens/coracao_cinza.png"};

        float[] tamq1 = {30,5}; // tamanho da barra
        float[] tamb1 = {3f,3f}; // tamanho do raio da bolinha

        float[] tamq3 = {largura/5,altura/5}; // tamanho do background

        float[] tamq6 = {20,20}; // tamanho do obstáculo da fase 2
        float[] tamq7 = {10,10}; // tamanho do obstáculo da fase 2
        float[] tambtn1 = {25,15}; // tamanho do botão 1
        float[] tamcor = {10,10};

        float[] corq1 = {0,0,0}; // cor do quadrado 1(tnt faz se tiver textura aplicada(aparentemente faz ss))

        q1 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq1,corq1,face1,false); // barra
        b1 = new BolinhaSprite(1,filtro,wrap,modo, limite,tamb1,tamb1[0],corq1,face5,false); // bolinha
        q3 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq3,corq1,face3,false); // background
        q6 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq6,corq1,face6,false); // obstáculo 1

        q7 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq7,corq1,face1,false); // obstáculo 1
        q8 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq7,corq1,face1,false); // obstáculo 1
        q9 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq7,corq1,face1,false); // obstáculo 1
        q10 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq7,corq1,face1,false); // obstáculo 1

        btn1 = new BotaoSprite(1,filtro,wrap,modo,limite,tambtn1,corq1,faceBtn1,false);
        btn2 = new BotaoSprite(1,filtro,wrap,modo,limite,tambtn1,corq1,faceBtn2,false);
        btn3 = new BotaoSprite(1,filtro,wrap,modo,limite,tambtn1,corq1,faceBtn3,false);
        btn4 = new BotaoSprite(1,filtro,wrap,modo,limite,tambtn1,corq1,faceBtn4,false);
        btn5 = new BotaoSprite(1,filtro,wrap,modo,limite,tambtn1,corq1,faceBtn5,false);
        //btn2 = new BotaoSprite(1,filtro,wrap,modo,limite,tambtn1,corq1,faceBtn6,false);

        cor1 = new CoracaoSprite(1,filtro,wrap,modo,limite,tamcor,corq1,faceCor,false);
        cor2 = new CoracaoSprite(1,filtro,wrap,modo,limite,tamcor,corq1,faceCor,false);
        cor3 = new CoracaoSprite(1,filtro,wrap,modo,limite,tamcor,corq1,faceCor,false);



        // configurando q1 (barra)
        q1.setVelx(1.5f); // definindo a velocidade x do quadrado 1
        q1.setPosy(-60);

        // configurando q2 (bola palos)

        // configurando q3 (background)
        q3.setPosz(-0.1f);

        // configurando q6 (obstáculo)
        q6.setPosx(0);
        q6.setPosy(15);

        // configurando q7 (obstáculo)
        q7.setPosx(0);
        q7.setPosy(15);
        q7.setVelx(rand.nextFloat(0.8f,1.2f));

        // configurando q8 (obstáculo)
        q8.setPosx(-20);
        q8.setPosy(30);
        q8.setVelx(rand.nextFloat(0.8f,1.2f));

        // configurando q9 (obstáculo)
        q9.setPosx(20);
        q9.setPosy(45);
        q9.setVelx(rand.nextFloat(0.8f,1.2f));

        // configurando q10 (obstáculo)
        q10.setPosx(40);
        q10.setPosy(60);
        q10.setVelx(rand.nextFloat(0.8f,1.2f));

        // configurando b1 (bolinha)
        b1.setVelx(2);
        b1.setVely(2);

        // classes de controle
        jogador = new Jogador("Bruno","12345",3,0,1,q1,face1);
        bolinha = new Bolinha(b1,face5);
        backgroundMenu = new Background(q3,face3);

        // obstáculo fase 2
        obstaculo1 = new Obstaculo(q6,face1);

        // obstáculos fase 3
        obsBunny = new Obstaculo(q7,face7);
        obsChica = new Obstaculo(q8,face8);
        obsFredy = new Obstaculo(q9,face9);
        obsFox = new Obstaculo(q10,face10);

        // configurando btn 1
        btn1.setPosx(0);
        btn1.setPosy(10);
        btn1.setPosz(-0.05f);

        // configurando btn 2
        btn2.setPosx(0);
        btn2.setPosy(-20);
        btn2.setPosz(-0.05f);

        btn3.setPosx(0);
        btn3.setPosy(-30);
        btn3.setPosz(-0.05f);

        // configurando coração
        cor1.setPosx(-90);
        cor1.setPosy(60);

        cor2.setPosx(-80);
        cor2.setPosy(60);

        cor3.setPosx(-70);
        cor3.setPosy(60);

        // configurando som
        reproduzirEfeitoSonoro = new ReproduzirEfeitoSonoro();
        ReproduzirEfeitoSonoroEmLoop("Rave On");

        mouseHabilitado = true;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        ticksAtuais += 0.01f;
        // 0-menu, 1-fase1, 2-fase2, 3-fase3, 4-pause, 5-Ganhou, 6-Perdeu
        switch (iter_telas){
            case 0: // menu
                menu(drawable);
                break;
            case 1: // fase 1
                fase1(drawable);
                break;
            case 2: // fase 2
                fase2(drawable);
                break;
            case 3: // fase 3
                fase3(drawable);
                break;
            case 4: // pause
                pause(drawable);
                break;
            case 5: // ganhou
                ganhou(drawable);
                break;
            case 6: // perdeu
                perdeu(drawable);
                break;
            case 7: // passou
                passou(drawable);
                break;

            default:
                break;
        }


    }

    // telas
    public void pause(GLAutoDrawable drawable){ // TODO fazer pause bunitin
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo

        // background
        backgroundMenu.getObjSprite().desenhar(gl);

        // botões
        //btn2.desenhar(gl);
        //btn3.desenhar(gl);

        // textos do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -10,60, "PAUSADO",24);
        desenhaTexto(gl, -10,30, "Pressione X para continuar",18);
        desenhaTexto(gl, -10,20, "Pressione K para voltar ao menu",18);


        gl.glFlush();
    }

    public void menu(GLAutoDrawable drawable){ // TODO fazer mouse bunitin
        gl = drawable.getGL().getGL2();


        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        backgroundMenu.getObjSprite().desenhar(gl);
        //btn1.desenhar(gl);
        //btn2.desenhar(gl);

        // texto do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -10,60, "BEM-VINDO!",24);
        desenhaTexto(gl, -10,30, "Pressione P para iniciar",18);
        desenhaTexto(gl, -10,-10, "Pressione K para voltar ao menu",18);
        desenhaTexto(gl, -10,20, "Pressione 1 para ir para a Fase 1",18);
        desenhaTexto(gl, -10,10, "Pressione 2 para ir para a Fase 2",18);
        desenhaTexto(gl, -10,0, "Pressione 3 para ir para a Fase 3",18);


        gl.glFlush();
    }

    public void ganhou(GLAutoDrawable drawable){ // TODO ganhou todo bunitin
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        backgroundMenu.getObjSprite().desenhar(gl);
        btn3.desenhar(gl);

        // texto do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -10,60, "GANHOU!!",24);
        desenhaTexto(gl, -10,30, "Pressione K para voltar ao menu",18);


        gl.glFlush();
    }

    public void perdeu(GLAutoDrawable drawable){ // TODO perdeu pause bunitin
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        backgroundMenu.getObjSprite().desenhar(gl);

        btn2.desenhar(gl);
        btn3.desenhar(gl);

        // texto do menu
        gl.glColor3f(0f, 1f, 0f);
        desenhaTexto(gl, -10,60, "VOCE PERDEU KKKK",24);
        desenhaTexto(gl, -10,30, "Pressione K para voltar ao menu",18);


        gl.glFlush();
    }

    public void passou(GLAutoDrawable drawable){ // TODO fazer passou bunitin
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        backgroundMenu.getObjSprite().desenhar(gl);
        btn2.desenhar(gl);
        btn3.desenhar(gl);

        // texto do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -10,60, "VOCÊ PASSOU A FASE "+jogador.getFase(),24);
        desenhaTexto(gl, -10,30, "Pressione C para continuar",18);
        desenhaTexto(gl, -10,20, "Pressione K para voltar ao menu",18);


        gl.glFlush();
    }

    // fases
    public void fase1(GLAutoDrawable drawable){
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        jogador.getObjSprite().desenhar(gl);
        bolinha.getObjSprite().desenhar(gl);
        backgroundMenu.trocarSkin("imagens/BackgroundFase.png");
        backgroundMenu.getObjSprite().desenhar(gl);

        cor1.desenhar(gl);
        cor2.desenhar(gl);
        cor3.desenhar(gl);

        desenhaTexto(gl, -10,50, "Pontos: " + jogador.getPontos(),18);
        // movimentar tudo q precisa

        if (!jogador.isPausado()){
            // TODO adicionar uma classe para manipular eventos do jogo (colisão, pontos, etc)

            // movimentação q2
            colisaoBolinhaBordas(bolinha.getObjSprite()); // detectando colisões
            colisaoBolinhaBarra(bolinha.getObjSprite(),jogador.getObjSprite());
            if (bolinha.getObjSprite().isMovendo()) bolinha.getObjSprite().mover(); // chamando o método de movimento

            // movimentação q1
            if (jogador.getObjSprite().isMovendo()) {
                switch (jogador.getObjSprite().getDirecaoX()) {
                    case DIREITA:
                        jogador.getObjSprite().setVelx(+1.5f);
                        break;

                    case ESQUERDA:
                        jogador.getObjSprite().setVelx(-1.5f);
                        break;

                    default:
                        jogador.getObjSprite().setVelx(0);
                        break;
                }
                colisaoBarraBordas(jogador.getObjSprite());

                if (mouseHabilitado){
                    float intervDx1 = jogador.getObjSprite().getIntervaloEsquerda()[0][0]+
                            (jogador.getObjSprite().getTamanho()[0]/2)-1;

                    float intervDx2 = jogador.getObjSprite().getIntervaloDireita()[0][1]-
                            (jogador.getObjSprite().getTamanho()[0]/2)+1;

                    if (!(mouseX > intervDx1 && mouseX < intervDx2))
                    {jogador.getObjSprite().mover();}
                    else {jogador.getObjSprite().setMovendo(false);}
                }
                else {
                    jogador.getObjSprite().mover();
                }
            }

        }

        // textos de debug
        gl.glColor3f(1,1,1);
        desenhaTexto(gl,0,70,"FASE: "+jogador.getFase());

        gl.glFlush();

        if (jogador.getPontos() >= 100){
            ReproduzirEfeitoSonoro("jet-set-radio-success");
            iter_telas = 7;
        }
    }

    public void fase2(GLAutoDrawable drawable){
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        jogador.getObjSprite().desenhar(gl);
        bolinha.getObjSprite().desenhar(gl);
        backgroundMenu.trocarSkin("imagens/BackgroundFase2.png");
        backgroundMenu.getObjSprite().desenhar(gl);
        obstaculo1.getObjSprite().desenhar(gl);

        cor1.desenhar(gl);
        cor2.desenhar(gl);
        cor3.desenhar(gl);

        desenhaTexto(gl, -10,50, "Pontos: " + jogador.getPontos(),18);
        // movimentar tudo q precisa

        if (!jogador.isPausado()){

            // movimentação q2

            colisaoBolinhaBordas(bolinha.getObjSprite() ); // detectando colisões
            colisaoBolinhaBarra(bolinha.getObjSprite(),jogador.getObjSprite());
            colisaoBolinhaObstaculo(bolinha.getObjSprite(), obstaculo1.getObjSprite());

            if (bolinha.getObjSprite().isMovendo()) bolinha.getObjSprite().mover(); // chamando o método de movimento

            gl.glColor4f(1.0f, 1.0f, 1.0f, bolinha.getObjSprite().getAlfa());
            desenhaTexto(gl, (int) (bolinha.getObjSprite().getIntervaloEsquerda()[0][0]),
                    (int) bolinha.getObjSprite().getIntervaloCima()[1][1] + 10,
                    "alfa bolinha: " + bolinha.getObjSprite().getAlfa());

            // movimentação q1
            if (jogador.getObjSprite().isMovendo()) {
                switch (jogador.getObjSprite().getDirecaoX()) {
                    case DIREITA:
                        jogador.getObjSprite().setVelx(+1);
                        break;

                    case ESQUERDA:
                        jogador.getObjSprite().setVelx(-1);
                        break;

                    default:
                        jogador.getObjSprite().setVelx(0);
                        break;
                }
//                switch (jogador.getObjSprite().getDirecaoY()) {
//                    case CIMA:
//                        jogador.getObjSprite().setVely(+1);
//                        break;
//
//                    case BAIXO:
//                        jogador.getObjSprite().setVely(-1);
//                        break;
//
//                    default:
//                        jogador.getObjSprite().setVely(0);
//                        break;
//                }
                colisaoBarraBordas(jogador.getObjSprite());

                if (mouseHabilitado){
                    float intervDx1 = jogador.getObjSprite().getIntervaloEsquerda()[0][0]+
                            (jogador.getObjSprite().getTamanho()[0]/2)-1;
                    float intervDx2 = jogador.getObjSprite().getIntervaloDireita()[0][1]-
                            (jogador.getObjSprite().getTamanho()[0]/2)+1;
                    if (!(mouseX > intervDx1 && mouseX < intervDx2))
                    {jogador.getObjSprite().mover();}
                    else {jogador.getObjSprite().setMovendo(false);}
                }
                else {
//                    if (!(nPosX > jogador.getObjSprite().getIntervaloEsquerda()[0][0] &&
//                            nPosX2 < jogador.getObjSprite().getIntervaloDireita()[0][1] ))
//                    {jogador.getObjSprite().mover();}
//                    else {jogador.getObjSprite().setMovendo(false);}
                    jogador.getObjSprite().mover();
                }
            }

        }

        gl.glFlush();

        if (jogador.getPontos() >= 200){
            ReproduzirEfeitoSonoro("jet-set-radio-success");
            iter_telas = 7;
        }
    }

    public void fase3(GLAutoDrawable drawable){
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        jogador.getObjSprite().desenhar(gl);
        bolinha.getObjSprite().desenhar(gl);
        backgroundMenu.trocarSkin("imagens/BackgroundFase2.png");
        backgroundMenu.getObjSprite().desenhar(gl);
        obsFox.getObjSprite().desenhar(gl);
        obsFredy.getObjSprite().desenhar(gl);
        obsChica.getObjSprite().desenhar(gl);
        obsBunny.getObjSprite().desenhar(gl);

        cor1.desenhar(gl);
        cor2.desenhar(gl);
        cor3.desenhar(gl);

        desenhaTexto(gl, -10,50, "Pontos: " + jogador.getPontos(),18);
        // movimentar tudo q precisa

        if (!jogador.isPausado()){// TODO adicionar uma classe para manipular eventos do jogo (colisão, pontos, etc)

            // movimentação q2
            colisaoBolinhaBordas(bolinha.getObjSprite() ); // detectando colisões
            colisaoBolinhaBarra(bolinha.getObjSprite(),jogador.getObjSprite());

            colisaoBolinhaObstaculo(bolinha.getObjSprite(),obsBunny.getObjSprite());
            colisaoBolinhaObstaculo(bolinha.getObjSprite(),obsFredy.getObjSprite());
            colisaoBolinhaObstaculo(bolinha.getObjSprite(),obsChica.getObjSprite());
            colisaoBolinhaObstaculo(bolinha.getObjSprite(),obsFox.getObjSprite());

            if (bolinha.getObjSprite().isMovendo()) bolinha.getObjSprite().mover(); // chamando o método de movimento

            // movimentação obstáculos
            colisaoObstaculosBordas(obsBunny.getObjSprite());
            colisaoObstaculosBordas(obsFredy.getObjSprite());
            colisaoObstaculosBordas(obsChica.getObjSprite());
            colisaoObstaculosBordas(obsFox.getObjSprite());

            obsBunny.getObjSprite().mover();
            obsFredy.getObjSprite().mover();
            obsChica.getObjSprite().mover();
            obsFox.getObjSprite().mover();

            // movimentação q1
            if (jogador.getObjSprite().isMovendo()) {
                switch (jogador.getObjSprite().getDirecaoX()) {
                    case DIREITA:
                        jogador.getObjSprite().setVelx(+1);
                        break;

                    case ESQUERDA:
                        jogador.getObjSprite().setVelx(-1);
                        break;

                    default:
                        jogador.getObjSprite().setVelx(0);
                        break;
                }
                colisaoBarraBordas(jogador.getObjSprite());

                if (mouseHabilitado){
                    float intervDx1 = jogador.getObjSprite().getIntervaloEsquerda()[0][0]+
                            (jogador.getObjSprite().getTamanho()[0]/2)-1;
                    float intervDx2 = jogador.getObjSprite().getIntervaloDireita()[0][1]-
                            (jogador.getObjSprite().getTamanho()[0]/2)+1;
                    if (!(mouseX > intervDx1 && mouseX < intervDx2))
                    {jogador.getObjSprite().mover();}
                    else {jogador.getObjSprite().setMovendo(false);}
                }
                else {
                    jogador.getObjSprite().mover();
                }
            }

        }

        gl.glColor3f(1, 0, 0);
        desenhaTexto(gl, 0, 90, "Pontos: " + pontos);

        gl.glFlush();

        if (jogador.getPontos() >= 300){
            ReproduzirEfeitoSonoro("jet-set-radio-success");
            iter_telas = 5;
        }
    }

    // métodos
    public void desenhaTexto(GL2 gl, int x, int y, String frase) {
        glut = new GLUT(); //objeto da biblioteca glut
        gl.glRasterPos2f(x, y);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, frase);
    }
    public void desenhaTexto(GL2 gl, int x, int y, String frase,int tamFonte) {
        glut = new GLUT(); //objeto da biblioteca glut
        gl.glRasterPos2f(x, y);
        switch (tamFonte){
            case 12:
                glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, frase);
                break;
            case 18:
                glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18, frase);
                break;
            case 24:
                glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, frase);
                break;
            default:
                glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, frase);
                break;
        }
    }

    // colisões

    public void colisaoBolinhaBordas(QuadradoSprite bolinha){
        // definindo as bordas que interagirão com o quadrado q2
        float[][][] bordas = {
                {{-largura, largura}, {altura, altura}}, // bordas cima ((x0-x1), (y0-y1))
                {{-largura, largura}, {-altura, -altura}}, // bordas baixo ((x0-x1), (y0-y1))
                {{largura, largura}, {-altura, altura}}, // bordas direita ((x0-x1), (y0-y1))
                {{-largura, -largura}, {-altura, altura}} // bordas esquerda ((x0-x1), (y0-y1))

        };

        // criando vars de controle que receberão a resposta do teste de colisão
        boolean colisaoQ2cima, colisaoQ2baixo, colisaoQ2direita, colisaoQ2esquerda;

        // obtendo resposta se houve colisão ou não
        colisaoQ2cima = bolinha.isColiding(bordas[0][0], bordas[0][1]);
        colisaoQ2baixo = bolinha.isColiding(bordas[1][0], bordas[1][1]);
        colisaoQ2direita = bolinha.isColiding(bordas[2][0], bordas[2][1]);;
        colisaoQ2esquerda = bolinha.isColiding(bordas[3][0], bordas[3][1]);

        // definindo interação caso às condições sejam atendidas
        if (colisaoQ2cima || colisaoQ2baixo){bolinha.setVely(bolinha.getVely()*-1);}
        if (colisaoQ2direita || colisaoQ2esquerda){bolinha.setVelx(bolinha.getVelx()*-1);}
        if (colisaoQ2baixo){
            //pontos = (pontos <= 0 ) ? 0 : pontos -5;
            //jogador.perderPontos(5);
            if (jogador.getFase() == 1){
                jogador.perderPontos(50);
            } else if (jogador.getFase() == 2){
                jogador.perderPontos(75);
            } else if (jogador.getFase() == 3) {
                jogador.perderPontos(pontos);
            }

            jogador.perderVida(1);
            switch (jogador.getVidas()){
                case 0:
                    ReproduzirEfeitoSonoro("health");
                    cor1.setModoCoracao(1);
                    cor2.setModoCoracao(1);
                    cor3.setModoCoracao(1);
                    break;
                case 1:
                    ReproduzirEfeitoSonoro("health");
                    cor1.setModoCoracao(0);
                    cor2.setModoCoracao(1);
                    cor3.setModoCoracao(1);
                    break;
                case 2:
                    ReproduzirEfeitoSonoro("health");
                    cor1.setModoCoracao(0);
                    cor2.setModoCoracao(0);
                    cor3.setModoCoracao(1);
                    break;
                case 3:
                    cor1.setModoCoracao(0);
                    cor2.setModoCoracao(0);
                    cor3.setModoCoracao(0);
                    break;
            };
        }
    }

    public void colisaoBarraBordas(QuadradoSprite barra){
        // definindo as bordas que interagirão com o quadrado q2
        float[][][] bordas = {
                {{largura, largura}, {-altura, altura}}, // bordas direita ((x0-x1), (y0-y1))
                {{-largura, -largura}, {-altura, altura}} // bordas esquerda ((x0-x1), (y0-y1))

        };

        // criando vars de controle que receberão a resposta do teste de colisão
        boolean colisaoQ1direita, colisaoQ1esquerda;

        // obtendo resposta se houve colisão ou não
        colisaoQ1direita = barra.isColiding(bordas[0][0], bordas[0][1]);
        colisaoQ1esquerda = barra.isColiding(bordas[1][0], bordas[1][1]);

        // definindo interação caso às condições sejam atendidas
        if (colisaoQ1direita && barra.getVelx() == 1) barra.setVelx(0);
        if (colisaoQ1esquerda && barra.getVelx() == -1) barra.setVelx(0);
    }

    public void colisaoBolinhaBarra(QuadradoSprite bolinha, QuadradoSprite barra){


        // obtendo feedback e armazenando
        boolean colisaoYcima,colisaoXdireita, colisaoXesquerda;
        colisaoYcima = bolinha.isColiding(barra.getIntervaloCima()[0],barra.getIntervaloCima()[1]);
        //|| bolinha.isColiding(barra.getIntervaloBaixo()[0],barra.getIntervaloBaixo()[1]);

        colisaoXdireita = bolinha.isColiding(barra.getIntervaloDireita()[0],barra.getIntervaloDireita()[1]);
        colisaoXesquerda = bolinha.isColiding(barra.getIntervaloEsquerda()[0],barra.getIntervaloEsquerda()[1]);

        // definindo interações
        if (colisaoYcima && bolinha.getDirecaoY() == Direcao.BAIXO){
            bolinha.setVely(rand.nextFloat(0.9f, 1.7f));
            //jogador.ganharPontos(10);
            if (jogador.getFase() == 1){
                jogador.ganharPontos(20);
                ReproduzirEfeitoSonoro("coin");
            } else if (jogador.getFase() == 2){
                jogador.ganharPontos(10);
                ReproduzirEfeitoSonoro("coin");
            } else if (jogador.getFase() == 3) {
                jogador.perderPontos(5);
                ReproduzirEfeitoSonoro("health");
            }
        }

        if (colisaoXdireita && bolinha.getDirecaoX() == Direcao.ESQUERDA){
            bolinha.setVelx(bolinha.getVelx()*-1);
        }

        if (colisaoXesquerda && bolinha.getDirecaoX() == Direcao.DIREITA){
            bolinha.setVelx(bolinha.getVelx()*-1);
        }

    }

    public void colisaoBolinhaObstaculo(QuadradoSprite bolinha, QuadradoSprite obstaculo){

        // obtendo feedback e armazenando
        boolean colisaoYcima, colisaoYbaixo,colisaoXdireita,colisaoXesquerda;


        colisaoYcima = bolinha.isColiding(obstaculo.getIntervaloCima()[0],obstaculo.getIntervaloCima()[1]);
        colisaoYbaixo = bolinha.isColiding(obstaculo.getIntervaloBaixo()[0],obstaculo.getIntervaloBaixo()[1]);

        colisaoXdireita = bolinha.isColiding(obstaculo.getIntervaloDireita()[0],obstaculo.getIntervaloDireita()[1]);
        colisaoXesquerda = bolinha.isColiding(obstaculo.getIntervaloEsquerda()[0],obstaculo.getIntervaloEsquerda()[1]);

        // definindo interações
        if (colisaoYcima && bolinha.getDirecaoY() == Direcao.BAIXO){
            bolinha.setVely(bolinha.getVely()*-1);jogador.perderPontos(20);
        }
        if (colisaoYbaixo && bolinha.getDirecaoY() == Direcao.CIMA){
            bolinha.setVely(bolinha.getVely()*-1);jogador.perderPontos(20);
        }
        if (colisaoXdireita && bolinha.getDirecaoX() == Direcao.ESQUERDA){
            bolinha.setVelx(bolinha.getVelx()*-1);jogador.perderPontos(20);
        }
        if (colisaoXesquerda && bolinha.getDirecaoX() == Direcao.DIREITA){
            bolinha.setVelx(bolinha.getVelx()*-1);jogador.perderPontos(20);
        }


    }

    public void colisaoObstaculosBordas(QuadradoSprite obstaculo){
        // definindo as bordas que interagirão com o quadrado q2
        float[][][] bordas = {
                {{largura, largura}, {-altura, altura}}, // bordas direita ((x0-x1), (y0-y1))
                {{-largura, -largura}, {-altura, altura}} // bordas esquerda ((x0-x1), (y0-y1))

        };

        // criando vars de controle que receberão a resposta do teste de colisão
        boolean colisaoDireita, colisaoEsquerda;

        // obtendo resposta se houve colisão ou não
        colisaoDireita = obstaculo.isColiding(bordas[0][0], bordas[0][1]);
        colisaoEsquerda = obstaculo.isColiding(bordas[1][0], bordas[1][1]);

        //feedback

        // definindo interação caso às condições sejam atendidas
        if (colisaoDireita && obstaculo.getVelx() >0)
            obstaculo.setVelx(-rand.nextFloat(0.6f, 1.4f));
        if (colisaoEsquerda && obstaculo.getVelx() <0)
            obstaculo.setVelx(rand.nextFloat(0.6f, 1.4f));
    }

    // audio
    public void ReproduzirEfeitoSonoroEmLoop(String nome) {
        reproduzirEfeitoSonoro.DefinirArquivo(nome);
        reproduzirEfeitoSonoro.Reproduzir();
        reproduzirEfeitoSonoro.Loop();
    }

    public void ReproduzirEfeitoSonoro(String nome) {
        reproduzirEfeitoSonoro.DefinirArquivo(nome);
        reproduzirEfeitoSonoro.Reproduzir();
    }

    public void PausarEfeitoSonoro() {
        reproduzirEfeitoSonoro.Pausar();
    }

    public void AlternarEfeitoSonoroEmLoop() {
        if(reproduzirEfeitoSonoro.isTocando()){
            reproduzirEfeitoSonoro.Pausar();}
        else {
            ReproduzirEfeitoSonoroEmLoop(reproduzirEfeitoSonoro.getNomeDoArquivoSelecionado());}

    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //obtem o contexto grafico Opengl
        gl = drawable.getGL().getGL2();

        //evita a divisão por zero
        //if(height == 0) height = 1;
        //calcula a proporção da janela (aspect ratio) da nova janela
        //float aspect = (float) width / height;
        largura = width / 10;
        altura = height / 10;

        //seta o viewport para abranger a janela inteira
        //gl.glViewport(0, 0, width, height);

        //ativa a matriz de projeção
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity(); //lê a matriz identidade

        //Projeção ortogonal
        gl.glOrtho(-largura, largura, -altura, altura, -100, 100);

        //ativa a matriz de modelagem
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        //gl.glLoadIdentity(); //lê a matriz identidade
        System.out.println("Reshape: " + width + ", " + height);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

}
