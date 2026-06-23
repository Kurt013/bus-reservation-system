package com.kurt.brs.utility;

import java.util.regex.Pattern;

import javax.swing.JTextField;

public class ValidationUtil {
    
    public static String validateField(JTextField field, String[] options){
        
        String value = field.getText();
        
        for (String option : options) {
            switch(option){
                case "required" :
                    if (value.equals("")){
                        field.setText(null);
                        return "'" + field.getName() + "' is a required field.";
                    }
                    break;
                    
                case "alphabetsOnly" :
                    if (!value.equals("") && !Pattern.matches("[A-Za-z ]+", value)){
                        field.setText(null);
                        return "'" + field.getName() + "' field supports only alphabets.";
                    }
                    break;
                    
                case "username" :
                    if (!value.equals("") && !Pattern.matches("[A-Za-z1-9._]+", value)){
                        field.setText(null);
                        return "Invalid Username! It supports only alphabets, numbers, underscore and full stop.";
                    }
                    break;
                case "noSpaces" :
                    if (!value.equals("") && !Pattern.matches("[^ ]+", value)){
                        field.setText(null);
                        return "'" + field.getName() + "' field doesn't support spaces.";
                    }
                    break;
                case "numeric" :
                    if (!value.equals("") && !Pattern.matches("[0-9]+", value)){
                        field.setText(null);
                        return "'" + field.getName() + "' field supports only digits.";
                    }
                    break;
                case "mobile" :
                    if (!value.equals("") && (!Pattern.matches("[0-9]+", value) || value.length() != 11)) {
                    field.setText(null);
                    return "Mobile number must be 11 digits.";
                }
                    break;
                    case "email" :
                    if (!value.equals("") && !Pattern.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", value)){
                        field.setText(null);
                        return "Invalid Email Address.";
                    }
                    break;
                case "password" :
                    if (!value.equals("") && !Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", value)){
                        field.setText(null);
                        return "Password must be at least 8 characters long and include numbers, special characters, and letters.";
                    }
                    break;
                case "repassword" :
                    if (!value.equals("") && !Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", value)){
                        field.setText(null);
                    }
                    break;
            }
        }
        return null;
    }
}
