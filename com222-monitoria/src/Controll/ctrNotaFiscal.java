package Controll;

import Model.entNotaFiscal;
import Model.entVenda;
import View.limNotaFiscal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ctrNotaFiscal {
    //Declaração dos atributos
    private limNotaFiscal objLimNotaFiscal;
    private ArrayList<entNotaFiscal> listaNotaFiscal;
    private entNotaFiscal objEntNotaFiscal;
    private String arquivo;

    public ctrNotaFiscal() {
        this.listaNotaFiscal = new ArrayList<>();
        this.arquivo = "Notas_fiscais_cad.dat";
        this.objLimNotaFiscal = new limNotaFiscal();
    }
    
    public ArrayList<entNotaFiscal> getlistaNotaFiscal() {
        return listaNotaFiscal;
    }
    
    public int consultaNota (String pCod) {
        int idx = -1;
        for (entNotaFiscal notaFiscal : this.listaNotaFiscal) {
            idx += 1;
            if (notaFiscal.equals(pCod)) {
                return idx;
            }
        }
        return idx;
    }
    
    public void gerarNotaFiscal (String pCpf, String pNome) {
        String codigo = String.valueOf(UUID.randomUUID());
        objEntNotaFiscal = new entNotaFiscal(codigo, pCpf, pNome);
        this.listaNotaFiscal.add(objEntNotaFiscal);
    }
    
    public void adicionarVenda (String pCod, entVenda pVenda) throws Exception {
        int idx = this.consultaNota(pCod);
        if (idx == -1) {
            throw new Exception("Não existe nota fiscal cadastrada com o código "+pCod);
        } else {
            objEntNotaFiscal = this.listaNotaFiscal.get(idx);
            ArrayList<entVenda> listaVendas = objEntNotaFiscal.getListaVendas();
            if (listaVendas.size() < 10) {
                listaVendas.add(pVenda);
                objEntNotaFiscal.setListaVendas(listaVendas);
            } else {
                throw new Exception("Nota já possui 10 mercadorias. Não é possivel adicionar mais");
            }
        }
    }
    
    public double valorNota (entNotaFiscal nf) {
        double valorTotal = 0;
        for (entVenda venda : nf.getListaVendas()) {
            valorTotal += venda.getQtdVendido() * (venda.getMercadoria().getPrecoVenda());
        }
        return valorTotal;
    }
    
    public double fatCliente (String pCPF) {
        double valorTotal = 0;
        for (entNotaFiscal notaFisca1 : listaNotaFiscal) {
            if (pCPF.equals(notaFisca1.getCpf())) {
                valorTotal += valorNota(notaFisca1);
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
    
    public double fatPeriodoCliente (String pCpf, String pDataInicio, String pDataFim) {
        double valorTotal = 0;
        Date dataInicio, dataFim;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataInicio = formatter.parse(pDataInicio);
            dataFim = formatter.parse(pDataFim);
            if (!testaData(pDataInicio)) {
                return -1;
            }
            for (entNotaFiscal notaFisca1 : listaNotaFiscal) {
                if (pCpf.equals(notaFisca1.getCpf())) {
                    ArrayList<entVenda> listaVendas = notaFisca1.getListaVendas();
                    for (entVenda venda : listaVendas) {
                        if(dataInicio.before(venda.getData()) && dataFim.after(venda.getData())){
                            valorTotal += venda.getQtdVendido() * (venda.getMercadoria().getPrecoVenda());
                        }
                    }
                }
            }
        } catch (ParseException ex) {
        }
        return valorTotal;
    }
    
    private void serializaNota() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream(this.arquivo);
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(this.listaNotaFiscal);
        objOS.flush();
        objOS.close();
    } 
    
    private void desserializaNota() throws Exception {
        File objFile = new File(this.arquivo);
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream(arquivo);
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            this.listaNotaFiscal = (ArrayList) objIS.readObject();
            objIS.close();
        }
    }
    
    public ArrayList getListaNotas() {
        return this.listaNotaFiscal;
    }
    
    public void iniciar() throws Exception {
        this.desserializaNota();
    }
    
    public void finalize() throws Exception {
        this.serializaNota();
    }
}
