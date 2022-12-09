package banco.digital.bancodigital.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NUMERO")
    private int numero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_USUARIO")
    private Usuario usuario;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    @Column(name = "TIPO")
    private String tipoConta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }    
    
}
