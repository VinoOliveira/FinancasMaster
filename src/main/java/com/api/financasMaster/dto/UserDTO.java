package com.api.financasMaster.dto;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String email, String password, BigDecimal balance) {
}
