
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lienzo extends JPanel

{
    private Color colorFondo;
    private int coordXOrig = 200;
    private int coordYOrig = 150;

    //pelota
    private int iX;
    private int iY;
    private int diametro;


    public void setCoordXOrig(int coordXOrig) {
        this.coordXOrig = coordXOrig;
    }

    public void setCoordYOrig(int coordYOrig) {
        this.coordYOrig = coordYOrig;
    }


    @Override
    public void paintComponent(Graphics g) {


        //Establecer el color de fondo
        //-----ésta forma no funciona cuando se trabaja en el contexto gráfico
        //
        //this.setBackground(colorFondo);
        //-------------

        g.setColor(colorFondo);
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        //Dibujar el plano cartesiano
        //Obtener coordenadas centrales del lienzo para establecer origen (0,0)

        //coordXOrig = (int) (this.getWidth() / 2);
        //coordYOrig = (int) (this.getHeight() / 2);
        g.setColor(Color.GREEN);

        //Dibujar eje X con valores y etiquetas
        g.drawLine(0, coordYOrig, this.getWidth() - 1, coordYOrig);

        //dibujar número de eje x

        int numValores = (int) (coordXOrig / 40 + 1);
        int XActual = coordXOrig - numValores * 40;

        for (int valor = 0 - numValores; valor <= ((this.getWidth() / 2) / 40); valor++) {
            g.drawLine(XActual, coordYOrig - 5, XActual, coordYOrig + 5);
            g.drawString("" + valor, XActual - 10, coordYOrig + 20);
            //para ubicar el texto 5 pixeles debajo de la línea y centrado hay que cálculo

            XActual += 40;
        }

        //Dibujar eje y con valores y etiquetas
        g.drawLine(coordXOrig, 0, coordXOrig, this.getHeight());
        graficarSeno(g, coordXOrig, coordYOrig);

        //dibuje el circulo
        graficarCirculo(g);
    }

    public void graficarSeno(Graphics g, int x, int y) {
        float gradRad;
        float sinGrad;
        int XActual, YActual, factorX = 1, factorY = 200;

        g.setColor(Color.red);

        for (int grad = 0; grad < 361; grad++) {
            gradRad = (float) Math.toRadians(grad++);
            sinGrad = (float) Math.sin(gradRad);

            //Cálculo de coordenadas de pantalla
            XActual = x + (grad * factorX);
            YActual = (int) (y - sinGrad * factorY);

            g.fillRect(XActual, YActual, 5, 5);

        }
    }


    public Lienzo() {
        super();
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    iX = 50;
                    repaint();
                }

                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    iX  = -50;
                    repaint();
                }

                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    iY =  50;
                    repaint();
                }

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    iY =  - 50;
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

        });
        this.setFocusable(true);
        this.requestFocusInWindow();


        diametro = 50;
        iX = iY = 100;

        //Crear JPanel y agregarlo
        this.setPreferredSize(new Dimension(800, 400));
        colorFondo = Color.BLACK;
        this.setBackground(colorFondo);


        controladorRaton oyenteRaton = new controladorRaton();
        this.addMouseMotionListener(oyenteRaton);
        this.addMouseMotionListener(oyenteRaton);
    }

    public void graficarCirculo(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRoundRect(iX + 100, iY + 100, diametro, diametro, 50, 50);

    }

    public Color getColorFondo() {
        return colorFondo;
    }
    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;

    }

    public class controladorRaton extends MouseAdapter implements MouseMotionListener {
        boolean bandMoverOrigen = false;

        @Override
        public void mouseClicked(MouseEvent e){
            //Detectar el bóton al que se le dió click

            //Click derecho activa bandera si fué en el origen o en un radio de 20px
            if((e.getButton() == MouseEvent.BUTTON3)&&((e.getX() >= coordXOrig - 20)
                    && (e.getX() <= coordXOrig +20)
                    && (e.getY() >= coordYOrig -20)
                    && (e.getY() >= coordYOrig +20)
            )){

                bandMoverOrigen = true;
                //el boton 1 es clic izquierdo

            }else if(e.getButton() == MouseEvent.BUTTON1){

                bandMoverOrigen = false;
            }
            System.out.println("Clic Botón ="+ e.getButton() +
                    "Cordenada  Clic ("+ e.getX()+" , "+ e.getY());
        }
        @Override
        public void mouseMoved(MouseEvent e){
            if(e.getButton() == MouseEvent.BUTTON1){
                coordXOrig = e.getX();
                coordYOrig = e.getY();

                repaint();
            }

            System.out.println("Cordenada actual ("+ e.getX()+ ", "+ e.getY() + ")" + "bandMoverOrigen " +bandMoverOrigen);
        }


    }
}
