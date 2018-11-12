package br.com.imhere.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Frequencia extends DefaultEntity<Frequencia> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(value = TemporalType.DATE)
    private Date horaInicio;

    @Temporal(value = TemporalType.DATE)
    private Date horaFim;

    @ManyToOne
    private Aluno aluno;
}
