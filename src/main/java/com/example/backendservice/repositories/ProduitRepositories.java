package com.example.backendservice.repositories;

import com.example.backendservice.models.Produit;
import com.example.backendservice.models.categories;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProduitRepositories extends JpaRepository<Produit, Integer> {
    List<Produit> findByCategorie(categories categorie);
}
