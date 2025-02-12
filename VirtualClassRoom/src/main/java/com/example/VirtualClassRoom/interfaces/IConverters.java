package com.example.VirtualClassRoom.interfaces;

import com.example.VirtualClassRoom.dto.UserDTO;

public interface IConverters<T,D> {
    D convertDTOFromEntity(T EntityData);
}
