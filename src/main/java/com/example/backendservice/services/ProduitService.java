package com.example.backendservice.services;
import com.example.backendservice.models.Produit;
import com.example.backendservice.models.categories;
import com.example.backendservice.repositories.ProduitRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepositories produitRepository;

    @Autowired
    private CategorieService categorieService;

    public List<Produit> getProduitsByCategorie(String categorieNom) {
        categories categorie = categorieService.getCategorieByNom(categorieNom);
        return produitRepository.findByCategorie(categorie);
    }

    public Produit ajouterProduit(Produit produit, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            produit.setImage(imageFile.getBytes());
        }
        return produitRepository.save(produit);
    }

    public void supprimerProduit(int id) {
        produitRepository.deleteById(id);
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
}
