package com.example.backendservice.repositories;

import com.example.backendservice.models.categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepositories extends JpaRepository<categories, Integer> {
    categories findByNom(String nom);
}
