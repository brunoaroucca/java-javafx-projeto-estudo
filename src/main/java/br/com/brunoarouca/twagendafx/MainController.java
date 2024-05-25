package br.com.brunoarouca.twagendafx;

import br.com.brunoarouca.twagendafx.entidades.Contato;
import br.com.brunoarouca.twagendafx.repositorios.ContatoRepositorio;
import br.com.brunoarouca.twagendafx.repositorios.ContatoRepositorioJdbc;
import br.com.brunoarouca.twagendafx.repositorios.interfaces.AgendaRepositorio;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Contato> tabelaContatos;

    @FXML
    private Button botaoInserir;
    @FXML
    private Button botaoAlterar;
    @FXML
    private Button botaoExcluir;

    @FXML
    private TextField txfNome;
    @FXML
    private TextField txfIdade;
    @FXML
    private TextField txfTelefone;

    @FXML
    private Button botaoSalvar;
    @FXML
    private Button botaoCancelar;

    private Boolean ehInserir;
    private Contato contatoSelecionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tabelaContatos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        habilitarEdicaoAgenda(false);
//        this.tabelaContatos.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contato>() {
//            @Override
//            public void changed(ObservableValue<? extends Contato> observableValue, Contato contato, Contato t1) {
//                if(t1 != null){
//                    txfNome.setText(t1.getNome());
//                    txfIdade.setText(String.valueOf(t1.getIdade()));
//                    txfTelefone.setText(t1.getTelefone());
//                }
//            }
//        });
        this.tabelaContatos.getSelectionModel().selectedItemProperty().addListener((observador, contatoAntigo, contatoNovo) -> {
            if(contatoNovo != null){
                txfNome.setText(contatoNovo.getNome());
                txfIdade.setText(String.valueOf(contatoNovo.getIdade()));
                txfTelefone.setText(contatoNovo.getTelefone());
                this.contatoSelecionado = contatoNovo;
            }
        });
        carregarTabelaContatos();
    }

    public void botaoInserir_Action(){
        this.ehInserir = true;
        this.txfNome.setText("");
        this.txfIdade.setText("");
        this.txfTelefone.setText("");
        habilitarEdicaoAgenda(true);

    }
    public void botaoAlterar_Action(){
        habilitarEdicaoAgenda(true);
        this.ehInserir = false;
        this.txfNome.setText(this.contatoSelecionado.getNome());
        this.txfIdade.setText(String.valueOf(this.contatoSelecionado.getIdade()));
        this.txfTelefone.setText(this.contatoSelecionado.getTelefone());
    }
    public void botaoCancelar_Action(){
        habilitarEdicaoAgenda(false);
        this.tabelaContatos.getSelectionModel().selectFirst();
    }
    public void botaoSalvar_Action(){
        try {
            AgendaRepositorio<Contato> repositorioContato = new ContatoRepositorioJdbc();
            Contato contato = new Contato();
            contato.setNome(txfNome.getText());
            contato.setIdade(Integer.parseInt(txfIdade.getText()));
            contato.setTelefone(txfTelefone.getText());

            if (this.ehInserir){
                repositorioContato.inserir(contato);
            } else {
                repositorioContato.atualizar(contato);
            }
            habilitarEdicaoAgenda(false);
            carregarTabelaContatos();
            this.tabelaContatos.getSelectionModel().selectFirst();
        } catch (Exception e){
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Erro");
            mensagem.setHeaderText("Erro no banco de dados");
            mensagem.setContentText("houve um erro ao manipular o contato: " + e.getMessage());
            mensagem.showAndWait();
        }

    }
    public void botaoExcluir_Action(){
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("Confirmação da exclusão do contato");
        confirmacao.setContentText("Tem certeza que deseja excluir esse contato?");
        Optional<ButtonType> resultadoConfirmacao = confirmacao.showAndWait();

        if(resultadoConfirmacao.isPresent() && resultadoConfirmacao.get() == ButtonType.OK){
            AgendaRepositorio<Contato> repositorioContato = new ContatoRepositorio();
            repositorioContato.excluir(contatoSelecionado);
            carregarTabelaContatos();
            this.tabelaContatos.getSelectionModel().selectFirst();
        }


    }
    private void carregarTabelaContatos(){
        try {
            AgendaRepositorio<Contato> repositorioContato = new ContatoRepositorioJdbc();
            List<Contato> contatos = repositorioContato.selecionar();
            ObservableList<Contato> contatoObservableList = FXCollections.observableArrayList(contatos);
            this.tabelaContatos.getItems().setAll(contatoObservableList);
        } catch (Exception e){
            Alert mensagem = new Alert(Alert.AlertType.ERROR);
            mensagem.setTitle("Erro");
            mensagem.setHeaderText("Erro no banco de dados");
            mensagem.setContentText("houve um erro ao obter a lista de contatos: " + e.getMessage());
            mensagem.showAndWait();
        }

    }

    private void habilitarEdicaoAgenda(Boolean edicaoEstaHabilitada){
        this.txfNome.setDisable(!edicaoEstaHabilitada);
        this.txfIdade.setDisable(!edicaoEstaHabilitada);
        this.txfTelefone.setDisable(!edicaoEstaHabilitada);

        this.botaoSalvar.setDisable(!edicaoEstaHabilitada);
        this.botaoCancelar.setDisable(!edicaoEstaHabilitada);

        this.botaoInserir.setDisable(edicaoEstaHabilitada);
        this.botaoAlterar.setDisable(edicaoEstaHabilitada);
        this.botaoExcluir.setDisable(edicaoEstaHabilitada);
        this.tabelaContatos.setDisable(edicaoEstaHabilitada);

    }
}