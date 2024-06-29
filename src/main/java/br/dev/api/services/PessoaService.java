package br.dev.api.services;

import br.dev.api.models.PessoaModel;
import br.dev.api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaModel> findAll() {
        return pessoaRepository.findAll();
    }

    public Optional<PessoaModel> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    @Transactional
    public PessoaModel save(PessoaModel pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void deleteById(Long id) {
        pessoaRepository.deleteById(id);
    }
}

