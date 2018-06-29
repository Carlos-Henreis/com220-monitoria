package Model;

import java.io.Serializable;

public class entMercadoria implements Serializable {
    private int codigo;
    private String descricao;
    private double precoVenda;
    private double precoCompra;
    private int estoque;

    public entMercadoria(int pCodigo, String pDescricao, double pPrecoVenda, double pPrecoCompra, int pEstoque) {
        this.setCodigo(pCodigo);
        this.setDescricao(pDescricao);
        this.setPrecoCompra(pPrecoCompra);
        this.setPrecoVenda(pPrecoVenda);
        this.setEstoque(pEstoque);
    }
    
   public entMercadoria() {
   
   }
    
    public int getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(int pCodigo) {
        this.codigo = pCodigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String pDescricao) {
        this.descricao = pDescricao;
    }

    public double getPrecoVenda() {
        return this.precoVenda;
    }

    public void setPrecoVenda(double pPrecoVenda) {
        this.precoVenda = pPrecoVenda;
    }

    public double getPrecoCompra() {
        return this.precoCompra;
    }

    public void setPrecoCompra(double pPrecoCompra) {
        this.precoCompra = pPrecoCompra;
    }

    public int getEstoque() {
        return this.estoque;
    }

    public void setEstoque(int pEstoque) {
        this.estoque = pEstoque;
    }
}
