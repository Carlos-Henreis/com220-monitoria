package View.principal;

import Controll.ctrPrincipal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class limPrincipal {

    private JFrame frame;
    private JPanel painel, central, inicial, pfinal;
    private JLabel equipe, boasvindas;
    private JLabel logotipo;
    private JMenuBar barramenu;
    private JButton btnExit;
    private JMenu mercadoria, cliente, consultas, vendas;
    private ctrPrincipal objCtrl;
    private WindowListener winList;

    private final ImageIcon logoIMG = new ImageIcon("img/estoque13.png");
    private final ImageIcon create = new ImageIcon("img/create.png");
    private final ImageIcon delete = new ImageIcon("img/delete.png");
    private final ImageIcon read = new ImageIcon("img/read.png");
    private final ImageIcon update = new ImageIcon("img/update.png");
    private final ImageIcon regiao = new ImageIcon("img/local.png");

    public limPrincipal(ctrPrincipal pCtrl) {
        objCtrl = pCtrl;

        //Criar JLabel's
        equipe = new JLabel("Developed by: Carlos Henrique Reis @ All rights reserved. UNIFEI, 2018");
        logotipo = new JLabel(logoIMG);
        boasvindas = new JLabel();

        //Criar JMenuItem's e adicionar action listeners
        //->  MENUS DE Produto
        JMenuItem cadasMercadoria = new JMenuItem("Cadastrar Mercadoria", create);
        cadasMercadoria.setBackground(Color.white);
        cadasMercadoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getCtrMercadorias().interfaceCadastroMercadoria();
            }
        });
        
        JMenuItem visualizarMercadoria = new JMenuItem("Visualizar Mercadoria", read);
        visualizarMercadoria.setBackground(Color.white);
        visualizarMercadoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getCtrMercadorias().exibirMercadoria();
            }
        });
        
        JMenuItem atualizarMercadoria = new JMenuItem("Atualizar Mercadoria", update);
        atualizarMercadoria.setBackground(Color.white);
        atualizarMercadoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getCtrMercadorias().interfaceAtualizacaoMercadorias();
            }
        });
        
        JMenuItem listararMercadoria = new JMenuItem("Listar Mercadorias em Estoque", read);
        listararMercadoria.setBackground(Color.white);
        listararMercadoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getCtrMercadorias().listarMercadorias();
            }
        });
        //-> MENUS DE Cliente
        JMenuItem cadastrarCliente = new JMenuItem("Cadastrar Cliente", create);
        cadastrarCliente.setBackground(Color.white);
        cadastrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //objCtrl.getCtrMercadorias().exibirMercadoria();;
            }
        });
        
        JMenuItem consultarCliente = new JMenuItem("Consultartar Cliente", update);
        consultarCliente.setBackground(Color.white);
        consultarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //objCtrl.getCtrMercadorias().exibirMercadoria();
            }
        });
        //->MENUS DE Consultas
        JMenuItem consultarFatProduto = new JMenuItem("Por Produto", create);
        consultarFatProduto.setBackground(Color.white);
        consultarFatProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //objCtrl.getCtrMercadorias().exibirMercadoria();
            }
        });
        
        JMenuItem consultarFatCliente = new JMenuItem("Por Cliente", create);
        consultarFatCliente.setBackground(Color.white);
        consultarFatCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //objCtrl.getCtrMercadorias().exibirMercadoria();
            }
        });
        
        JMenuItem consultarFatPeriodo = new JMenuItem("Por Periodo", create);
        consultarFatPeriodo.setBackground(Color.white);
        consultarFatPeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //objCtrl.getCtrMercadorias().exibirMercadoria();
            }
        });
        
        JMenuItem consultarLucroPeriodo = new JMenuItem("Lucro Liquido por periodo", create);
        consultarLucroPeriodo.setBackground(Color.white);
        consultarLucroPeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //objCtrl.getCtrMercadorias().exibirMercadoria();
            }
        });
        
        JMenuItem vendasCliente = new JMenuItem("Vendas por cliente", create);
        vendasCliente.setBackground(Color.white);
        vendasCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //objCtrl.getCtrMercadorias().exibirMercadoria();
            }
        });
        
        JMenuItem top10 = new JMenuItem("Top 10", create);
        top10.setBackground(Color.white);
        top10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //objCtrl.getCtrMercadorias().exibirMercadoria();
            }
        });
        
        JMenuItem vendasCad = new JMenuItem("Cadastrar venda", create);
        vendasCad.setBackground(Color.white);
        vendasCad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //objCtrl.getControladorAtendimentoDRG().interfaceCadastroAtendimentoDRG();
            }
        });

        //Criar JMenu's
        mercadoria = new JMenu("Mercadoria");
        mercadoria.setForeground(Color.white);
        mercadoria.setBackground(new Color(124, 1, 45));
        cliente = new JMenu("Cliente");
        cliente.setForeground(Color.white);
        cliente.setBackground(new Color(124, 1, 45));
        consultas = new JMenu("Consultas");
        consultas.setForeground(Color.white);
        consultas.setBackground(new Color(124, 1, 45));
        vendas = new JMenu("Vendas");
        vendas.setForeground(Color.white);
        vendas.setBackground(new Color(124, 1, 45));

        //Adicionar itens a seus respectivos menus
        //-> Menu Mercadoria
        mercadoria.add(cadasMercadoria);
        mercadoria.add(visualizarMercadoria);
        mercadoria.add(atualizarMercadoria);
        mercadoria.addSeparator();
        mercadoria.add(listararMercadoria);
        //-> Menu Clientes
        cliente.add(cadastrarCliente);
        cliente.add(consultarCliente);
        //->Menu Atendimento a consultas
        consultas.add(consultarFatPeriodo);
        consultas.add(vendasCliente);
        consultas.add(consultarFatCliente);
        consultas.add(consultarFatProduto);
        consultas.add(consultarLucroPeriodo);
        consultas.add(top10);
        //->Menu vendas
        vendas.add(vendasCad);

        //Criar JMenuBar e adicionar submenus
        barramenu = new JMenuBar();
        //Layout barramenu
        BoxLayout box = new BoxLayout(barramenu, BoxLayout.X_AXIS);
        barramenu.setLayout(box);

        //Configurações barramenu
        barramenu.setBackground(new Color(65, 130, 224));

        //Exit
        btnExit = new JButton(new ImageIcon("img/exit.png"));
        btnExit.setBorderPainted(false);
        btnExit.setBackground(new Color(65, 130, 224));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.finaliza();
                System.exit(0);
            }
        });

        barramenu.add(mercadoria);
        barramenu.add(cliente);
        barramenu.add(consultas);
        barramenu.add(vendas);
        barramenu.add(Box.createGlue());
        barramenu.add(btnExit);

        //Criar JPanel's
        pfinal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pfinal.add(equipe);
        pfinal.setBackground(Color.white);
        painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.white);
        central = new JPanel(new FlowLayout(FlowLayout.CENTER));
        central.setBackground(Color.white);
        inicial = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 30));
        inicial.setBackground(Color.WHITE);

        //Adicionar componentes aos paineis
        inicial.add(boasvindas);
        central.add(logotipo);
        //painel.add(inicial,BorderLayout.PAGE_START);
        painel.add(pfinal, BorderLayout.PAGE_END);
        painel.add(central, BorderLayout.CENTER);

        //Gerar JFrame e setar configurações
        frame = new JFrame("Sistema de gerenciamento de estoque");
        frame.addWindowListener(winList);
        frame.setSize(800, 600);
        frame.add(painel);
        frame.setJMenuBar(barramenu);
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
