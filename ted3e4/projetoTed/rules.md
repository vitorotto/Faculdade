Funcionalidades obrigatórias
O sistema deverá oferecer um menu de opções para realizar as operações bancárias.
As seguintes funcionalidades deverão ser implementadas:
1. Cadastrar conta
   Permitir cadastrar uma conta corrente ou poupança.
   Solicitar os dados comuns: número, titular e saldo inicial.
   Solicitar também os dados específicos conforme o tipo de conta.
   Armazenar os dados no banco de dados, utilizando a classe DAO.
2. Listar contas
   Consultar todas as contas cadastradas no banco.
   Exibir número, titular, saldo e tipo de conta.
   Exibir também as informações específicas de cada tipo de conta.
3. Consultar conta
   Permitir localizar uma conta pelo seu número.
   Exibir todas as informações da conta encontrada.
4. Realizar depósito
   Solicitar o número da conta e o valor do depósito.
   Atualizar o saldo da conta no banco de dados.
5. Realizar saque
   Solicitar o número da conta e o valor do saque.
   Verificar as regras de saque de cada tipo de conta.
   Atualizar o saldo no banco de dados somente se o saque for permitido.
6. Verificar o extrato de uma conta
   Permitir que os usuários verifiquem os logs das operações realizadas em suas contas bancárias
7. Excluir conta
   Permitir excluir uma conta pelo seu número.
   Garantir que os registros correspondentes nas tabelas especializadas também sejam excluídos.
5. Regras de negócio
   O sistema deverá respeitar as seguintes regras:
   Não permitir depósitos ou saques com valores iguais ou inferiores a zero.
   Uma conta corrente poderá realizar saques utilizando o saldo disponível e seu limite adicional.
   Uma conta poupança não poderá realizar saques acima do saldo disponível.
   Não permitir excluir uma conta inexistente ou com saldo diferente de zero.
   Todas as operações deverão refletir corretamente as alterações no banco de dados.
   Exemplo de funcionamento:
   Considere uma conta corrente com saldo de R$ 200,00 e limite de R$ 300,00.
   Saque de R$ 150,00: permitido.
   Saque de R$ 300,00: permitido.
   Saque de R$ 400,00: permitido.
   Saque de R$ 351,00: não permitido.
   O limite representa um valor adicional que pode ser utilizado quando o saldo da conta não for suficiente.
6. Requisitos técnicos
   Durante o desenvolvimento, deverão ser observados os seguintes requisitos:
   Utilizar JDBC para realizar a comunicação entre Java e MySQL.
   Utilizar PreparedStatement para executar comandos SQL.
   Utilizar um arquivo db.properties para armazenar as configurações de conexão.
   Implementar uma classe responsável pela conexão com o banco de dados.
   Implementar a classe ContaDAO para centralizar as operações de persistência.
   Utilizar os métodos executeUpdate() e executeQuery() adequadamente.
   Recuperar o identificador gerado automaticamente pelo banco de dados ao cadastrar uma conta.
   Utilizar transações JDBC (commit e rollback) nas operações que envolvam mais de uma tabela (preferencialmente, se não viu esses conceitos ainda pode ignorar essa parte).
   Utilizar try-with-resources para gerenciar o fechamento de conexões, comandos e resultados.
   Manter a separação entre as classes de modelo, persistência e interface do sistema.
   Importante: as operações de depósito e saque deverão atualizar o saldo diretamente no banco de dados. Não basta alterar o atributo do objeto em memória.
7. Testes obrigatórios
   Antes de finalizar a atividade, realize os seguintes testes:
   Cadastrar uma conta corrente.
   Cadastrar uma conta poupança.
   Listar todas as contas cadastradas.
   Consultar uma conta pelo número.
   Realizar um depósito.
   Realizar um saque permitido.
   Tentar realizar um saque não permitido.
   Tentar cadastrar duas contas com o mesmo número.
   Excluir uma conta.
   Encerrar o programa, executar novamente e verificar se as contas continuam cadastradas e com os saldos atualizados.
8. Entrega
   Deverá ser entregue:
   Projeto Java completo, contendo os pacotes e classes implementados.
   Arquivo SQL contendo a criação do banco de dados e das tabelas.
   Um pequeno documento ou arquivo README contendo as instruções necessárias para configurar e executar o projeto.