package View.principal;

import Controll.ctrPrincipal;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class limTelaLogin{
    JFrame frame;
    JButton entrar,sair;
    JTextField login;
    JPasswordField senha;
    private ctrPrincipal objCtrl;

    public limTelaLogin(ctrPrincipal principal) {
        //Criar Jbuttons
        entrar = new JButton("Login");
        entrar.setBorderPainted(false);
        entrar.setBackground(new Color(0,0,128));
        entrar.setForeground(Color.WHITE);
        sair = new JButton("Sair");
        sair.setBorderPainted(false);
        sair.setBackground(new Color(0,0,128));
        sair.setForeground(Color.WHITE);
        String dados[];
        //Criar TextFields
        login = new JTextField();
        login.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"Login:"));
        senha = new JPasswordField();
        senha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"Senha:"));
        objCtrl = principal;
        
        //Adicionar Listener aos botoes e ao campo de senha
        ActionListener go  = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dados[] = obterDadosInterface();
                try {
                    objCtrl.login(dados[0], dados[1]);
                    sucessoLogin(dados[0]);
                } catch (Exception ex) {
                    mensagemErro(ex.getMessage());
                }
            }
        };
        entrar.addActionListener(go);
        senha.addActionListener(go);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        //Criar paineis
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        botoes.setBackground(Color.white);
        JPanel p1 = new JPanel(new GridLayout(3, 1));
        p1.setBackground(Color.white);
        JPanel p2 = new JPanel();
        BoxLayout box0 = new BoxLayout(p2, BoxLayout.X_AXIS);
        p2.setLayout(box0);
        p2.setBackground(Color.white);
        JPanel sub = new JPanel();
        BoxLayout box = new BoxLayout(sub, BoxLayout.Y_AXIS);
        sub.setLayout(box);
        sub.setBackground(Color.white);
        sub.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        //Adicionar componentes a seus paineis
        botoes.add(sair);
        botoes.add(entrar);
        p1.add(login);
        p1.add(senha);
        p1.add(botoes);
        
        p2.add(Box.createHorizontalGlue());
        p2.add(p1);
        p2.add(Box.createHorizontalGlue());
        
        sub.add(new JLabel(new ImageIcon("img/logo_login.png")));
        sub.add(p2);
        sub.add(Box.createGlue());
        
        //Criar Frame e setar opçoes
        frame = new JFrame();
        frame.add(sub);
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(true);
        frame.setSize(500,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Metodo para ser executado apos login do sistema
     */
    public void sucessoLogin(String nomeusuario) {
       JOptionPane.showMessageDialog(frame,"Bem vindo ao Sistema de controle de estoque Sr(a). "+nomeusuario+"!");
        
       frame.dispose();
       objCtrl.exibirInterfacePrincipal();
    }
    
    /**
     * Exibir mensagem de erro ao usuario
     */
    public void mensagemErro(String erro) {
        JOptionPane.showMessageDialog(frame, erro, "Informaçao do sistema", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Obter dados da interface de usuario
     * @return login e senha
     */
    public String[] obterDadosInterface() {        
        if(login.getText().isEmpty() || senha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame,"Informe todos os campos!");            
            return null;
        }
        String form[] = {login.getText(),senha.getText()};
        return form;
    }
}
