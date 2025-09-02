package br.pucrs.totem.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "exhibitors")
public class Exhibitor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String companyName;

    private String contactEmail;

    @OneToMany(mappedBy = "exhibitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lead> leads;
}
