package br.com.imhere.factory;


import br.com.imhere.model.MatrizCurricular;

public class MatrizCurricularFactory {
    public static MatrizCurricular initialize() {
        MatrizCurricular t = new MatrizCurricular();
        t.setAluno(AlunoFactory.initialize());
        t.setCoordenador(CoordenadorFactory.initialize());
        t.setCurso(CursoFactory.initialize());
        t.setDisciplina(DisciplinaFactory.initialize());
        t.setProfessor(ProfessorFactory.initialize());
        t.setSemestre(SemestreFactory.initialize());
        return t;
    }
}
