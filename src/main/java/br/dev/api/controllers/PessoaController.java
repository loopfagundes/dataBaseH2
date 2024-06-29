package br.dev.api.controllers;

import br.dev.api.models.PessoaModel;
import br.dev.api.services.PessoaService;
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
    public List<PessoaModel> getAllPessoas() {
        return pessoaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaModel> getPessoaById(@PathVariable Long id) {
        Optional<PessoaModel> pessoa = pessoaService.findById(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PessoaModel createPessoa(@Valid @RequestBody PessoaModel pessoa) {
        return pessoaService.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> updatePessoa(@PathVariable Long id, @Valid @RequestBody PessoaModel pessoaDetails) {
        Optional<PessoaModel> pessoa = pessoaService.findById(id);

        if (pessoa.isPresent()) {
            PessoaModel p = pessoa.get();
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