package br.com.zup.handora.cadastrandobilhetesloteria.bilhete;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bilhetes", uniqueConstraints = {
        @UniqueConstraint(name = "UK_BILHETE_TELEFONE_NUMERO_SORTEIO", columnNames = {
                "hashTelefoneCelular", "numeroDaSorte", "sorteio_id"})})
public class Bilhete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefoneCelular;

    @Column(nullable = false)
    private byte[] hashTelefoneCelular;

    @ManyToOne(optional = false)
    private Sorteio sorteio;

    @Column(nullable = false)
    private Integer numeroDaSorte;

    @Column(nullable = false)
    private LocalDateTime dataRegistro = LocalDateTime.now();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Bilhete() {}

    public Bilhete(String nome, String telefoneCelular, Integer numeroDaSorte) {
        this.nome = nome;
        this.telefoneCelular = TelefoneUtils.anonymize(telefoneCelular);
        this.hashTelefoneCelular = TelefoneUtils.hash(telefoneCelular);
        this.numeroDaSorte = numeroDaSorte;
    }

    public Long getId() {
        return id;
    }

    public byte[] getHashTelefoneCelular() {
        return hashTelefoneCelular;
    }

    public void setSorteio(Sorteio sorteio) {
        this.sorteio = sorteio;
    }

}
