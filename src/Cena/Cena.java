package Cena;

import ClassesJogo.*;
import EfeitosSonoros.*;

import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import objetos.*;

public class Cena implements GLEventListener {

    // atributos gerais
    private GL2 gl;
    private GLU glu;
    private GLUT glut;

    int largura=128, altura=72;

    //atributos de jogo
    public int pontos=0;
    public int iter_telas = 0;

    // atributos dos quads
    public QuadradoSprite q1, q3, q6, btn1, btn2, cor;
    public BolinhaSprite b1;
    public Jogador jogador;
    public Bolinha bolinha;
    public Background background;
    public Obstaculo obstaculo;
    public boolean mouseHabilitado = false;
    public float mouseX=0;
    public float mouseY=0;

    // atributos textura
    public float limite;
    public int filtro = GL2.GL_LINEAR; ////GL_NEAREST ou GL_LINEAR
    public int wrap = GL2.GL_REPEAT;  //GL.GL_REPEAT ou GL.GL_CLAMP
    public int modo = GL2.GL_DECAL; ////GL.GL_MODULATE ou GL.GL_DECAL ou GL.GL_BLEND
    public static final String FACE2 = "imagens/palosInterrogacao1-1.png";
    public static final String FACE1 = "imagens/brick.jpg";
    public static final String FACE3 = "imagens/background.png";
    public static final String FACE4 = "imagens/ferreira1-1.png";
    public static final String FACE5 = "imagens/bolinha2.png";
    public static final String FACE6 = "imagens/palosInterrogacao1-1.png";
    public static final String FACEBTN1 = "imagens/rosa-claro.jpg";
    public static final String FACECOR = "imagens/coracao.png";
    public static ReproduzirEfeitoSonoro reproduzirEfeitoSonoro;


    @Override
    public void init(GLAutoDrawable drawable) {
        // coisas opengl
        GL2 gl = drawable.getGL().getGL2();
        glu = new GLU();

        limite = 1;

        gl.glEnable(GL2.GL_DEPTH_TEST);
        //gl.glEnable(GL.GL_BLEND);

        // criação de objetos que entrarão em cena

        float[] tamq1 = {30,5}; // tamanho da barra
        float[] tamb1 = {3f,3f}; // tamanho do raio da bolinha
        float[] tamq3 = {200,200}; // tamanho do background
        float[] tamq4 = {50,50}; // tamanho do quadrado 2
        float[] tamq6 = {20,20}; // tamanho do obstáculo da fase 2
        float[] tambtn1 = {30,10}; // tamanho do botão 1

        float[] corq1 = {0,0,0}; // cor do quadrado 1(tnt faz se tiver textura aplicada(aparentemente faz ss))

        q1 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq1,corq1,FACE1,false); // barra
        b1 = new BolinhaSprite(1,filtro,wrap,modo, limite,tamb1,tamb1[0],corq1,FACE5,false); // bolinha
        q3 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq3,corq1,FACE3,false); // background
        q6 = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq6,corq1,FACE6,false); // obstáculo

        btn1 = new BotaoSprite(1,filtro,wrap,modo,limite,tambtn1,corq1,FACEBTN1,false);
        btn2 = new BotaoSprite(1,filtro,wrap,modo,limite,tambtn1,corq1,FACEBTN1,false);
        cor = new QuadradoSprite(1,filtro,wrap,modo,limite,tamq6,corq1,FACECOR,false);

        // configurando q1 (barra)
        q1.setVelx(1.5f); // definindo a velocidade x do quadrado 1
        q1.setPosy(-60);

        // configurando q2 (bola palos)

        // configurando q3 (background)
        q3.setPosz(-0.1f);

        // configurando q6 (obstáculo)
        q6.setPosx(0);
        q6.setPosy(20);

        // configurando b1 (bolinha)
        b1.setVelx(2);
        b1.setVely(2);

        // classes de controle
        jogador = new Jogador("Bruno","12345",5,0,1,q1,FACE1);
        bolinha = new Bolinha(b1,FACE5);
        background = new Background(q3,FACE3);
        obstaculo = new Obstaculo(q6,FACE1);

        // configurando btn 1
        btn1.setPosx(0);
        btn1.setPosy(10);
        btn1.setPosz(-0.05f);

        // configurando btn 2
        btn2.setPosx(0);
        btn2.setPosy(-20);
        btn2.setPosz(-0.05f);

        // configurando btn cor?
        cor.setPosx(80);
        cor.setPosy(-80);

        // configurando som
