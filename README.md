# Projeto de Estudo: Agenda de Contatos em JavaFX

Este projeto foi desenvolvido como parte de um estudo sobre o framework JavaFX. O objetivo principal é criar uma aplicação simples de agenda de contatos, utilizando FXML, SceneBuilder e conceitos de Repositories e Entidades.

## Tecnologias Utilizadas

- **JavaFX**: Framework para construção de interfaces gráficas em Java.
- **FXML**: Linguagem de marcação XML usada para construir a interface do usuário separadamente da lógica de aplicação.
- **SceneBuilder**: Ferramenta visual para criação de arquivos FXML.
- **Java SE 21**: Versão do JDK utilizada para o desenvolvimento.

## Funcionalidades

- **Adicionar Contato**: Adicione novos contatos à agenda.
- **Editar Contato**: Edite informações de contatos existentes.
- **Excluir Contato**: Remova contatos da agenda.
- **Listar Contatos**: Visualize todos os contatos cadastrados.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- **src/main/java**: Contém o código fonte Java.
    - **br/com/brunoarouca/twagendafx**: Pacote principal da aplicação.
        - **Main.java**: Classe principal que inicia a aplicação.
        - **MainController.java**: Controlador da interface principal.
        - **repositories**: Contém as classes de repositório responsáveis pela persistência dos dados.
        - **entities**: Contém as classes de entidade que representam os dados de contato.
- **src/main/resources**: Contém os recursos da aplicação.
    - **fxml**: Arquivos FXML usados para definir a interface gráfica.
    - **css**: Arquivos CSS para estilização da interface (se necessário).

## Como Executar

Para executar a aplicação, siga os seguintes passos:

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seuusuario/tw-agenda-fx.git
