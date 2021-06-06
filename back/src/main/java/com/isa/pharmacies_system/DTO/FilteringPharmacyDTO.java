package com.isa.pharmacies_system.DTO;

import java.util.List;

public class FilteringPharmacyDTO {

    private List<Boolean> filterListForRating;
    private List<Boolean> filterListForPharmacistAppointmentPricePerHour;
    private List<Boolean> filterListForDermatologistAppointmentPricePerHour;
    private List<PharmacyDTO> pharmacyDTOS;


    public FilteringPharmacyDTO() {
    }

    public List<Boolean> getFilterListForRating() {
        return filterListForRating;
    }

    public void setFilterListForRating(List<Boolean> filterListForRating) {
        this.filterListForRating = filterListForRating;
    }

    public List<Boolean> getFilterListForPharmacistAppointmentPricePerHour() {
        return filterListForPharmacistAppointmentPricePerHour;
    }

    public void setFilterListForPharmacistAppointmentPricePerHour(List<Boolean> filterListForPharmacistAppointmentPricePerHour) {
        this.filterListForPharmacistAppointmentPricePerHour = filterListForPharmacistAppointmentPricePerHour;
    }

    public List<Boolean> getFilterListForDermatologistAppointmentPricePerHour() {
        return filterListForDermatologistAppointmentPricePerHour;
    }

    public void setFilterListForDermatologistAppointmentPricePerHour(List<Boolean> filterListForDermatologistAppointmentPricePerHour) {
        this.filterListForDermatologistAppointmentPricePerHour = filterListForDermatologistAppointmentPricePerHour;
    }

    public List<PharmacyDTO> getPharmacyDTOS() {
        return pharmacyDTOS;
    }

    public void setPharmacyDTOS(List<PharmacyDTO> pharmacyDTOS) {
        this.pharmacyDTOS = pharmacyDTOS;
    }
}
