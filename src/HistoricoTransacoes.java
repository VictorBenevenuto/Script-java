import java.util.ArrayDeque;
import java.util.Deque;

public class HistoricoTransacoes {

    private Deque<Payable> pilhaPagamentos = new ArrayDeque<>();
    private int pagamentosEstornados = 0;
    private double valorTotalEstornado = 0;

    // Guarda o pagamento realizado na pilha
    public void registrarPagamento(Payable pagamento) {
        pilhaPagamentos.push(pagamento);
    }

    // Desfaz os pagamentos na ordem inversa
    public void executarRollback() {
        System.out.println("\\n=== INICIANDO ROLLBACK ===");

        while (!pilhaPagamentos.isEmpty()) {
            Payable pagamento = pilhaPagamentos.pop();

            pagamentosEstornados++;
            valorTotalEstornado += pagamento.getPaymentAmount();

            System.out.printf(
                    "Pagamento estornado: R$ %.2f%n",
                    pagamento.getPaymentAmount());
        }

        System.out.println("Rollback concluído.");
    }

    // Mostra o resumo da auditoria ao final do processamento
    public void finalizarAuditoria(int pagamentosConcluidos) {
        System.out.println("\\n=== AUDITORIA FINAL ===");
        System.out.println("Pagamentos processados: " + pagamentosConcluidos);
        System.out.println("Pagamentos estornados: " + pagamentosEstornados);
        System.out.printf("Valor total estornado: R$ %.2f%n", valorTotalEstornado);
        System.out.println("Ciclo de auditoria finalizado.");
    }
}
