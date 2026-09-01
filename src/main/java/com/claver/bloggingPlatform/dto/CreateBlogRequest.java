package com.claver.bloggingPlatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBlogRequest {

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotBlank(message = "Le contenu est obligatoire")
    private String content;
    @NotBlank(message = "La catégorie est obligatoire")
    private String category;

    @NotEmpty(message = "Au moins un tag est obligatoire")
    private String[] tags;

}
