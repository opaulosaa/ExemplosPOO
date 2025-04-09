package service;

import dao.ClientDao;
import dao.IClientDao;

public class ClientService {

    private IClientDao clientDao;

    public ClientService(IClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public String salvar(){
        clientDao.salvar();
        return "Sucesso";
    }

    public String buscar() {
        return "Cliente encontrado";
    }

    public String excluir() {
        return "Cliente excluído com sucesso";
    }

    public String atualizar() {
        return "Cliente atualizado com sucesso";
    }
}
