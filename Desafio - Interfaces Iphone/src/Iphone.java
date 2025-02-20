public class Iphone implements AparelhoTelefonico, NavegadorInternet, ReprodutorMusical {

    @Override
    public void ligar(String numero) {
        System.out.println("ligando para " + numero);
    }

    @Override
    public void atender() {

    }

    @Override
    public void iniciarCorreiroDeVoz() {

    }

    @Override
    public void exibirPagina(String url) {
        System.out.println("exibindo pagina " + url);
    }

    @Override
    public void addAba() {

    }

    @Override
    public void atualizaarPagina() {

    }

    @Override
    public void tocar() {

    }

    @Override
    public void pausar() {

    }

    @Override
    public void selecionarMusica(String musica) {
        System.out.println("musica selecionada " + musica);
    }

    public static void main(String[] args) {
        Iphone iphone = new Iphone();
        iphone.ligar("85998980000");
        iphone.exibirPagina("dio.com");
    }
}
