package br.dev.api.controllers;

import br.dev.api.models.EnderecoModel;
import br.dev.api.services.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<EnderecoModel> getAllEnderecos() {
        return enderecoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> getEnderecoById(@PathVariable Long id) {
        Optional<EnderecoModel> endereco = enderecoService.findById(id);
        return endereco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EnderecoModel createEndereco(@Valid @RequestBody EnderecoModel endereco) {
        return enderecoService.save(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoModel> updateEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoModel enderecoDetails) {
        Optional<EnderecoModel> endereco = enderecoService.findById(id);

        if (endereco.isPresent()) {
            EnderecoModel e = endereco.get();
            e.setRua(enderecoDetails.getRua());
            e.setNumero(enderecoDetails.getNumero());
            e.setBairro(enderecoDetails.getBairro());
            e.setCidade(enderecoDetails.getCidade());
            e.setEstado(enderecoDetails.getEstado());
            e.setCep(enderecoDetails.getCep());
            return ResponseEntity.ok(enderecoService.save(e));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        enderecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}