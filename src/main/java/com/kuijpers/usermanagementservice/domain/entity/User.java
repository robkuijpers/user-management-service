package com.kuijpers.usermanagementservice.domain.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Representation of a user.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User implements Serializable {

    /**
     * The unique identifier of a user.
     */
    @Id
    private String id;

    /**
     * The name of a user.
     */
    private String name;


}