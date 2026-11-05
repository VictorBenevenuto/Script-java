public class ExcessoJornadaException extends Exception {
    private String nomeColaborador;
    private String data;
    private double horasLancadas;
    private double limiteLegal;

    public ExcessoJornadaException(String nomeColaborador, String data, double horasLancadas, double limiteLegal) {
        super(String.format("Colaborador %s ultrapassou o limite em %s: lançou %.1f horas (Limite legal: %.1f horas)",
                nomeColaborador, data, horasLancadas, limiteLegal));
        this.nomeColaborador = nomeColaborador;
        this.data = data;
        this.horasLancadas = horasLancadas;
        this.limiteLegal = limiteLegal;
    }

    public String getNomeColaborador() { return nomeColaborador; }
    public String getData() { return data; }
    public double getHorasLancadas() { return horasLancadas; }
    public double getLimiteLegal() { return limiteLegal; }
}