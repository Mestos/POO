
class ContaBancaria 
{
    private int numeroConta;
    private double saldo;

    public ContaBancaria(int numeroConta, double saldoInicial) 
    {
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial;
    }

    public void depositar(double valor) 
    {
        saldo += valor;
    }

    public boolean sacar(double valor) 
    {
        if (valor <= saldo) 
        {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public double consultarSaldo() 
    {
        return saldo;
    }

    @Override
    public String toString() 
    {
        return "Conta Bancária [Número: " + numeroConta + ", Saldo: " + saldo + "]";
    }
}
