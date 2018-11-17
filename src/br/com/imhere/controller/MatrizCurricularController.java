package br.com.imhere.controller;


import br.com.imhere.listController.MatrizCurricularListController;
import br.com.imhere.model.MatrizCurricular;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class MatrizCurricularController extends Controller<MatrizCurricular> {


    public void abrirListMatrizCurricular() {
        MatrizCurricularListController list = new MatrizCurricularListController();
        list.openList(this::setEntity);
    }

    @Override
    public void limpar() {
        setEntity(new MatrizCurricular());
    }

    @Override
    public MatrizCurricular getEntity() {
        if (entity == null)
            entity = new MatrizCurricular();
        return entity;
    }

}
