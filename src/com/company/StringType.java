package com.company;

public class StringType {
    private final String type;

    StringType(String type) {
        this.type = type;
    }

    final String[] shortNames = {"DAPL", "DAPB", "DANW", "DAXSG", "DAHRG", "DACG", "DAFT", "DABW", "DAZW", "DAXB", "DAHB", "DABC", "DABS"};

    public String getStringType() {
        switch (this.type) {
            case "Plain Steel":
                return shortNames[0];
            case "Phosphore Bronze Wound":
                return shortNames[1];
            case "Nickel Wound":
                return shortNames[2];
            case "Stainless Steel Wound":
                return shortNames[3];
            case "Half Round Wound":
                return shortNames[4];
            case "Chromes - Stainless Steel Flat Wound":
                return shortNames[5];
            case "Flat Tops - Phosphore Bronze Polished":
                return shortNames[6];
            case "80-20 S- 80/20 Brass Round Wound":
                return shortNames[7];
            case "Great American Bronze - 85/15 Brass Round W":
                return shortNames[8];
            case "Bass - Nickplated Round Wound":
                return shortNames[9];
            case "Bass - Half Round - Pure Nickel Half Round":
                return shortNames[10];
            case "Bass - Chromes - Stainless Steel Flat Wound":
                return shortNames[11];
            case "Bass - ProSteels - ProSteel Round Wound":
                return shortNames[12];
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
