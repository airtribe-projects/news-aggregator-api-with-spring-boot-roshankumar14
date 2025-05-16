package com.newsAggregator.newsAggregator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tokenId;

    @NotNull
    private String genToken;

    @NotNull
    private Date expDate;

    @OneToOne
    private NewsReaderUser newsReaderUser;

}
