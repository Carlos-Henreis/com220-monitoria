package Model;

import java.io.Serializable;

public class entCliente implements Serializable {
    //Declaração dos atributos
    private String cpf;
    private String email;
    private String nome;
    private String endereco[];//CEP, Cidade, Rua, Numero;
    
    //construtor
    public entCliente (String pEmail,String pCEP, String pCidade, String pRua,String pNumero, String pNome, String pCpf){
       this.endereco = new String[4];
       this.setEmail (pEmail);
       this.setEndereco (pCEP, pCidade, pRua, pNumero);
       this.setNome (pNome);
       this.setCpf (pCpf);
    }

    public entCliente() {
        
    }
    //gets e seters
    public void setEmail (String pEmail) {
        this.email = pEmail;
    }
    public void setEndereco (String pCEP, String pCidade, String pRua,String pNumero) {
        this.endereco[0] = pCEP;
        this.endereco[1] = pCidade;
        this.endereco[2] = pRua;
        this.endereco[3] = pNumero;
    }
     public void setNome (String pNome) {
        this.nome = pNome;
    }
    
    public void setCpf (String pCpf){
        this.cpf = pCpf;
    }
     
    public String getEmail () {
       return this.email;
    }
    public String[] getEndereco () {
        return this.endereco;
    }
     public String getNome () {
         return this.nome ;
    }
     
    public String getCpf (){
        return this.cpf;
    }
}
