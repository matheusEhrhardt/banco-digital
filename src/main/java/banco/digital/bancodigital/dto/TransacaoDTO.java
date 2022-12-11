package banco.digital.bancodigital.dto;

import banco.digital.bancodigital.model.Conta;
import jakarta.persistence.Transient;

import java.math.BigDecimal;

public class TransacaoDTO {

    public Integer id;

    public Conta contaRemetente;

    public Conta contaDestinatario;

    public BigDecimal valor;

    public String dataHora;

    public String tipo;

    public int numContaDestinatario;

    public int numContaRemetente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Conta getContaRemetente() {
        return contaRemetente;
    }

    public void setContaRemetente(Conta contaRemetente) {
        this.contaRemetente = contaRemetente;
    }

    public Conta getContaDestinatario() {
        return contaDestinatario;
    }

    public void setContaDestinatario(Conta contaDestinatario) {
        this.contaDestinatario = contaDestinatario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumContaDestinatario() {
        return numContaDestinatario;
    }

    public void setNumContaDestinatario(int numContaDestinatario) {
        this.numContaDestinatario = numContaDestinatario;
    }

    public int getNumContaRemetente() {
        return numContaRemetente;
    }

    public void setNumContaRemetente(int numContaRemetente) {
        this.numContaRemetente = numContaRemetente;
    }
}
