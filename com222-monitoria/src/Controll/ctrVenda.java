package Controll;
import Model.entMercadoria;
import Model.entVenda;
import View.limVenda;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ctrVenda {
    //Declaração dos atributos
    ArrayList<entVenda> listaVendas;
    private limVenda objLimVenda;
    private entVenda objEntVenda;
    private String arquivo;

    public ctrVenda() {
        this.listaVendas = new ArrayList<>();
        this.arquivo = "vendas_cad.dat";
        this.objLimVenda = new limVenda();
    }
    
    public entVenda efetuarVenda (int pQtd, entMercadoria pMercadoria, Date data) {
        objEntVenda = new entVenda(pMercadoria, pQtd, data);
        this.listaVendas.add(objEntVenda);
        return objEntVenda;
    }
    
    public double fatProduto (int pCod) {
        double valorTotal = 0;
        for (entVenda venda : listaVendas) {
            if (venda.getMercadoria().getCodigo() == pCod) {
                valorTotal += venda.getMercadoria().getPrecoVenda() * venda.getQtdVendido();
            }
        }
        return valorTotal;
    }
    
    public boolean testaData (String pData){
        try{
            Date hoje = new Date();
            Date data;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            data = formatter.parse(pData);
            if (0<(int) ((hoje.getTime() - data.getTime()) / 86400000L)){
                //objLimReserva.montaFormaDataIn(hoje);
                return false;
            }
        }catch (ParseException e) {      
        } 
         return true;        
    }
    
    public double fatPeriodo (String pDataInicio, String pDataFim) {
        double valorTotal = 0;
        Date dataInicio, dataFim;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataInicio = formatter.parse(pDataInicio);
            dataFim = formatter.parse(pDataFim);
            if (!testaData(pDataInicio)) {
                return -1;
            }
            for (entVenda venda : listaVendas) {
                if(dataInicio.before(venda.getData()) && dataFim.after(venda.getData())){
                    valorTotal += venda.getQtdVendido() * (venda.getMercadoria().getPrecoVenda());
                }
            }
        } catch (ParseException ex) {
        }
        return valorTotal;
    }
    
    public double LucroLiqPeriodo (String pDataInicio, String pDataFim) {
        double valorTotal = 0;
        Date dataInicio, dataFim;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataInicio = formatter.parse(pDataInicio);
            dataFim = formatter.parse(pDataFim);
            if (!testaData(pDataInicio)) {
                return -1;
            }
            for (entVenda venda : listaVendas) {
                if(dataInicio.before(venda.getData()) && dataFim.after(venda.getData())){
                    valorTotal += venda.getQtdVendido() * (venda.getMercadoria().getPrecoVenda() - venda.getMercadoria().getPrecoCompra());
                }
            }
        } catch (ParseException ex) {
        }
        return valorTotal;
    }
    
    public String top10Prod () {
        String retorno = "";
        ArrayList<entMercadoria> listaProd = new ArrayList<>();
        ArrayList<Integer> qtd = new  ArrayList<>();
        for (entVenda venda : listaVendas) {
            if (listaProd.size() == 0) {
                listaProd.add(venda.getMercadoria());
                qtd.add(venda.getQtdVendido());
            } else {
                int idx = 0, flag = 0;
                for (entMercadoria mercas : listaProd) {
                    if (mercas.getCodigo() == venda.getMercadoria().getCodigo()) {
                        qtd.set(idx, qtd.get(idx)+ 1);
                        flag = 1;
                    }
                    idx+=1; 
                }
                if (flag == 0) {
                    listaProd.add(venda.getMercadoria());
                    qtd.add(venda.getQtdVendido());
                }
            }
        }
        retorno += "Top 10 mercadorias";
        int idx = 0;
        while (idx < 10 || idx < qtd.size()) {            
            retorno += "Mercadoria-"+idx;
            retorno += "\n Código:"+listaProd.get(idx).getCodigo();
            retorno += "\n Descrição:"+listaProd.get(idx).getDescricao();
            retorno += "\n Preço de Venda:"+listaProd.get(idx).getPrecoVenda();
            retorno += "\n Total vendido: "+qtd.get(idx);
        }
        return retorno;
    }
    
    private void serializaVenda() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(this.arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(this.listaVendas);
        objOS.flush();
        objOS.close();
    } 
    
    private void desserializaVenda() throws Exception {
        File objFile = new File(this.arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            this.listaVendas = (ArrayList) objIS.readObject();
            objIS.close();
        }
    }
    
    public ArrayList getListaVendas() {
        return this.listaVendas;
    }
    
    public void finalize() throws Exception {
        this.serializaVenda();
    }
    
    public void iniciar() throws Exception {
        this.desserializaVenda();
    }
}
    