package com.exam.PTIT.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRespon {
    private String email;
    private String username;
}
