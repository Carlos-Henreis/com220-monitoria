package Controll;

import Model.entMercadoria;
import View.mercadoria.*;
import java.io.*;
import java.util.ArrayList;

public class ctrMercadoria {
    //Declaração dos atributos
    private LimCadastroMercadoria objLimCadMercadoria;
    private LimConsultaMercadoria objLimExibeMercadoria;
    private limAtualizacaoMercadoria objAtualizarMercadoria;
    private limExibirMercadorias objListagemMercadorias;
    private ArrayList<entMercadoria> listaMercadorias;
    private entMercadoria objEntMercadoria;
    private String arquivo;

    public ctrMercadoria() {
        this.listaMercadorias = new ArrayList<>();
        this.arquivo = "Mercadorias_cad.dat";
    }
    
    public void cadastraMercadoria() {
        try{
            String form[] = objLimCadMercadoria.getDados();
            objEntMercadoria = new entMercadoria(Integer.parseInt(form[0]),form[1],Double.parseDouble(form[3]),Double.parseDouble(form[2]),Integer.parseInt(form[4]));
            if(consultaCod(Integer.parseInt(form[0])) == null) {
                this.listaMercadorias.add(objEntMercadoria);
                objLimCadMercadoria.limparFormulario();
                objLimCadMercadoria.mensagemSucesso();
            }
            else
                objLimCadMercadoria.mensagemErro("Falha ao cadastrar Mercadoria (Codigo já em uso)!");
        }catch(Exception exc){
            objLimCadMercadoria.mensagemErro("Verifique os dados informados!");
        }
    }
    
     public entMercadoria consultaCod (int pCod) {
        for (entMercadoria mercadoria : this.listaMercadorias) {
            if (mercadoria.getCodigo() == pCod) {
                return mercadoria;
            }
        }
        return null;
    }
  
    public void vendeMercadoria(int pCod, int pQtdVendida) throws Exception {
        objEntMercadoria = this.consultaCod(pCod);
        if (objEntMercadoria != null) {
            if (objEntMercadoria.getEstoque() >= pQtdVendida) {
                objEntMercadoria.setEstoque(objEntMercadoria.getEstoque() - pQtdVendida);
            } else {
                throw new Exception("Existem apenas "+objEntMercadoria.getEstoque()+" e deseja-se vender: "+pQtdVendida);
            }
        } else {
            throw new Exception("Não existe mercadoria cadastrada com o código "+pCod);
        }
    }
    
     private void serializaMercadoria() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(this.arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(this.listaMercadorias);
        objOS.flush();
        objOS.close();
    } 
    
    private void desserializaMercadoria() throws Exception {
        File objFile = new File(this.arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            this.listaMercadorias = (ArrayList) objIS.readObject();
            objIS.close();
        }
    }
    
    public ArrayList getListaMercadorias() {
        return this.listaMercadorias;
    }
    
    public void iniciar() throws Exception {
        this.desserializaMercadoria();
    }
    
    public void finalize() throws Exception {
        this.serializaMercadoria();
    }
    
    public String imprimeMercadorias() {
        String result = "";
        if (this.listaMercadorias.size() == 0)
           result+="<CENTER><FONT COLOR=RED SIZE=5>NENHUMA MERCADORIA CADASTRADA COM ESTE CÒDIGO</FONT></CENTER>";
        else{
            result += "<CENTER><FONT COLOR=BLUE SIZE=6>Todos os clientes cadastrados</FONT></CENTER><TABLE BORDER=1> "
                    + "<TR>"
                    + "<TD>Cod </TD> "
                    + "<TD>Descição</TD>"
                    + "<TD>Preco de compra</TD>"
                    + "<TD>Preco de venda</TD>"
                    + "<TD>Estoque</TD>"
                    + " </TR>";
            for (entMercadoria mercadoria : this.listaMercadorias) {
                result +=  "<TR>"+"<TD>"+mercadoria.getCodigo()+ "</TD><TD>" +  mercadoria.getDescricao()+ "</TD><TD>" + mercadoria.getPrecoCompra() + "</TD><TD>" +  mercadoria.getPrecoVenda() + "</TD><TD>" +  mercadoria.getEstoque() + "</TD></TR>";
            }
            result += "</TABLE>";
        }
        return result;
    }
    
    public String imprimeMercadoria(int pCod) {
        String result = "";
        objEntMercadoria = consultaCod(pCod);
        if(objEntMercadoria == null) {
            result+="<CENTER><FONT COLOR=RED SIZE=6>NENHUMA MERCADORIA CADASTRADA COM ESTE CÓDIGO</FONT></CENTER>";
        } else {
            result += "<CENTER><FONT COLOR=BLUE SIZE=6>Mercadoria Cadastrada</FONT></CENTER><TABLE BORDER=1> "
                    + "<TR>"
                    + "<TD>Cod </TD> "
                    + "<TD>Descição</TD>"
                    + "<TD>Preco de compra</TD>"
                    + "<TD>Preco de venda</TD>"
                    + "<TD>Estoque</TD>"
                    + " </TR>"
                    + "<TR>"+"<TD>"+objEntMercadoria.getCodigo()+ "</TD><TD>" +  objEntMercadoria.getDescricao()+ "</TD><TD>" + objEntMercadoria.getPrecoCompra() + "</TD><TD>" +  objEntMercadoria.getPrecoVenda() + "</TD><TD><b>" +  objEntMercadoria.getEstoque() + "</b></TD></TR>"
                    + "</TABLE>";
        }
            
        return result;
    }

    public void interfaceCadastroMercadoria() {
        objLimCadMercadoria = new LimCadastroMercadoria(this);
    }

    public void exibirMercadoria() {
        objLimExibeMercadoria = new LimConsultaMercadoria(this);
    }

    public void atualizarDadosMercadoria() {
        try{
            String form[] = objAtualizarMercadoria.getDados();
            objEntMercadoria = this.consultaCod(Integer.parseInt(form[0]));
            if (objEntMercadoria != null) {
                objEntMercadoria.setEstoque(objEntMercadoria.getEstoque() + Integer.parseInt(form[1]));
                objAtualizarMercadoria.limparFormulario();
                objAtualizarMercadoria.mensagemSucesso();
            } else {
                objAtualizarMercadoria.mensagemErro("Não existe mercadoria cadastrada com o código "+form[0]);
            }   
        }catch(Exception exc){
           objAtualizarMercadoria.mensagemErro("Verifique se voce informou todos os campos!\nO codigo deve ser numerico!");
        }        
    }
    
    public void interfaceAtualizacaoMercadorias(){
        objAtualizarMercadoria = new limAtualizacaoMercadoria(this);
    }
    
     public void interfaceListagemMercadorias(String form[][]){
        objListagemMercadorias = new limExibirMercadorias(form);
    }

    public void listarMercadorias() {
        String form[][] = new String[listaMercadorias.size()][5];
        int idx = 0;
        for (entMercadoria mercadoria : listaMercadorias) {
            form[idx][0] = ""+mercadoria.getCodigo();
            form[idx][1] = mercadoria.getDescricao();
            form[idx][2] = ""+mercadoria.getPrecoVenda();
            form[idx][3] = ""+mercadoria.getPrecoCompra();
            form[idx][4] = ""+mercadoria.getEstoque();
            idx += 1;   
        }
        interfaceListagemMercadorias(form);
    }
}
