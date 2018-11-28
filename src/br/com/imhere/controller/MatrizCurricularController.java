package br.com.imhere.controller;


import br.com.imhere.factory.JPAFactory;
import br.com.imhere.factory.MatrizCurricularFactory;
import br.com.imhere.listController.MatrizCurricularListController;
import br.com.imhere.model.*;
import br.com.imhere.repository.*;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class MatrizCurricularController extends Controller<MatrizCurricular> {

    private List<Curso> cursoList;
    private List<Disciplina> disciplinaList;

    @PostConstruct
    public void init() {
        CursoRepository cursoRepository = new CursoRepository(getEntityManager());
        DisciplinaRepository disciplinaRepository = new DisciplinaRepository(getEntityManager());

        setCursoList(cursoRepository.lista());
        setDisciplinaList(disciplinaRepository.lista());
    }

    public void abrirListMatrizCurricular() {
        MatrizCurricularListController list = new MatrizCurricularListController();
        list.openList(this::setEntity);
    }

    public List<Aluno> completeAluno(String query) {
        AlunoRepository repository = new AlunoRepository(JPAFactory.getEntityManager());
        return repository.buscarComplete(query, 5);
    }

    public List<Professor> completeProfessor(String query) {
        ProfessorRepository repository = new ProfessorRepository(JPAFactory.getEntityManager());
        return repository.buscarComplete(query, 5);
    }

    public List<Coordenador> completeCoordenador(String query) {
        CoordenadorRepository repository = new CoordenadorRepository(JPAFactory.getEntityManager());
        return repository.buscarComplete(query, 5);
    }

    @Override
    public void limpar() {
        setEntity(new MatrizCurricular());
    }

    @Override
    public MatrizCurricular getEntity() {
        if (entity == null)
            entity = MatrizCurricularFactory.initialize();
        return entity;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public List<Disciplina> getDisciplinaList() {
        return disciplinaList;
    }

    public void setDisciplinaList(List<Disciplina> disciplinaList) {
        this.disciplinaList = disciplinaList;
    }
}
