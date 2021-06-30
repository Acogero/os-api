package com.tipiniquim.os.service;

import com.tipiniquim.os.enums.Prioridade;
import com.tipiniquim.os.enums.Status;
import com.tipiniquim.os.model.dto.ClienteDTO;
import com.tipiniquim.os.model.dto.OsDTO;
import com.tipiniquim.os.model.dto.TecnicoDTO;
import com.tipiniquim.os.model.entity.Cliente;
import com.tipiniquim.os.model.entity.OS;
import com.tipiniquim.os.model.entity.Tecnico;
import com.tipiniquim.os.repositories.OSRepository;
import com.tipiniquim.os.service.exceptions.ObjectNotFoundException;
import com.tipiniquim.os.utils.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OsService {

    @Autowired
    private OSRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OsDTO findById(Integer id) {
        Optional<OS> obj = repository.findById(id);
        OS os = obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));

        return new OsDTO(os);
    }

    public List<OsDTO> findAll() {
        return repository.findAll().stream().map(OsDTO::new).collect(Collectors.toList());
    }

    public OsDTO create(@Valid OsDTO dto) {
        return new OsDTO(fromDTO(dto));
    }

    public OsDTO update(OsDTO dto) {
        findById(dto.getId());

        return new OsDTO(fromDTO(dto));
    }

    private OS fromDTO(OsDTO dto) {
        OS newOBJ = new OS();
        newOBJ.setId(dto.getId());
        newOBJ.setObservacoes(dto.getObservacoes());
        newOBJ.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        newOBJ.setStatus(Status.toEnum(dto.getStatus()));

        TecnicoDTO tec = tecnicoService.findById(dto.getTecnico());
        ClienteDTO cli = clienteService.findById(dto.getCliente());

        newOBJ.setTecnico(ModelUtils.convertModel(tec, Tecnico.class));
        newOBJ.setCliente(ModelUtils.convertModel(cli, Cliente.class));

        if (newOBJ.getStatus().equals(2)) {
            newOBJ.setDataFechamento(LocalDateTime.now());
        }

        return repository.save(newOBJ);

    }
}
