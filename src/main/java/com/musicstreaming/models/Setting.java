package com.musicstreaming.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "settings")
public class Setting {

    @Id
    @Column( nullable = false)
    private String key;

    @Column(name = "value", columnDefinition = "TEXT", nullable = false)
    private String value;
}