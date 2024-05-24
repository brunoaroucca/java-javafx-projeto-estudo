package br.com.brunoarouca.twagendafx.repositorios;

import br.com.brunoarouca.twagendafx.entidades.Contato;
import br.com.brunoarouca.twagendafx.repositorios.interfaces.AgendaRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContatoRepositorio implements AgendaRepositorio<Contato> {
    private static List<Contato> contatos = new ArrayList<Contato>();

    @Override
    public List<Contato> selecionar() {
        return contatos;
    }

    @Override
    public void inserir(Contato entidade) {
        contatos.add(entidade);
    }

    @Override
    public void atualizar(Contato entidade) {
        Optional<Contato> original = contatos.stream().filter((contato) -> contato.getNome().equals(entidade.getNome())).findFirst();
        if(original.isPresent()){
            Contato contatoAtualizar = original.get();

            contatoAtualizar.setIdade(entidade.getIdade());
            contatoAtualizar.setTelefone(entidade.getTelefone());

        }
    }

    @Override
    public void excluir(Contato entidade) {
        contatos.remove(entidade);
    }
}
