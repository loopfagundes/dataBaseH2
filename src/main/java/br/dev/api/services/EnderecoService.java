package br.dev.api.services;

import br.dev.api.models.EnderecoModel;
import br.dev.api.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoModel> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<EnderecoModel> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    @Transactional
    public EnderecoModel save(EnderecoModel endereco) {
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }
}
