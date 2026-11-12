
public class SalariedEmployee implements Payable {

    private String nome;
    private double salarioMensal;

    public SalariedEmployee(String nome, double salarioMensal) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException(
                    "O nome do funcionário deve ser informado.");
        }

        if (!Double.isFinite(salarioMensal) || salarioMensal < 0) {
            throw new DadosInvalidosException(
                    "O salário mensal deve ser finito e não negativo.");
        }

        this.nome = nome;
        this.salarioMensal = salarioMensal;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public double getPaymentAmount() {
        return salarioMensal;
    }
}

