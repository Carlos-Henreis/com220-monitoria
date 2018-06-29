package View.mercadoria;

import Controll.ctrMercadoria;
import Model.entMercadoria;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class LimConsultaMercadoria {
    String result;
    ctrMercadoria objCtrMercadoria;

    JFrame janela, janela1;

    JLabel labelCodigo, labelDados;

    JTextField textCodigo;
    JTextArea textDados;
    JButton botaoConsultar;
    JButton sair;
    JPanel panel1, panel2;

    public LimConsultaMercadoria(ctrMercadoria pCtrMercadoria) {
        objCtrMercadoria = pCtrMercadoria;
        String resultado = "";
        janela1 = new JFrame("Consulta Mercadoria");
        panel1 = new JPanel();
        panel2 = new JPanel(new BorderLayout(100, 100));
        labelDados = new JLabel();
        //Label e TextField
        labelCodigo = new JLabel("Informe o código: ");
        textCodigo = new JTextField(10);

        //Botão
        botaoConsultar = new JButton("Consultar");
        botaoConsultar.setBackground(new Color(0,0,128));
        botaoConsultar.setForeground(Color.white);
        botaoConsultar.setBorderPainted(false);
        botaoConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(textCodigo.getText());
                entMercadoria objMercadoria = objCtrMercadoria.consultaCod(codigo);
                result = objCtrMercadoria.imprimeMercadoria(codigo);
                labelDados.setText("<html>"+result+"</html>");
            }
        });
        
        sair = new JButton("SAIR");
        sair.setBackground(new Color(0,0,128));
        sair.setForeground(Color.white);
        sair.setBorderPainted(false);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela1.dispose();
            }
        });
        
        labelDados.setHorizontalAlignment(JLabel.CENTER);
        labelDados.setVerticalAlignment(JLabel.CENTER);

        //Organizando os componentes na janela

        panel1.add(labelCodigo);
        panel1.add(textCodigo);
        panel1.add(botaoConsultar);        
        panel2.add(panel1, BorderLayout.PAGE_START);
        panel2.add(labelDados, BorderLayout.CENTER);
        panel2.add(sair,BorderLayout.PAGE_END);
        janela1.add(panel2);
        janela1.setUndecorated(true);
        janela1.setSize(800, 600);
        janela1.setLocationRelativeTo(null);
        janela1.setAlwaysOnTop(true);
        janela1.setVisible(true);
    }

    
}
