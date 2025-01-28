package com.mycompany.funeraria2;

public class Funeraria {
    private String nomeCompleto;
    private String dataObito;
    private String dataNasci;
    private String tipoServico;
    private String rg;
    private String cpf;
    private String local;

    public Funeraria() {
        this.nomeCompleto = nomeCompleto;
        this.dataObito = dataObito;
        this.dataNasci = dataNasci;
        this.tipoServico = tipoServico;
        this.rg = rg;
        this.cpf = cpf;
        this.local = local;

    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDataObito() {
        return dataObito;
    }

    public void setDataObito(String dataObito) {
        this.dataObito = dataObito;
    }

    public String getDataNasci() {
        return dataNasci;
    }

    public void setDataNasci(String dataNasci) {
        this.dataNasci = dataNasci;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
