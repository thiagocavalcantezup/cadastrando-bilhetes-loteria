package br.com.zup.handora.cadastrandobilhetesloteria.bilhete;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;

@Entity
@Table(name = "sorteios")
public class Sorteio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false)
    @Future
    private LocalDateTime data;

    @OneToMany(mappedBy = "sorteio", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Bilhete> bilhetes = new HashSet<>();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Sorteio() {}

    public Sorteio(String descricao, @Future LocalDateTime data) {
        this.descricao = descricao;
        this.data = data;
    }

    public void adicionar(Bilhete bilhete) {
        bilhete.setSorteio(this);
        bilhetes.add(bilhete);
    }

    public Long getId() {
        return id;
    }

}
