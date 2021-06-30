package com.tipiniquim.os.service;

import com.tipiniquim.os.model.dto.TecnicoDTO;
import com.tipiniquim.os.model.entity.Pessoa;
import com.tipiniquim.os.model.entity.Tecnico;
import com.tipiniquim.os.repositories.PessoaRepository;
import com.tipiniquim.os.repositories.TecnicoRepository;
import com.tipiniquim.os.service.exceptions.DataIntegratyViolationException;
import com.tipiniquim.os.service.exceptions.ObjectNotFoundException;
import com.tipiniquim.os.utils.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public TecnicoDTO findById(Integer id) {
        Optional<Tecnico> byId = repository.findById(id);

        return ModelUtils.convertModel(byId.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Tecnico.class.getName())), TecnicoDTO.class);
    }

    public List<TecnicoDTO> findAll() {
        return repository.findAll().stream().map(obj -> ModelUtils.convertModel(obj, TecnicoDTO.class)).collect(Collectors.toList());
    }

    public TecnicoDTO create(TecnicoDTO dto) {

        if (findByCPF(dto) != null) {
            throw new DataIntegratyViolationException("CPF Já cadastrado!");
        }

        Tecnico tecnico = ModelUtils.convertModel(dto, Tecnico.class);
        tecnico = repository.save(tecnico);

        return ModelUtils.convertModel(tecnico, TecnicoDTO.class);
    }

    public TecnicoDTO update(Integer id, TecnicoDTO dto) {
        TecnicoDTO old = findById(id);

        if (findByCPF(dto) != null && !findByCPF(dto).getId().equals(id)) {
            throw new DataIntegratyViolationException("CPF já cadastrado!");
        }

        old.setNome(dto.getNome());
        old.setCpf(dto.getCpf());
        old.setTelefone(dto.getTelefone());

        Tecnico tecnico = repository.save(ModelUtils.convertModel(old, Tecnico.class));

        return ModelUtils.convertModel(tecnico, TecnicoDTO.class);
    }

    public void delete(Integer id) {
//        Tecnico byId = repository.findById(id).get();
        TecnicoDTO byId = findById(id);

        if (byId.getList().size() > 0)
            throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, e não pode ser deletado!");

        repository.deleteById(byId.getId());
    }

    private Pessoa findByCPF(TecnicoDTO dto) {
        return pessoaRepository.findByCPF(dto.getCpf());
    }
}
