package uolbackendd.entities.dtos;

import uolbackendd.entities.enums.GroupType;

public record PlayerDTO(String name, String email, String phone, GroupType groupType) {
}
