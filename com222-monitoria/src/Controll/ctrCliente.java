package Controll;

import Model.entCliente;
import View.limCliente;
import java.io.*;
import java.util.ArrayList;

public class ctrCliente {
    //Declaração dos atributos
    private limCliente objLimCliente;
    ArrayList<entCliente> listaClientes;
    private entCliente objEntCliente;
    private String arquivo;
    
    public ctrCliente() {
        this.listaClientes = new ArrayList<>();
        this.arquivo = "Clientes_cad.dat";
        this.objLimCliente = new limCliente();
    }
    
    public boolean cadastrarCliente(String pCpf, String pNome,  String pEmail, String pCep, String pCidade, String pRua, String pNumero) {
        objEntCliente = new entCliente();
        objEntCliente.setCpf(pCpf);
        if (!this.CPF_Val(objEntCliente.getCpf())){
            //objALimCliente.montaFormaCpfInvalido();
            return true;
        }   
        objEntCliente.setEmail(pEmail);
        objEntCliente.setNome(pNome);
        objEntCliente.setEndereco (pCep, pCidade, pRua, pNumero);
        this.addLista(objEntCliente);
        //objALimCliente.montaFormaClienteAdd();
        return true;
    }
    
    public boolean CPF_Val(String pCpfClinte) {
        for (entCliente cliente : this.listaClientes) {
            if(pCpfClinte.equals(cliente.getCpf()))
                return false;
        }
        return true;
    }
    
    public void addLista(entCliente pCliente) {
        listaClientes.add(pCliente);
    }

    private void serializaCliente() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(this.arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(this.listaClientes);
        objOS.flush();
        objOS.close();
    } 
    
    private void desserializaCliente() throws Exception {
        File objFile = new File(this.arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            this.listaClientes = (ArrayList) objIS.readObject();
            objIS.close();
        }
    }
    
    public ArrayList getListaClientes() {
        return this.listaClientes;
    }
    
    public void iniciar() throws Exception {
        this.desserializaCliente();
    }
    
    public void finalize() throws Exception {
        this.serializaCliente();
    }
    
     public void consultarCPF() {
        String cpf = "";
        String result = "";
        //limite para pegar cpf
        for (entCliente cliente : this.listaClientes) {
            if(cpf.equals(cliente.getCpf())) {
                result +=  "<TR>"+"<TD>"+cliente.getCpf()+ "</TD><TD>" +  cliente.getNome()+ "</TD><TD>" + cliente.getEmail()+"</TD></TR>";
            }
        }
        if (result.equals("")) {
             result = "<CENTER><FONT COLOR=RED SIZE=8>NENHUM CLIENTE CADASTRADO</FONT></CENTER><TABLE BORDER=1> ";
        } else {
            result =  "<CENTER><FONT COLOR=BLUE SIZE=6>Todos os clientes cadastrados</FONT></CENTER><TABLE BORDER=1> "
                    + "<TR>"
                    + "<TD>Cpf </TD> "
                    + "<TD>Nome </TD>"
                    + "<TD>e-mail </TD>"
                    + " </TR>"
                    + result
                    +"</TABLE>";
        }
        //imprima result
     }
    public String imprimeClientes() {
        String result = "";
        if (this.listaClientes.size() == 0)
           result+="<CENTER><FONT COLOR=RED SIZE=8>NENHUM CLIENTE CADASTRADO</FONT></CENTER><TABLE BORDER=1> ";
        else{
            result += "<CENTER><FONT COLOR=BLUE SIZE=6>Todos os clientes cadastrados</FONT></CENTER><TABLE BORDER=1> "
                    + "<TR>"
                    + "<TD>Cpf </TD> "
                    + "<TD>Nome </TD>"
                    + "<TD>e-mail </TD>"
                    + " </TR>";
            for (entCliente cliente : this.listaClientes) {
                result +=  "<TR>"+"<TD>"+cliente.getCpf()+ "</TD><TD>" +  cliente.getNome()+ "</TD><TD>" + cliente.getEmail()+"</TD></TR>";
            }
            result += "</TABLE>";
        }
        return result;
    }
}


 