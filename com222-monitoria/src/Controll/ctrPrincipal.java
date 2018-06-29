package Controll;
import Controll.*;
import View.principal.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ctrPrincipal {
    //Definir constantes de login
    private final String USUARIO = "Carlos";
    private final String SENHA = "qwe123";
    private ctrCliente objCtrCliente;
    private ctrMercadoria objCtrMercadoria;
    private ctrNotaFiscal objCtrNotaFiscal;
    private ctrVenda objCtrVenda;
    //limites
    private limTelaLogin objLimLogin;
    private limPrincipal objLimPrincipal;
    
    
    public ctrPrincipal() {
        this.objCtrCliente = new ctrCliente();
        this.objCtrMercadoria = new ctrMercadoria();
        this.objCtrNotaFiscal = new ctrNotaFiscal();
        this.objCtrVenda = new ctrVenda();
        inicializa();
        //Iniciar interface parao usuario
        this.objLimLogin = new limTelaLogin(this);
    }
    
    public void login (String usuario, String senha) throws Exception {
        if (!usuario.equals(this.USUARIO) && !senha.equals(this.SENHA)) {
            throw new Exception("Dados incorretos");
        }
    }

    public void exibirInterfacePrincipal() {
        this.objLimPrincipal = new limPrincipal(this);
    }

    public ctrMercadoria getCtrMercadorias() {
        return this.objCtrMercadoria;
    }

    private void inicializa() {
        try {
            this.objCtrCliente.iniciar();
            this.objCtrMercadoria.iniciar();
            this.objCtrNotaFiscal.iniciar();
            this.objCtrVenda.iniciar();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public void finaliza() {
        try {
            this.objCtrCliente.finalize();
            this.objCtrMercadoria.finalize();
            this.objCtrNotaFiscal.finalize();
            this.objCtrVenda.finalize();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public ctrCliente getCliente() {
        return objCtrCliente;
    }
}
