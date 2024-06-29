package br.dev.api.controllers;

import br.dev.api.models.Pessoa;
import br.dev.api.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> getAllPessoas() {
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.findById(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pessoa createPessoa(@Valid @RequestBody Pessoa pessoa) {
        return pessoaService.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @Valid @RequestBody Pessoa pessoaDetails) {
        Optional<Pessoa> pessoa = pessoaService.findById(id);

        if (pessoa.isPresent()) {
            Pessoa p = pessoa.get();
            p.setNome(pessoaDetails.getNome());
            p.setDataNascimento(pessoaDetails.getDataNascimento());
            p.setCpf(pessoaDetails.getCpf());
            p.setEnderecos(pessoaDetails.getEnderecos());
            return ResponseEntity.ok(pessoaService.save(p));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
