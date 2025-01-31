import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite seu nome");
        String nome = input.nextLine();
        System.out.println("Por favor, digite o número da agência");
        String agencia = input.nextLine();
        System.out.println("Por favor, digite quatro números");
        int conta = input.nextInt();
        System.out.println("Por favor, digite o valor do depósito");
        float saldo = input.nextFloat();

        System.out.printf("Olá %s, obrigado pro abrir uma conta no nosso banco. A partir de agora sua agência é a %s e o número da sua conta é %d. Além disso, você tem o valor de R$ %f disponivel para saque", nome, agencia, conta, saldo);


    }
}
