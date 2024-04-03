package com.example.demo.model;

import com.example.demo.converter.JSONAttributeConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "STUDENT")
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) & @Type(type = "jsonb") -> deprecated in hibernate 6
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="STUDENT_SEQ", allocationSize = 1)
    private Integer studentId;
    @Column(name = "FIRST_NAME", nullable = false)
    private String fname;
    @Column(name = "LAST_NAME", nullable = false)
    private String lname;
    @Column(name = "MOBILE_NUMBER", unique = true)
    private String mobileNumber;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;
    @Column(name = "PERCENTAGE")
    private Double pastPercentage;
    @JdbcTypeCode( SqlTypes.JSON ) //Error without this: could not execute statement [ERROR: column "additional_info" is of type jsonb but expression is of type character varying
    //Hint: You will need to rewrite or cast the expression. Replacement of @Type(type = "")
    @Convert(converter = JSONAttributeConverter.class)
    @Column(columnDefinition = "jsonb", name = "ADDITIONAL_INFO")
    private Address additionalInfo;
    @Column(name = "IS_ACTIVE")
    @JsonProperty("isActive")
    private boolean isActive;
}
