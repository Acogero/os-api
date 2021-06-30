package com.tipiniquim.os.service;

import com.tipiniquim.os.enums.Prioridade;
import com.tipiniquim.os.enums.Status;
import com.tipiniquim.os.model.entity.Cliente;
import com.tipiniquim.os.model.entity.OS;
import com.tipiniquim.os.model.entity.Tecnico;
import com.tipiniquim.os.repositories.ClienteRepository;
import com.tipiniquim.os.repositories.OSRepository;
import com.tipiniquim.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OSRepository osRepository;

    public void instanciaDB() {
        Tecnico t1 = new Tecnico(null, "Marcos", "144.785.300-84", "27 91234-1234");
        Tecnico t2 = new Tecnico(null, "Sharkoon Teclado", "597.924.710-65", "27 91234-1234");
        Cliente c1 = new Cliente(null, "Vinicius", "109.711.640-96", "27 99938-3726");
        OS os1 = new OS(null, Prioridade.ALTA, "Teste Create OS", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1, t2));
        clienteRepository.saveAll(Collections.singletonList(c1));
        osRepository.saveAll(Collections.singletonList(os1));
    }
}
