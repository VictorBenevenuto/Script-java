public class Main {
    public static void main(String[] args) {
        System.out.println("=== TESTE DA EXCEÇÃO DE JORNADA ===");

        try {
            HourlyEmployee funcionario = new HourlyEmployee("Carlos Silva", 50.0, 0);

            System.out.println("Tentando registrar 12 horas de trabalho...");
            funcionario.registrarJornadaDiaria("24/10/2026", 12.0);

        } catch (ExcessoJornadaException e) {
            System.out.println("\n[ERRO CAPTURADO COM SUCESSO]: " + e.getMessage());
            e.printStackTrace();
        }
    }
}