package br.dev.api.repositories;

import br.dev.api.models.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository <EnderecoModel, Long> {
}