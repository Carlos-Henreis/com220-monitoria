package View.mercadoria;

import Controll.ctrMercadoria;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimCadastroMercadoria {
    private JFrame frame;
    private JButton cadastrar,sair;
    private JLabel codigoJL, descricaoJL, precoVendaJL, precoCompraJL, estoqueJL;
    private JTextField codigoTF, descricaoTF, precoVendaTF, precoCompraTF, estoqueTF;
    private ActionListener cadListener,exitListener;
    private JPanel formulario,pcad,principal,externo;
    private ctrMercadoria objCtrl;

    public LimCadastroMercadoria(ctrMercadoria pCtrl) {
        objCtrl = pCtrl;
        
        //Criar JLabel's
        codigoJL = new JLabel("Código:");
        descricaoJL = new JLabel("Descricao:");
        precoVendaJL = new JLabel("Preço de venda:");
        precoCompraJL = new JLabel("Preço de compra:");
        estoqueJL = new JLabel("Quantidade:");
        
        //Criar TextField's
        codigoTF = new JTextField(20);
        descricaoTF = new JTextField(20);
        precoVendaTF = new JTextField(20);
        precoCompraTF = new JTextField(20);
        estoqueTF = new JTextField(20);
        
        //Criar Jbuttons
        cadastrar = new JButton("CADASTRAR");
        cadastrar.setBorderPainted(false);
        cadastrar.setForeground(Color.white);
        cadastrar.setBackground(new Color(0,0,128));
        sair = new JButton("SAIR");
        sair.setBorderPainted(false);
        sair.setForeground(Color.white);
        sair.setBackground(new Color(0,0,128));
        
        //Gerar Listener's e adicionar aos botoes
        cadListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.cadastraMercadoria();
            }
        };
        cadastrar.addActionListener(cadListener);
        
        exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        };
        sair.addActionListener(exitListener);
        
        //Criar JPanel's
        formulario = new JPanel(new GridLayout(6, 2, 10, 20));
        pcad = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        principal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        externo = new JPanel();
        BoxLayout box = new BoxLayout(externo,BoxLayout.Y_AXIS);
        externo.setLayout(box);
        externo.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        //Adicionar componentes aos devidos paineis
        pcad.add(cadastrar);
        pcad.add(sair);
        
        formulario.add(codigoJL);
        formulario.add(codigoTF);
        formulario.add(descricaoJL);
        formulario.add(descricaoTF);
        formulario.add(precoCompraJL);
        formulario.add(precoCompraTF);
        formulario.add(precoVendaJL);
        formulario.add(precoVendaTF);
        formulario.add(estoqueJL);
        formulario.add(estoqueTF);
        formulario.add(Box.createHorizontalGlue());
        formulario.add(pcad);
        
        principal.add(formulario);
        
        externo.add(Box.createGlue());
        externo.add(principal);
        externo.add(Box.createGlue());
        
        frame = new JFrame("Cadastro de Mercadoria");
        frame.add(externo);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
    /**
     * Metodo que exibe uma mensagem de erro durante a atualizacao
     * @param msg Mensagem que sera exibida
     */
    public void mensagemErro(String msg) {
        JOptionPane.showMessageDialog(externo,msg,"Erro na atualizaçao",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Exibir mensagem de sucesso de cadastro ao usuario
     */
    public void mensagemSucesso() {
        JOptionPane.showMessageDialog(principal, "Mercadoria cadastrada!");
    }
    
    /**
     * Metodo para obter os dados do nova mercadoria da interface de usuario
     * @return Dados da mercadoria
     * @throws Exception Caso o usuario nao informe todos os campos
     */
    public String[] getDados() throws Exception {
        String form[] = new String[5];
        
        if(codigoTF.getText().isEmpty() ||descricaoTF.getText().isEmpty() ||  precoCompraTF.getText().isEmpty() || precoVendaTF.getText().isEmpty() ||  estoqueTF.getText().isEmpty())
            throw new Exception("Voce deve informar todos os campos");
        
        form[0] = codigoTF.getText();
        form[1] = descricaoTF.getText();
        form[2] = precoCompraTF.getText();
        form[3] = precoVendaTF.getText();
        form[4] = estoqueTF.getText();
        
        return form;
    }
    
    /**
     * Metodo que limpa os campos de entrada de texto da interface
     */
    public void limparFormulario() {
        codigoTF.setText("");
        descricaoTF.setText("");
        precoCompraTF.setText("");
        precoVendaTF.setText("");
        estoqueTF.setText("");
    }
}
