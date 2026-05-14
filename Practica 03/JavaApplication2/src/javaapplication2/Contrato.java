package javaapplication2;

public class Contrato {
    private double salarioMensual;
    private int vigenciaMeses;
    private double clausulaRescision;
    protected String tipoRepresentacion;

    public Contrato(double salario, int vigencia, double clausula, String tipoRepresentacion) {
        setSalarioMensual(salario);
        setVigenciaMeses(vigencia);
        setClausulaRescision(clausula);
        this.tipoRepresentacion = tipoRepresentacion;
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

    public double getClausulaRescision() {
        return clausulaRescision;
    }

    public void setClausulaRescision(double clausulaRescision) {
        if (clausulaRescision >= 0)
            this.clausulaRescision = clausulaRescision;
        else {
            this.clausulaRescision = 0;
            System.out.println("Clausula invalida, 0 asignado por defecto");
        }
    }

    public String getTipoRepresentacion() {
        return tipoRepresentacion;
    }

    public void setTipoRepresentacion(String tipoRepresentacion) {
        this.tipoRepresentacion = tipoRepresentacion;
    }
    
    @Override
    public String toString() {
        return "Salario: $" + salarioMensual + " | Vigencia: " + vigenciaMeses + " meses | Cláusula: " + clausulaRescision + " meses | Tipo de representacion: " + tipoRepresentacion;
    }
}
