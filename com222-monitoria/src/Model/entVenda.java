package Model;

import java.io.Serializable;
import java.util.Date;

public class entVenda implements Serializable {
    private entMercadoria mercadoria;
    private int qtdVendido;
    private Date data;

    public entVenda() {
    }

    public entVenda(entMercadoria pMercadoria, int pQdVendido, Date pData) {
        this.mercadoria = pMercadoria;
        this.qtdVendido = pQdVendido;
        this.data = pData;
    }
    
     public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public entMercadoria getMercadoria() {
        return mercadoria;
    }

    public void setMercadoria(entMercadoria mercadoria) {
        this.mercadoria = mercadoria;
    }

    public int getQtdVendido() {
        return qtdVendido;
    }

    public void setQtdVendido(int qtdVendido) {
        this.qtdVendido = qtdVendido;
    }
}
