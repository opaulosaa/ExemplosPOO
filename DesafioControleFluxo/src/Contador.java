import java.util.Scanner;

public class Contador {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o primeiro parâmetro");
        int parametroUm = input.nextInt();
        System.out.println("Digite o segundo parâmentro");
        int parametroDois = input.nextInt();

        try{
            contar(parametroUm, parametroDois);
        } catch (ParametrosInvalidosException exception){
            System.out.println("O segundo parametro deve ser maior que o primeiro");
        }

    }

    static void contar(int parametroUm, int parametroDois) throws ParametrosInvalidosException {
        if (parametroUm >= parametroDois){
            throw new ParametrosInvalidosException();
        }
        int cont = parametroDois - parametroUm;

        for (int i = 1; i <= cont; i++){
            System.out.println("Imprimindo o numero " + i);
        }
    }
}
