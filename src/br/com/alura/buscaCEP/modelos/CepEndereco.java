package br.com.alura.buscaCEP.modelos;

public class CepEndereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

    public CepEndereco (CepEnderecoJSON cepEnderecoAPI) {
        this.cep = cepEnderecoAPI.cep();
        this.logradouro = cepEnderecoAPI.logradouro();
        this.bairro = cepEnderecoAPI.bairro();
        this.localidade = cepEnderecoAPI.localidade();
        this.uf = cepEnderecoAPI.uf();
    }

    @Override
    public String toString() {
        return "cep: " + cep + ", logradouro: " + logradouro + ", bairro: " + bairro +
        ", localidade: " + localidade + ", uf: " + uf;
    }
}
