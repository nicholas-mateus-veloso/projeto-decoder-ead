package com.ead.course.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_USERS")
public class UserModel implements Serializable {

    private static final long serialVersionUID = -7611230144748175318L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

}