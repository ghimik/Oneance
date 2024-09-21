package com.petproject.oneance.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter
public class UserRoleConverter implements AttributeConverter<User.UserRole, String> {

    @Override
    public String convertToDatabaseColumn(User.UserRole userRole) {
        if (userRole == null) {
            return null;
        }
        System.out.println("Converted UserRole " + userRole + "  to String: " +
                userRole.getRole().toLowerCase());

        return userRole.getRole().toLowerCase();
    }

    @Override
    public User.UserRole convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        System.out.println("Converted String " + s + " to UserRole: " + User.UserRole.valueOf(s.toUpperCase()));

        return User.UserRole.valueOf(s.toUpperCase());
    }

}
