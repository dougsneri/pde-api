package br.edu.riobrancofac.pdeapi.enums;

public enum Genero {
    M("Masculino"),
    F("Feminino"),
    O("Outro");

    private String generoCompleto;

    Genero(String generoCompleto) {
        this.generoCompleto = generoCompleto;
    }

    public String getGeneroCompleto() {
        return this.generoCompleto;
    }
}
