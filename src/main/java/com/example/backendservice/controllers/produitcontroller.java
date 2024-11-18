package com.example.backendservice.controllers;

import com.example.backendservice.models.Produit;
import com.example.backendservice.models.categories;
import com.example.backendservice.services.ProduitService;
import com.example.backendservice.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:54609")
@RestController
@RequestMapping("/produits")
public class produitcontroller {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private CategorieService categorieService;

    // Get all products
    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/categories")
    public List<categories> getAllCategories() {
        return categorieService.getAllCategories();
    }

    // Get products by category
    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Produit>> getProduitsByCategorie(@PathVariable String categorie) {
        List<Produit> produits = produitService.getProduitsByCategorie(categorie);
        if (produits.isEmpty()) {
            return ResponseEntity.noContent().build();  // No products found for the category
        }
        return ResponseEntity.ok(produits);
    }

    // Add a new product
    @PostMapping("/ajouter")
    public ResponseEntity<Produit> ajouterProduit(
            @RequestParam("nom") String nom,
            @RequestParam("description") String description,
            @RequestParam("prix") double prix,
            @RequestParam("quantite") int quantite,
            @RequestParam("categorie_id") int categorieId,
            @RequestParam("adresse_ip") String adresseIp,
            @RequestParam("image_File") MultipartFile imageFile) throws IOException {
        Optional<categories> optionalCategorie = Optional.ofNullable(categorieService.getCategorieById(categorieId));

        // Check if category exists
        if (optionalCategorie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Return error if category doesn't exist
        }

        // Prepare product
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setPrix(prix);
        produit.setQuantite(quantite);
        produit.setCategorie(optionalCategorie.get());
        produit.setAdresse_ip(adresseIp);

        // Handle image file
        if (!imageFile.isEmpty()) {
            try {
                produit.setImage(imageFile.getBytes());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Error handling for file upload
            }
        }

        // Save product and return response
        Produit savedProduit = produitService.ajouterProduit(produit, imageFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduit);
    }

    // Add a new category
    @PostMapping("/categorie/ajouter")
    public ResponseEntity<categories> ajouterCategorie(@RequestBody categories categorie) {
        categories savedCategorie = categorieService.ajouterCategorie(categorie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategorie);
    }

    // Delete a product
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerProduit(@PathVariable int id) {
        if (!produitService.getAllProduits().stream().anyMatch(produit -> produit.getId() == id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Return error if product not found
        }
        produitService.supprimerProduit(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Success response
    }
}
