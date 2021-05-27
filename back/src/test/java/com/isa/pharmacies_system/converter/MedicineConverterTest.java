package com.isa.pharmacies_system.converter;

import com.isa.pharmacies_system.DTO.MedicineForRecipeDTO;
import com.isa.pharmacies_system.domain.storage.PharmacyStorageItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.isa.pharmacies_system.prototype.ProtoClass.protoPharmacyStorage;
import static com.isa.pharmacies_system.prototype.ProtoClass.protoPharmacyStorageItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MedicineConverterTest {

    private MedicineConverter medicineConverter;

    @BeforeEach
    void setUp(){
        medicineConverter = new MedicineConverter();
    }

    @Test
    void convertOnePharmacyStorageItemToMedicineForRecipe() {
        PharmacyStorageItem item = protoPharmacyStorageItem(new PharmacyStorageItem());
        MedicineForRecipeDTO medicineForRecipeDTO = medicineConverter.convertOnePharmacyStorageItemToMedicineForRecipe(item);

        assertThat(medicineForRecipeDTO).isNotNull();
        assertThat(medicineForRecipeDTO.getMedicineId()).isEqualTo(item.getMedicineItem().getId());
        assertThat(medicineForRecipeDTO.getMedicineName()).isEqualTo(item.getMedicineItem().getMedicineName());
        assertThat(medicineForRecipeDTO.getManufacturerOfMedicine()).isEqualTo(item.getMedicineItem().getManufacturerOfMedicine());
        assertThat(medicineForRecipeDTO.getNotes()).isEqualTo(item.getMedicineItem().getNotes());
        assertThat(medicineForRecipeDTO.getMedicineAmount()).isEqualTo(item.getMedicineAmount());
        assertThat(medicineForRecipeDTO.getTypeOfMedicine()).isEqualTo(item.getMedicineItem().getTypeOfMedicine());
        assertThat(medicineForRecipeDTO.getFormOfMedicine()).isEqualTo(item.getMedicineItem().getFormOfMedicine());

    }

    @Test
    void convertOnePharmacyStorageItemToMedicineForRecipeThrowsExceptionWhenPharmacyStorageItemIsNull(){
        assertThrows(NullPointerException.class,
                () -> medicineConverter.convertOnePharmacyStorageItemToMedicineForRecipe(null));

    }


}