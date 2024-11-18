package com.example.backendservice.services;

import com.example.backendservice.models.categories;
import com.example.backendservice.repositories.CategoriesRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategoriesRepositories categorieRepository;

    public categories ajouterCategorie(categories categorie) {
        return categorieRepository.save(categorie);
    }

    public categories getCategorieByNom(String nom) {
        return categorieRepository.findByNom(nom);
    }

    public categories getCategorieById(int id) {
        return categorieRepository.findById(id).orElse(null); // Renvoie null si la catégorie n'existe pas
    }

    public List<categories> getAllCategories() {
        return categorieRepository.findAll();
    }
}