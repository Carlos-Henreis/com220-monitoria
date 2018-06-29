package Model;
import java.io.Serializable;
import java.util.ArrayList;

public class entNotaFiscal implements Serializable {
    private String codigo;
    private String cpf;
    private String nome;
    private ArrayList<entVenda> listaVendas;

    public entNotaFiscal(String pCod, String pCpf, String pNome) {
        this.setCodigo(pCod);
        this.setCpf(pCpf);
        this.setNome(pNome);
        this.listaVendas = new ArrayList<>();
    }
    
    public entNotaFiscal () {
      
    }
       
    public String getCodigo () {
        return this.codigo;
    }
    
    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<entVenda> getListaVendas() {
        return this.listaVendas;
    }
    
    public void setCodigo(String pCodigo) {
        this.codigo = pCodigo;
    }
    
    public void setCpf(String pCpf) {
        this.cpf = pCpf;
    }
    public void setListaVendas(ArrayList<entVenda> pListaVendas) {
        this.listaVendas = pListaVendas;
    }

    public void setNome(String pNome) {
        this.nome = pNome;
    }
}