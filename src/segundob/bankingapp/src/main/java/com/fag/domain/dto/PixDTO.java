package com.fag.domain.dto;

public class PixDTO {
    private String chavePix;
    private Double valor;

    public PixDTO(String chavePix, Double valor) {
        this.chavePix = chavePix;
        this.valor = valor;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}