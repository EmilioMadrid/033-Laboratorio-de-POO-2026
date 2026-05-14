package javaapplication2;

public class Contrato {
    private double salarioMensual;
    private int vigenciaMeses;
    protected String tipoContrato;

    public Contrato(double salario, int vigencia, String tipoContrato) {
        setSalarioMensual(salario);
        setVigenciaMeses(vigencia);
        this.tipoContrato = tipoContrato;
    }

    public double getSalarioMensual() {
        return salarioMensual;
    }

    public void setSalarioMensual(double salarioMensual) {
        if (salarioMensual >= 0.0 && salarioMensual <= 5000000)
            this.salarioMensual = salarioMensual;
        else {
            this.salarioMensual = 0;
            System.out.println("Salario invalido, $0.00 asignado por defecto");
        }
    }

    public int getVigenciaMeses() {
        return vigenciaMeses;
    }

    public void setVigenciaMeses(int vigenciaMeses) {
        if (vigenciaMeses >= 1 && vigenciaMeses <= 120)
            this.vigenciaMeses = vigenciaMeses;
        else {
            this.vigenciaMeses = 6;
            System.out.println("Vigencia invalida, 6 asignado por defecto");
        }
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    
    @Override
    public String toString() {
        return "Salario: $" + salarioMensual + " | Vigencia: " + vigenciaMeses + " meses | Tipo de contrato: " + tipoContrato;
    }
}
