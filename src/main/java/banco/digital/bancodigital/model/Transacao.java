package banco.digital.bancodigital.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CONTA_REMETENTE")
    public Conta contaRemetente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CONTA_DESTINATARIO")
    public Conta contaDestinatario;

    @Column(name = "VALOR")
    public BigDecimal valor;

    @Column(name = "DATA_HORA")
    public String dataHora;

    @Column(name = "TIPO")
    public String tipo;

    @Transient
    public int numContaDestinatario;

    @Transient
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