//        String[] playlist = {"sons/faz-o-l-vinheta.wav", "sons/health.wav"};
        reproduzirEfeitoSonoro = new ReproduzirEfeitoSonoro();
        ReproduzirEfeitoSonoroEmLoop("Royal Days");

        mouseHabilitado = true;
    }

    @Override
    public void display(GLAutoDrawable drawable) {

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
        background.getObjSprite().desenhar(gl);

        // botões
        btn1.desenhar(gl);
        btn2.desenhar(gl);

        // textos do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -5,80, "PAUSADO",24);
        desenhaTexto(gl, -20,60, "Pressione X para continuar",18);
        desenhaTexto(gl, -20,0, "Pressione ??? para voltar ao menu",18);
        desenhaTexto(gl, -20,-90, "Pressione ESC para sair",18);
        desenhaTexto(gl, -10,40, "Pontos: " + pontos,18);


        gl.glFlush();
    }

    public void menu(GLAutoDrawable drawable){ // TODO fazer mouse bunitin
        gl = drawable.getGL().getGL2();


        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        background.getObjSprite().desenhar(gl);
        btn1.desenhar(gl);
        btn2.desenhar(gl);
        cor.desenhar(gl);

        // texto do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -5,80, "BEM-VINDO!",24);
        desenhaTexto(gl, -15,10, "Pressione P para iniciar o jogo",18); //sincronizar o texto com os botões
        desenhaTexto(gl, -20,-90, "Pressione ESC para sair",18); //sincronizar o texto com os botões
        desenhaTexto(gl, -10,40, "Pontos: " + pontos,18);


        gl.glFlush();
    }

    public void ganhou(GLAutoDrawable drawable){ // TODO ganhou todo bunitin
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        background.getObjSprite().desenhar(gl);
        btn1.desenhar(gl);
        btn2.desenhar(gl);

        // texto do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -5,80, "PAUSADO",24);
        desenhaTexto(gl, -20,60, "Pressione X para continuar",18);
        desenhaTexto(gl, -20,0, "Pressione ??? para voltar ao menu",18);
        desenhaTexto(gl, -20,-90, "Pressione ESC para sair",18);
        desenhaTexto(gl, -10,40, "Pontos: " + pontos,18);


        gl.glFlush();
    }

    public void perdeu(GLAutoDrawable drawable){ // TODO perdeu pause bunitin
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        background.getObjSprite().desenhar(gl);
        btn1.desenhar(gl);
        btn2.desenhar(gl);

        // texto do menu
        gl.glColor3f(0f, 1f, 0f);
        desenhaTexto(gl, -5,70, "VOCE PERDEU KKKK",24);
        desenhaTexto(gl, -20,60, "Pressione ??? para voltar ao menu",18);
        desenhaTexto(gl, -20,-90, "Pressione ESC para sair",18);
        desenhaTexto(gl, -10,40, "Pontos Finais: " + pontos,18);


        gl.glFlush();
    }

    public void passou(GLAutoDrawable drawable){ // TODO fazer passou bunitin
        gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0); // Defines the window color in RGB
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reads the matrix identity

        // cena

        // desenhar tudo
        background.getObjSprite().desenhar(gl);
        btn1.desenhar(gl);
        btn2.desenhar(gl);

        // texto do menu
        gl.glColor3f(1f, 1f, 1f);
        desenhaTexto(gl, -5,80, "VOCÊ PASSOU A FASE "+jogador.getFase(),24);
        desenhaTexto(gl, -20,60, "(S) CONTINUAR?",18);
        desenhaTexto(gl, -20,0, "(N) VOLTAR AO MENU",18);
        desenhaTexto(gl, -20,-90, "(ESC) SAIR",18);
        desenhaTexto(gl, -10,40, "Pontos: " + pontos,18);


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
        background.getObjSprite().desenhar(gl);

        // movimentar tudo q precisa

        if (!jogador.isPausado()){
            // TODO adicionar uma classe para manipular eventos do jogo (colisão, pontos, etc)

            // movimentação q2
            colisaoBolinhaBordas(bolinha.getObjSprite()); // detectando colisões
            colisaoBolinhaBarra(bolinha.getObjSprite(),jogador.getObjSprite());
            if (bolinha.getObjSprite().isMovendo()) bolinha.getObjSprite().mover(); // chamando o método de movimento

            gl.glColor4f(1.0f, 1.0f, 1.0f, bolinha.getObjSprite().getAlfa());
            desenhaTexto(gl, (int) (bolinha.getObjSprite().getIntervaloEsquerda()[0][0]),
                    (int) bolinha.getObjSprite().getIntervaloCima()[1][1] + 10,
                    "alfa q2: " + bolinha.getObjSprite().getAlfa());

            // movimentação q1
            if (jogador.getObjSprite().isMovendo()) {
                switch (jogador.getObjSprite().getDirecao()) {
                    case DIREITA:
                        jogador.getObjSprite().setVelx(+1);
                        break;

                    case ESQUERDA:
                        jogador.getObjSprite().setVelx(-1);
                        break;

                    case CIMA:
                        jogador.getObjSprite().setVely(+1);
                        break;

                    case BAIXO:
                        jogador.getObjSprite().setVely(-1);
                        break;

                    default:
                        jogador.getObjSprite().setVelx(0);
                        jogador.getObjSprite().setVely(0);
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
        desenhaTexto(gl,(int)mouseX,(int)(mouseY+5),"mouse X: "+mouseX,18);
        desenhaTexto(gl,(int)mouseX,(int)(mouseY),"mouse Y: "+mouseY,18);
        desenhaTexto(gl,0,90,"FASE: "+jogador.getFase());

        gl.glFlush();

        if (jogador.getPontos() >= 100){
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
        background.getObjSprite().desenhar(gl);
        obstaculo.getObjSprite().desenhar(gl);

        // movimentar tudo q precisa

        if (!jogador.isPausado()){

            // movimentação q2

            colisaoBolinhaBordas(bolinha.getObjSprite() ); // detectando colisões
            colisaoBolinhaBarra(bolinha.getObjSprite(),jogador.getObjSprite());
            colisaoBolinhaObstaculo(bolinha.getObjSprite(),obstaculo.getObjSprite());

            if (bolinha.getObjSprite().isMovendo()) bolinha.getObjSprite().mover(); // chamando o método de movimento

            gl.glColor4f(1.0f, 1.0f, 1.0f, bolinha.getObjSprite().getAlfa());
            desenhaTexto(gl, (int) (bolinha.getObjSprite().getIntervaloEsquerda()[0][0]),
                    (int) bolinha.getObjSprite().getIntervaloCima()[1][1] + 10,
                    "alfa bolinha: " + bolinha.getObjSprite().getAlfa());

            // movimentação q1
            if (jogador.getObjSprite().isMovendo()) {
                switch (jogador.getObjSprite().getDirecao()) {
                    case DIREITA:
                        jogador.getObjSprite().setVelx(+1);
                        break;

                    case ESQUERDA:
                        jogador.getObjSprite().setVelx(-1);
                        break;

                    case CIMA:
                        jogador.getObjSprite().setVely(+1);
                        break;

                    case BAIXO:
                        jogador.getObjSprite().setVely(-1);
                        break;

                    default:
                        jogador.getObjSprite().setVelx(0);
                        jogador.getObjSprite().setVely(0);
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
//                    if (!(nPosX > jogador.getObjSprite().getIntervaloEsquerda()[0][0] &&
//                            nPosX2 < jogador.getObjSprite().getIntervaloDireita()[0][1] ))
//                    {jogador.getObjSprite().mover();}
//                    else {jogador.getObjSprite().setMovendo(false);}
                    jogador.getObjSprite().mover();
                }
            }

        }

        gl.glColor3f(1, 0, 0);
        desenhaTexto(gl, 0, 90, "Pontos: " + pontos);
        desenhaTexto(gl,0,90,"FASE: "+jogador.getFase());

        gl.glFlush();

        if (jogador.getPontos() >= 200){
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
        q3.desenhar(gl);

        // movimentar tudo q precisa

        if (!jogador.isPausado()){// TODO adicionar uma classe para manipular eventos do jogo (colisão, pontos, etc)

            // movimentação q2
            colisaoBolinhaBordas(bolinha.getObjSprite() ); // detectando colisões
            colisaoBolinhaBarra(bolinha.getObjSprite(),jogador.getObjSprite());
            //colisaoBolinhaQ6(bolinha.getObjSprite());
            if (bolinha.getObjSprite().isMovendo()) bolinha.getObjSprite().mover(); // chamando o método de movimento

            gl.glColor4f(1.0f, 1.0f, 1.0f, bolinha.getObjSprite().getAlfa());
            desenhaTexto(gl, (int) (bolinha.getObjSprite().getIntervaloEsquerda()[0][0]),
                    (int) bolinha.getObjSprite().getIntervaloCima()[1][1] + 10,
                    "alfa q2: " + bolinha.getObjSprite().getAlfa());

            // movimentação q1
            if (jogador.getObjSprite().isMovendo()) {
                switch (jogador.getObjSprite().getDirecao()) {
                    case DIREITA:
                        jogador.getObjSprite().setVelx(+1);
                        break;

                    case ESQUERDA:
                        jogador.getObjSprite().setVelx(-1);
                        break;

                    case CIMA:
                        jogador.getObjSprite().setVely(+1);
                        break;

                    case BAIXO:
                        jogador.getObjSprite().setVely(-1);
                        break;

                    default:
                        jogador.getObjSprite().setVelx(0);
                        jogador.getObjSprite().setVely(0);
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
//                    if (!(nPosX > jogador.getObjSprite().getIntervaloEsquerda()[0][0] &&
//                            nPosX2 < jogador.getObjSprite().getIntervaloDireita()[0][1] ))
//                    {jogador.getObjSprite().mover();}
//                    else {jogador.getObjSprite().setMovendo(false);}
                    jogador.getObjSprite().mover();
                }
            }

        }

        gl.glColor3f(1, 0, 0);
        desenhaTexto(gl, 0, 90, "Pontos: " + pontos);

        gl.glFlush();
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

        // feedback na tela
        desenhaTexto(gl,-90,10,"colidiu Cima: "+colisaoQ2cima);
        desenhaTexto(gl,-90,0,"colidiu baixo: "+colisaoQ2baixo);
        desenhaTexto(gl,-90,-10,"q2 colidiu direita: "+colisaoQ2direita);
        desenhaTexto(gl,-90,-20,"q2 colidiu esquerda: "+colisaoQ2esquerda);

        // definindo interação caso às condições sejam atendidas
        if (colisaoQ2cima || colisaoQ2baixo){bolinha.setVely(bolinha.getVely()*-1);}
        if (colisaoQ2direita || colisaoQ2esquerda){bolinha.setVelx(bolinha.getVelx()*-1);}
        if (colisaoQ2baixo){
            pontos = (pontos <= 0 ) ? 0 : pontos -5;
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

        //feedback
        desenhaTexto(gl, (int) barra.getIntervaloBaixo()[0][0],
                (int) (barra.getIntervaloBaixo()[1][0]-5),"q1 colidiu direita: "+colisaoQ1direita,18);

        desenhaTexto(gl, (int) barra.getIntervaloBaixo()[0][0],
                (int) (barra.getIntervaloBaixo()[1][0]-10),"q1 colidiu esquerda: "+colisaoQ1esquerda,18);

        // definindo interação caso às condições sejam atendidas
        if (colisaoQ1direita && barra.getVelx() == 1) barra.setVelx(0);
        if (colisaoQ1esquerda && barra.getVelx() == -1) barra.setVelx(0);
    }

    public void colisaoBolinhaBarra(QuadradoSprite bolinha, QuadradoSprite barra){


        // obtendo feedback e armazenando
        boolean colisaoY,colisaoX;

        colisaoY = bolinha.isColiding(barra.getIntervaloCima()[0],barra.getIntervaloCima()[1]) ||
                bolinha.isColiding(barra.getIntervaloBaixo()[0],barra.getIntervaloBaixo()[1]);

        colisaoX = bolinha.isColiding(barra.getIntervaloDireita()[0],barra.getIntervaloDireita()[1]) ||
                bolinha.isColiding(barra.getIntervaloEsquerda()[0],barra.getIntervaloEsquerda()[1]);

        // definindo interações
        if (colisaoY){
          bolinha.setVely(bolinha.getVely()*-1);
          jogador.ganharPontos(50);
        }
        if (colisaoX){
            bolinha.setVelx(bolinha.getVelx()*-1);
            barra.setVelx(0);
        }


    }

    public void colisaoBolinhaObstaculo(QuadradoSprite bolinha, QuadradoSprite obstaculo){

        // obtendo feedback e armazenando
        boolean colisaoY,colisaoX;


        colisaoY = bolinha.isColiding(obstaculo.getIntervaloCima()[0],obstaculo.getIntervaloCima()[1]) ||
                bolinha.isColiding(obstaculo.getIntervaloBaixo()[0],obstaculo.getIntervaloBaixo()[1]);

        colisaoX = bolinha.isColiding(obstaculo.getIntervaloDireita()[0],obstaculo.getIntervaloDireita()[1]) ||
                bolinha.isColiding(obstaculo.getIntervaloEsquerda()[0],obstaculo.getIntervaloEsquerda()[1]);

        // definindo interações
        if (colisaoY){bolinha.setVely(bolinha.getVely()*-1);jogador.perderPontos(20);}
        if (colisaoX){bolinha.setVelx(bolinha.getVelx()*-1);jogador.perderPontos(20);}


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
