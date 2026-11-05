public class HourlyEmployee implements Payable {
    private String nome;
    private double valorHora;
    private double horasTrabalhadas;

    public HourlyEmployee(String nome, double valorHora, double horasTrabalhadas) {
        if (valorHora < 0 || horasTrabalhadas < 0) {
            throw new DadosInvalidosException("Valor da hora ou horas trabalhadas não podem ser negativos.");
        }
        this.nome = nome;
        this.valorHora = valorHora;
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public void registrarJornadaDiaria(String data, double horasDoDia) throws ExcessoJornadaException {
        double limiteLegal = 10.0;
        if (horasDoDia > limiteLegal) {
            throw new ExcessoJornadaException(this.nome, data, horasDoDia, limiteLegal);
        }
        this.horasTrabalhadas += horasDoDia;
    }

    @Override
    public double getPaymentAmount() {
        return valorHora * horasTrabalhadas;
    }

    public String getNome() { return nome; }
}