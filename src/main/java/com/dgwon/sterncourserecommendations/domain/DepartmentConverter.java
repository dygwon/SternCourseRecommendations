package com.dgwon.sterncourserecommendations.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DepartmentConverter implements AttributeConverter<Department, String> {
    @Override
    public String convertToDatabaseColumn(Department department) {
        return department.getLabel();
    }

    @Override
    public Department convertToEntityAttribute(String s) {
        return Department.findByLabel(s);
    }
}
