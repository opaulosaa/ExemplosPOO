package projetodois;

public class ClienteService implements IClienteService {

    private IClienteDAO clienteDAO;

    public ClienteService(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public Boolean salvar(Cliente cliente) {
        clienteDAO.salvar(cliente);
        return null;
    }

    @Override
    public Cliente buscarPorCPF(Long cpf) {
        return clienteDAO.buscarPorCPF(cpf);
    }

    @Override
    public void excluir(Long cpf) {
    clienteDAO.excluir(cpf);
    }

    @Override
    public void alterar(Cliente cliente) {
        clienteDAO.alterar(cliente);
    }
}
