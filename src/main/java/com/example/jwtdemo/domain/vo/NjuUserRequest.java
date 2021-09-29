package com.example.jwtdemo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NjuUserRequest {

    private String njuName;

    @NotEmpty
    private String njuSsn;

    private String njuBirthday;

    @NotEmpty
    private String njuPassword;

}
