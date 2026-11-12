import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PayrollService {
    private final List<String> registrosAuditoria = new ArrayList<>();

    // O Main deve chamar este método para que a exceção passe pela camada de serviço.
    public void registrarJornada(HourlyEmployee funcionario, String data, double horasDoDia)
            throws FalhaProcessamentoFolhaException {
        if (funcionario == null) {
            throw new DadosInvalidosException("O funcionário deve ser informado.");
        }
        if (!Double.isFinite(horasDoDia) || horasDoDia < 0) {
            throw new DadosInvalidosException("As horas devem ser finitas e não negativas.");
        }

        try {
            funcionario.registrarJornadaDiaria(data, horasDoDia);
        } catch (ExcessoJornadaException e) {
            String registro = "Pendência trabalhista: " + e.getMessage();
            registrosAuditoria.add(registro);
            System.out.println("[AUDITORIA] " + registro);
            throw new FalhaProcessamentoFolhaException(
                    "Falha ao registrar a jornada de " + funcionario.getNome() + ".", e);
        }
    }

    // Simula os pagamentos. O histórico de transações e o rollback pertencem à parte 4.
public double processarPagamentos(Payable[] pagamentos)
        throws FalhaProcessamentoFolhaException {

    HistoricoTransacoes historico = new HistoricoTransacoes();

    double total = 0;
    int concluidos = 0;

    try {
        if (pagamentos == null) {
            throw new DadosInvalidosException(
                    "O lote de pagamentos deve ser informado.");
        }

        System.out.println("=== PROCESSAMENTO DA FOLHA ===");

        for (int i = 0; i < pagamentos.length; i++) {

            if (pagamentos[i] == null) {
                throw new DadosInvalidosException(
                        "O pagamento " + (i + 1) + " não pode ser nulo.");
            }

            double valor = pagamentos[i].getPaymentAmount();

            if (!Double.isFinite(valor) || valor < 0
                    || !Double.isFinite(total + valor)) {
                throw new DadosInvalidosException(
                        "Valor de pagamento ou total do lote inválido.");
            }

            total += valor;

            historico.registrarPagamento(pagamentos[i]);

            concluidos++;

            System.out.printf(
                    "Pagamento %d processado: R$ %.2f%n",
                    i + 1, valor);
        }

        System.out.printf("Total da folha: R$ %.2f%n", total);
        return total;

    } catch (RuntimeException e) {

        System.out.println("\n[FALHA] Erro durante o processamento.");
        historico.executarRollback();

        throw new FalhaProcessamentoFolhaException(
                "Falha ao processar o lote de pagamentos.", e);

    } finally {
        historico.finalizarAuditoria(concluidos);
    }
}

    public List<String> getRegistrosAuditoria() {
        return Collections.unmodifiableList(new ArrayList<>(registrosAuditoria));
    }
}