package br.com.brunoarouca.twagendafx.repositorios;

import br.com.brunoarouca.twagendafx.entidades.Contato;
import br.com.brunoarouca.twagendafx.fabricas.FabricaConexaoJdbc;
import br.com.brunoarouca.twagendafx.repositorios.interfaces.AgendaRepositorio;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepositorioJdbc implements AgendaRepositorio<Contato> {
    @Override
    public List<Contato> selecionar() throws SQLException, IOException {

        List<Contato> contatos = new ArrayList<Contato>();
        try (Connection conexao = FabricaConexaoJdbc.criarConexao()){
            Statement comando = conexao.createStatement();
            ResultSet rs = comando.executeQuery("SELECT * FROM contatos");
            while (rs.next()){
                Contato contato = new Contato();
                contato.setId(rs.getInt("id"));
                contato.setIdade(rs.getInt("idade"));
                contato.setNome(rs.getString("nome"));
                contato.setTelefone(rs.getString("telefone"));
                contatos.add(contato);
            }
        }


        return contatos;
    }

    @Override
    public void inserir(Contato entidade) throws SQLException, IOException {
        Connection conexao = null;
        
        try {
            conexao = FabricaConexaoJdbc.criarConexao();
            PreparedStatement comando = conexao.prepareStatement(
                    "INSERT INTO contatos (nome, idade, telefone) VALUES (?, ?, ?);"
            );
            comando.setString(1, entidade.getNome());
            comando.setInt(2, entidade.getIdade());
            comando.setString(3, entidade.getTelefone());
            comando.execute();

        } finally {
            if (conexao != null){
                conexao.close();
            }
        }
    }

    @Override
    public void atualizar(Contato entidade) {

    }

    @Override
    public void excluir(Contato entidade) {

    }
}
