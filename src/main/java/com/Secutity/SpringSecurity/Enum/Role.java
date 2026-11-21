package com.Secutity.SpringSecurity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
@Getter
@AllArgsConstructor
public enum Role {
    ROLE_ADMIN(Set.of(Permession.ROOM_ADD,Permession.ROOM_VIEW,Permession.ROOM_VIEW_ALL)),
    ROLE_STAFF(Set.of(Permession.ROOM_VIEW,Permession.ROOM_VIEW_ALL)),
    ROLE_GUEST(Set.of(Permession.ROOM_VIEW));

    private final Set<Permession> permessions;

}
