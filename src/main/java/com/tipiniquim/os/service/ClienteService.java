package com.tipiniquim.os.service;

import com.tipiniquim.os.model.dto.ClienteDTO;
import com.tipiniquim.os.model.entity.Cliente;
import com.tipiniquim.os.model.entity.Pessoa;
import com.tipiniquim.os.repositories.ClienteRepository;
import com.tipiniquim.os.repositories.PessoaRepository;
import com.tipiniquim.os.service.exceptions.DataIntegratyViolationException;
import com.tipiniquim.os.service.exceptions.ObjectNotFoundException;
import com.tipiniquim.os.utils.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public ClienteDTO findById(Integer id) {
        Optional<Cliente> byId = repository.findById(id);
        return ModelUtils.convertModel(byId.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName())), ClienteDTO.class);
    }

    public List<ClienteDTO> findAll() {
        return repository.findAll().stream().map(obj -> ModelUtils.convertModel(obj, ClienteDTO.class)).collect(Collectors.toList());
    }

    public ClienteDTO create(ClienteDTO dto) {
        if (findByCPF(dto) != null)
            throw new DataIntegratyViolationException("CPF Já cadastrado!");

        Cliente cliente = ModelUtils.convertModel(dto, Cliente.class);
        cliente = repository.save(cliente);

        return ModelUtils.convertModel(cliente, ClienteDTO.class);
    }

    public ClienteDTO update(Integer id, ClienteDTO dto) {
        ClienteDTO old = findById(id);

        if (findByCPF(dto) != null && !findByCPF(dto).getId().equals(id))
            throw new DataIntegratyViolationException("CPF já cadastrado!");

        old.setNome(dto.getNome());
        old.setCpf(dto.getCpf());
        old.setTelefone(dto.getTelefone());

        Cliente cliente = repository.save(ModelUtils.convertModel(old, Cliente.class));

        return ModelUtils.convertModel(cliente, ClienteDTO.class);
    }

    public void delete (Integer id) {
        ClienteDTO dto = findById(id);

        if (dto.getList().size() > 0)
            throw new DataIntegratyViolationException("Cliente possui Ordens de Serviço, e não pode ser deletado!");

        repository.deleteById(dto.getId());
    }

    private Pessoa findByCPF(ClienteDTO dto) {
        return pessoaRepository.findByCPF(dto.getCpf());
    }
}
