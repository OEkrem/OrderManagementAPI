package com.oekrem.SpringMVCBackEnd.Models;

public enum QuantityType {
    PIECE("Piece"),      // Adet
    DOZEN("Dozen"),      // Düzine
    PACK("Pack"),        // Paket
    BOX("Box"),          // Kutu
    CARTON("Carton"),    // Karton
    PALLET("Pallet"),    // Palet
    GRAM("Gram"),        // Gram
    KILOGRAM("Kilogram"), // Kilogram
    LITER("Liter"),      // Litre
    MILLILITER("Milliliter"); // Mililitre

    private final String displayName; // String karşılığı

    // Constructor (Kurucu Metot)
    QuantityType(String displayName) {
        this.displayName = displayName;
    }

    // Getter metodu (String karşılığını almak için)
    public String getDisplayName() {
        return displayName;
    }
}
