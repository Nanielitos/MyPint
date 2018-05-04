import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class pantallaPrincipal extends JFrame {

    public pantallaPrincipal(){
        super("Mi paint");

        //Agregar layoutManager BoxLayout
        Container contenedorPrincipal = getContentPane();
        contenedorPrincipal.setLayout(new BoxLayout(contenedorPrincipal, BoxLayout.Y_AXIS));

        //Crear JPanel y agregarlo
        Lienzo lienzo = new Lienzo();
        contenedorPrincipal.add(lienzo);

        //Crear una barra de botones y agregarla
        JPanel pnlBotones = new JPanel();

        JButton btnCambiaColor = new JButton("Cambiar color...");



        //Agregar un Actionlistener al botón para seleccionar un color mediante JColorChooser
        //mediante una clase anónima
        btnCambiaColor.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color colorLienzo = JColorChooser.showDialog(pantallaPrincipal.this,
                                "Selecciona un color", lienzo.getColorFondo());

                        if (colorLienzo != null){
                            lienzo.setColorFondo(colorLienzo);
                        }

                    }
                });

        pnlBotones.setLayout(new FlowLayout());
        pnlBotones.add(btnCambiaColor);

        //Agregar botones para redimencionar la ventana
        JLabel lblAncho = new JLabel("Ancho:");
        JTextField edAncho = new JTextField("800");
        edAncho.setPreferredSize(new Dimension(50,20));

        JLabel lblAlto = new JLabel("Alto:");
        JTextField edAlto = new JTextField("600");
        edAncho.setPreferredSize(new Dimension(50,20));

        JButton btnAjustarTamanio = new JButton("Cambiar tamaño");
        btnAjustarTamanio.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pantallaPrincipal.this.setSize(new Dimension(
                                        Integer.parseInt(edAncho.getText()),
                                        Integer.parseInt(edAlto.getText())
                                )
                        );
                    }
                }
        );
        pnlBotones.add(lblAncho);
        pnlBotones.add(edAncho);
        pnlBotones.add(lblAlto);
        pnlBotones.add(edAlto);
        pnlBotones.add(btnAjustarTamanio);
        contenedorPrincipal.add(pnlBotones);
    }

    public static void main(String[] args){
        pantallaPrincipal prog = new pantallaPrincipal();
        prog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prog.setSize(new Dimension(800, 600));
        prog.setResizable(false);
        prog.setVisible(true);


    }

}