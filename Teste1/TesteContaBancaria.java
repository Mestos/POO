/*
UNIDADE I - MINI TESTE I
1) (1,5 Pontos) Você está desenvolvendo um sistema bancário simples em Java para gerenciar contas bancárias. 
Crie uma classe chamada ContaBancaria que tenha os seguintes atributos e métodos: o número da conta bancária, 
o saldo atual da conta, depositar que adiciona o valor especificado ao saldo da conta, 
sacar que subtrai o valor especificado do saldo da conta, desde que haja saldo suficiente, 
consultarSaldo que retorna o saldo atual da conta e o toString que retorna uma representação em formato de string 
da conta bancária, contendo o número da conta e o saldo. 
Agora, crie uma classe chamada TesteContaBancaria que contenha o método main. 
No método main, realize as seguintes operações:
	a) Crie duas instâncias da classe ContaBancaria, uma com o número de conta 123 e saldo inicial de 1000, 
e outra com o número de conta 456 e saldo inicial de 500.
	b) Realize algumas operações de depósito e saque em ambas as contas.
	c) Imprima o saldo final das contas após as operações.

Dica: Utilize a classe Scanner ou JOptionPane para receber entrada do usuário e realizar as operações.
*/

public class TesteContaBancaria 
{
    public static void main(String[] args) 
    {
        ContaBancaria conta1 = new ContaBancaria(123, 1000);
        ContaBancaria conta2 = new ContaBancaria(456, 500);

        conta1.depositar(300);
        conta2.depositar(200);

        conta1.sacar(150);
        conta2.sacar(100);

        System.out.println("Saldo final da conta 123: " + conta1.consultarSaldo());
        System.out.println("Saldo final da conta 456: " + conta2.consultarSaldo());
    }
}
