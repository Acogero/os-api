package com.tipiniquim.os.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandartError implements Serializable {

    private static final long serialVersionUID = 5758047912701719595L;

    private Long timestamp;
    private Integer status;
    private String error;
}
