package br.com.brunoarouca.twagendafx.repositorios.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface AgendaRepositorio<T> {
    List<T> selecionar() throws SQLException, IOException;
    void inserir(T entidade) throws SQLException, IOException;
    void atualizar(T entidade);
    void excluir(T entidade);
}
