package com.remittancemiddleware.remittancemiddleware.dataclass.remittance.financenow.enumdata;

public enum PurposeOfRemittanceRN {
    BUSINESS_EXPENSES("16"),
    CONSTRUCTION_LAND_PURCHASE_MORTGAGE_REPAYMENTS("12"),
    DONATION("05"),
    EDUCATION("03"),
    FAMILY_EXPENSES("02"),
    OTHER("99"),
    PAYMENT_PRODUCT_SERVICES("06"),
    SELF("10");
    ;

    private String data;

    PurposeOfRemittanceRN(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
