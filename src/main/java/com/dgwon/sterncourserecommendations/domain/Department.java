package com.dgwon.sterncourserecommendations.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Department {
    ACCOUNTING("Accounting"),
    BUSINESS_AND_SOCIETY("Business and Society"),
    CORE("Core"),
    CORE_CAPSTONE("Core Capstone"),
    ECONOMICS("Economics"),
    FINANCE("Finance"),
    GLOBAL_TRIP("Global Trip"),
    SHANGHAI("Shanghai"),
    INTERAREA("Interarea"),
    MANAGEMENT("Management"),
    MANAGEMENT_COMMUNICATION("Management Communication"),
    MARKETING("Marketing"),
    NONCREDIT("Noncredit"),
    OPERATIONS_MANAGEMENT("Operations Management"),
    STATISTICS_AND_OPERATIONS_RESEARCH("Statistics and Operations Research"),
    TECHNOLOGY("Technology");

    private String department;

    private Department(String department) {
        this.department = department;
    }

    @JsonCreator
    public static Department decode(final String department) {
        return Stream.of(Department.values())
                .filter(targetEnum -> targetEnum.department.equals(department))
                .findFirst()
                .orElse(null);
    }

    @JsonValue
    public String getDepartment() {
        return this.department;
    }
}
