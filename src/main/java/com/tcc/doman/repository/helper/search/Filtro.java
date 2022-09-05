package com.tcc.doman.repository.helper.search;

public class Filtro {

    public static final String[] SIMPLE_OPERATION_SET = {":", "!", ">", "<", "~", "!IN"};

    private OperadorLogico operadorLogico;
    private String atributo;
    private OperadorRelacional operadorRelacional;
    private Object valor;
    private Object[] valores;


    public Filtro(OperadorLogico operadorLogico, String atributo, OperadorRelacional operadorRelacional, Object valor) {
        this.operadorLogico = operadorLogico;
        this.atributo = atributo;
        this.operadorRelacional = operadorRelacional;
        this.valor = valor;
    }

    public Filtro(String atributo, OperadorRelacional operadorRelacional, Object valor) {
        this.operadorLogico = OperadorLogico.AND;
        this.atributo = atributo;
        this.operadorRelacional = operadorRelacional;
        this.valor = valor;
    }

    public Filtro(String atributo, Object valor) {
        this.operadorLogico = OperadorLogico.AND;
        this.atributo = atributo;
        this.operadorRelacional = OperadorRelacional.IGUAL;
        this.valor = valor;
    }


    public OperadorLogico getOperadorLogico() {
        return operadorLogico;
    }

    public void setOperadorLogico(OperadorLogico operadorLogico) {
        this.operadorLogico = operadorLogico;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public OperadorRelacional getOperadorRelacional() {
        return operadorRelacional;
    }

    public void setOperadorRelacional(OperadorRelacional operadorRelacional) {
        this.operadorRelacional = operadorRelacional;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
}
