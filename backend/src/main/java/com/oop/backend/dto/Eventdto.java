package com.oop.backend.dto;

import com.oop.backend.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Eventdto {

    private Event event;
    private byte[] image;


}
