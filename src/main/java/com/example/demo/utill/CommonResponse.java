package com.example.demo.utill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonResponse {

    private boolean status = false;

    private List<String> errorMessages = new ArrayList<String>();

    private String commonMessage;

    private  List <Object> payload = null;

}
