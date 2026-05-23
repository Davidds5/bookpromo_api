package com.davidds5.bookpromo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "platform")
    @NotNull(message = "Platform not Null")
    private String platform;

    @Column(name = "price")
    @NotNull(message = "Price not Null")
    private BigDecimal price;

    @Column(name = "affiliate_link")
    @NotNull(message = "Affiliate Link not Null")
    private String affiliateLink;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

}
