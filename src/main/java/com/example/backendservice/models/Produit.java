package com.example.backendservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "prix")
    private double prix;

    @Column(name = "quantite")
    private int quantite;

    @Lob
    @Column(name = "image_File", columnDefinition = "BLOB")
    private byte[] imageFile;

    @Column(name="ip_adresse")
    private String adresse_ip;



    @ManyToOne
    @JoinColumn(name = "categorie_id")
    @JsonIgnoreProperties({"produits"})

    private categories categorie;

    // Constructors
    public Produit() {}

    public Produit(String nom, String description, double prix, int quantite, categories categorie, byte[] imageFile, String adresseIp) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.categorie = categorie;
        this.imageFile = imageFile;
        adresse_ip = adresseIp;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public byte[] getimage() { return imageFile; }
    public void setImage(byte[] imageFile) { this.imageFile= imageFile; }

    public categories getCategorie() { return categorie; }
    public void setCategorie(categories categorie) { this.categorie = categorie; }

    public String getAdresse_ip(){return adresse_ip;}
    public  void setAdresse_ip(String adresse_ip){this.adresse_ip=adresse_ip;}


}
