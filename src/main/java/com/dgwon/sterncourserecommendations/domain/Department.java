package com.dgwon.sterncourserecommendations.domain;

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

    private String label;

    private Department(String label) {
        this.label = label;
    }

    public static Department findByLabel(String byLabel) {
        for (Department d: Department.values()) {
            if (d.label.equalsIgnoreCase(byLabel))
                return d;
        }
        return null;
    }

    public String getLabel() {
        return label;
    }
}
