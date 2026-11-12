public class Invoice implements Payable {
    private String descricao;
    private int quantidade;
    private double valorUnitario;

    public Invoice(String descricao, int quantidade, double valorUnitario) {
        this.descricao = descricao;
        setQuantidade(quantidade);
        setValorUnitario(valorUnitario);
    }

    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
    public double getValorUnitario() { return valorUnitario; }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new DadosInvalidosException("A quantidade da fatura deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public void setValorUnitario(double valorUnitario) {
        if (!Double.isFinite(valorUnitario) || valorUnitario < 0) {
            throw new DadosInvalidosException("O valor unitário deve ser finito e não negativo.");
        }
        this.valorUnitario = valorUnitario;
    }

    @Override
    public double getPaymentAmount() {
        return quantidade * valorUnitario;
    }
}
