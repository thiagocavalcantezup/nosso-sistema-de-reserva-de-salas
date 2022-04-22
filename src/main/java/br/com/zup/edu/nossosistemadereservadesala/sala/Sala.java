package br.com.zup.edu.nossosistemadereservadesala.sala;

import static br.com.zup.edu.nossosistemadereservadesala.sala.StatusOcupacao.LIVRE;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import br.com.zup.edu.nossosistemadereservadesala.exceptions.SalaNaoLivreException;

@Entity
@Table(name = "salas")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusOcupacao status = LIVRE;

    @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Sala() {}

    public Sala(String nome) {
        this.nome = nome;
    }

    public void reservar() {
        if (!isLivre()) {
            throw new SalaNaoLivreException("A sala não está livre para ser reservada.");
        }

        status = StatusOcupacao.OCUPADO;
        atualizadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public boolean isLivre() {
        return status.equals(StatusOcupacao.LIVRE);
    }

}
