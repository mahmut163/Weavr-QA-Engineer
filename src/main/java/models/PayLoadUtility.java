package models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayLoadUtility {
    public static String getPayLoad(String name,String gender,String email, String status) {
        String load = null;
        UserBody payLoad=new UserBody(name,gender,email,status);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            load = objectMapper.writeValueAsString(payLoad);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return load;
    }
}
