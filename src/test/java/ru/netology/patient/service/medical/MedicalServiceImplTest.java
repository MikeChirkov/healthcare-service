package ru.netology.patient.service.medical;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.alert.SendAlertServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

class MedicalServiceImplTest {

    @Test
    void checkBloodPressureNegative() {
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString()))
                .thenReturn(
                        new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)))
                );
        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);

        medicalService.checkBloodPressure(Mockito.anyString(), new BloodPressure(140, 80));

        Mockito.verify(sendAlertService, Mockito.atLeastOnce()).send(Mockito.anyString());
        Mockito.verify(patientInfoRepository, Mockito.atLeastOnce()).getById(Mockito.anyString());
    }

    @Test
    void checkBloodPressurePositive() {
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString()))
                .thenReturn(
                        new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)))
                );
        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);

        medicalService.checkBloodPressure(Mockito.anyString(), new BloodPressure(120, 80));

        Mockito.verify(sendAlertService, Mockito.never()).send(Mockito.anyString());
        Mockito.verify(patientInfoRepository, Mockito.atLeastOnce()).getById(Mockito.anyString());
    }

    @Test
    void checkTemperatureNegative() {
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString()))
                .thenReturn(
                        new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)))
                );
        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);

        medicalService.checkTemperature(Mockito.anyString(), new BigDecimal("30"));

        Mockito.verify(sendAlertService, Mockito.atLeastOnce()).send(Mockito.anyString());
        Mockito.verify(patientInfoRepository, Mockito.atLeastOnce()).getById(Mockito.anyString());
    }

    @Test
    void checkTemperaturePositive() {
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(Mockito.anyString()))
                .thenReturn(
                        new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)))
                );
        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);

        medicalService.checkTemperature(Mockito.anyString(), new BigDecimal("36.6"));

        Mockito.verify(sendAlertService, Mockito.never()).send(Mockito.anyString());
        Mockito.verify(patientInfoRepository, Mockito.atLeastOnce()).getById(Mockito.anyString());
    }
}