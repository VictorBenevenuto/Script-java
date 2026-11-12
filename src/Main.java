public class Main {

    public static void main(String[] args) {

        PayrollService service = new PayrollService();
        
        System.out.println("\n=== CENÁRIO 1: PROCESSAMENTO NORMAL ===");

        try {

            // Cria uma fatura com quantidade e valor válidos.
            Invoice fatura = new Invoice("Material de escritório", 2, 150.0);

            
            SalariedEmployee funcionarioMensal =
                    new SalariedEmployee("Ana Souza", 3000.0);

            // Cria um funcionário horista.
            HourlyEmployee funcionarioHorista =
                    new HourlyEmployee("Pedro Santos", 30.0, 40.0);

            // Reúne os três pagamentos em um único lote.
            Payable[] pagamentosValidos = {
                fatura,
                funcionarioMensal,
                funcionarioHorista
            };

            
            double total = service.processarPagamentos(pagamentosValidos);

            System.out.printf("Total processado: R$ %.2f%n", total);

        } catch (FalhaProcessamentoFolhaException e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }


        System.out.println("\n=== CENÁRIO 2: EXCESSO DE JORNADA ===");

        try {

            // Cria um funcionário horista sem horas registradas.
            HourlyEmployee funcionario =
                    new HourlyEmployee("Carlos Silva", 50.0, 0);

            System.out.println("Tentando registrar 12 horas de trabalho...");

            // O PayrollService recebe a solicitação e trata a exceção.
            service.registrarJornada(funcionario, "12/11/2026", 12.0);

        } catch (FalhaProcessamentoFolhaException e) {

            // Captura a exceção final lançada pela camada de serviço.
            System.out.println("[ERRO CAPTURADO]: " + e.getMessage());

            // Exibe a origem do erro na pilha de execução.
            e.printStackTrace();
        }


        System.out.println("\n=== CENÁRIO 3: TESTE DE ROLLBACK ===");

        try {

            // Cria cinco pagamentos.
            // O quarto pagamento será nulo para provocar uma falha.
            Payable[] pagamentosComErro = {

                new Invoice("Compra 1", 1, 100.0),

                new SalariedEmployee("Maria", 2500.0),

                new HourlyEmployee("João", 40.0, 20.0),

                null,

                new Invoice("Compra 2", 1, 200.0)
            };

            // Inicia o processamento do lote.
            // Quando chegar ao quarto pagamento, ocorrerá uma falha.
            service.processarPagamentos(pagamentosComErro);

        } catch (FalhaProcessamentoFolhaException e) {

            System.out.println("[ERRO CAPTURADO]: " + e.getMessage());

            e.printStackTrace();
        }

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}
