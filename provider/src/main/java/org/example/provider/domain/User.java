package org.example.provider.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @NotBlank(message = "name not null")
    private String name;

    @NotNull(message = "age not null")
    @Min(value = 0, message = "age must be >= 0")
    private Integer age;

}
