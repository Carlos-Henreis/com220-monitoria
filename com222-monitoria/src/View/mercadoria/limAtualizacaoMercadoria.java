package View.mercadoria;

import Controll.ctrMercadoria;
import Model.entMercadoria;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class limAtualizacaoMercadoria {
    private JFrame frame;
    private JButton cadastrar,sair;
    private JTextField codigoTF,quantidadeTF;
    private JLabel codigoJL,quantidadeJL;
    private JPanel pcod,pdesc,psair,principal;
    private BoxLayout box;
    private ActionListener cadListener,sairListener;
    private ctrMercadoria objCtrl;

    public limAtualizacaoMercadoria(ctrMercadoria pCtrl) {
        objCtrl = pCtrl;
        
        //Criar text fields
        codigoTF = new JTextField(20);
        quantidadeTF = new JTextField(20);
        
        //Criar JLabel's
        codigoJL     = new JLabel("Codigo da Mercadoria :");
        quantidadeJL  = new JLabel("Aumentar Estoque:");
        
        //Criar Botoes
        cadastrar = new JButton("Atualizar");
        cadastrar.setBorderPainted(false);
        cadastrar.setForeground(Color.white);
        cadastrar.setBackground(new Color(0,0,128));
        sair = new JButton("Sair");
        sair.setBorderPainted(false);
        sair.setForeground(Color.white);
        sair.setBackground(new Color(0,0,128));
        
        //Criar listeners
        cadListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.atualizarDadosMercadoria();
            }
        };
        
        sairListener =  new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        };
        
        //Adicionar listeners aos botoes
        cadastrar.addActionListener(cadListener);
        sair.addActionListener(sairListener);
        
        //Criar Jpanel's
        pcod = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        pdesc = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        psair = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        principal = new JPanel();
        box = new BoxLayout(principal,BoxLayout.Y_AXIS);
        principal.setLayout(box);

        //Adicionar componentes aos paineis
        pcod.add(codigoJL);
        pcod.add(codigoTF);
        pdesc.add(quantidadeJL);
        pdesc.add(quantidadeTF);
        psair.add(Box.createHorizontalGlue());
        psair.add(Box.createHorizontalGlue());
        psair.add(Box.createHorizontalGlue());
        psair.add(cadastrar);
        psair.add(sair);
        
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(pcod);
        principal.add(pdesc);
        principal.add(psair);
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        //Criar JFRame e definir opcoes
        frame = new JFrame("Cadastro do grupo relacionado de diagnostico");
        frame.getContentPane().add(principal);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
    /**
     * Metodo que exibe uma mensagem de erro ao usuario
     * @param msg Mensagem que sera exibida na interface
     */
    public void mensagemErro(String msg) {
        JOptionPane.showMessageDialog(principal, msg, "Erro ao atualizar mercadoria", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Mensagem de confirmaçao de cadastro ao usuario
     */
    public void mensagemSucesso() {
        JOptionPane.showMessageDialog(principal, "Mercadoria atualizada com sucesso!");
    }
    
    /**
     * Obter os dados inseridos pelo usuario
     * @return dados de uma nova DRG
     * @throws Exception caso nao sejam informados todos os campos ou o codigo nao seja numerico
     */
    public String[] getDados() throws Exception {
        String form[] = new String[2];
        
        if(codigoTF.getText().isEmpty() || quantidadeTF.getText().isEmpty())
            throw new Exception("Voce deve informar todos os campos!");
        
        int teste = Integer.parseInt(codigoTF.getText());
        
        form[0] = codigoTF.getText();
        form[1] = quantidadeTF.getText();
        
        return form;
    }
    
    /**
     * Limpar os dados presentes nos campos de texto (usado apos um cadastro com sucesso)
     */
    public void limparFormulario() {
        codigoTF.setText("");
        quantidadeTF.setText("");
    }
}
