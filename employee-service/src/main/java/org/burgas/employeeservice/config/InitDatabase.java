package org.burgas.employeeservice.config;

import lombok.extern.slf4j.Slf4j;
import org.burgas.employeeservice.entity.Employee;
import org.burgas.employeeservice.entity.EmployeeProductType;
import org.burgas.employeeservice.model.Gender;
import org.burgas.employeeservice.entity.Position;
import org.burgas.employeeservice.repository.EmployeeRepository;
import org.burgas.employeeservice.repository.EmployeeProductTypeRepository;
import org.burgas.employeeservice.repository.PositionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Configuration
public class InitDatabase {

    @Bean
    public CommandLineRunner insertData(
            PositionRepository positionRepository,
            EmployeeRepository employeeRepository,
            EmployeeProductTypeRepository employeeProductTypeRepository
    ) {

        return _ -> {

            Position juniorSalesConsultant = Position.builder().name("Младший продавец-консультант").build();
            Position salesConsultant = Position.builder().name("Продавец-консультант").build();
            Position seniorSalesConsultant = Position.builder().name("Старший продавец-консультант").build();

            Employee pavelPoleev = Employee.builder().name("Павел").surname("Полеев").patronymic("Викторович")
                    .position(seniorSalesConsultant).gender(Gender.MALE).storeId(1L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1970, 11, 15)
                            )
                    ).build();

            Employee sergeyKarpov = Employee.builder().name("Сергей").surname("Карпов").patronymic("Аркадьевич")
                    .position(salesConsultant).gender(Gender.MALE).storeId(4L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1978, 3, 23)
                            )
                    ).build();

            Employee antoninaKuplina = Employee.builder().name("Антонина").surname("Куплина").patronymic("Станиславовна")
                    .position(salesConsultant).gender(Gender.FEMALE).storeId(5L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1977, 4, 5)
                            )
                    ).build();

            Employee marinaVorohina = Employee.builder().name("Марина").surname("Ворохина").patronymic("Алексеевна")
                    .position(juniorSalesConsultant).gender(Gender.FEMALE).storeId(2L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1981, 12, 14)
                            )
                    ).build();

            Employee vladislavKruglov = Employee.builder().name("Владислав").surname("Круглов").patronymic("Олегович")
                    .position(juniorSalesConsultant).gender(Gender.MALE).storeId(3L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1986, 6, 18)
                            )
                    ).build();

            Employee victoriaMirokhina = Employee.builder().name("Виктория").surname("Мирохина").patronymic("Владимировна")
                    .position(juniorSalesConsultant).gender(Gender.FEMALE).storeId(6L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1973, 10, 7)
                            )
                    ).build();

            ArrayList<Employee> employees = new ArrayList<>(
                    List.of(sergeyKarpov, vladislavKruglov, victoriaMirokhina,
                            marinaVorohina, antoninaKuplina, pavelPoleev)
            );

            log.info(
                    "Position added: {}", positionRepository.saveAll(
                            List.of(juniorSalesConsultant, salesConsultant, seniorSalesConsultant)
                    )
            );
            log.info(
                    "Employee added: {}", employeeRepository.saveAll(employees)
            );

            employees.forEach(
                    employee -> {

                        ArrayList<Long> productTypeIds = new ArrayList<>();
                        int nexted = new Random().nextInt(1, 10);

                        for (int i = 0; i < nexted; ++i) {

                            AtomicLong productTypeId = new AtomicLong(
                                    new Random().nextLong(1, 10)
                            );

                            if (i > 0) {
                                productTypeIds.forEach(aLong -> {
                                    while (aLong == productTypeId.get()) {
                                        productTypeId.set(new Random().nextLong(1, 10));
                                    }
                                });
                            }
                            productTypeIds.add(productTypeId.get());

                            employeeProductTypeRepository.save(
                                    EmployeeProductType.builder()
                                            .employeeId(employee.getId())
                                            .productTypeId(productTypeId.get())
                                            .build()
                            );

                            nexted = new Random().nextInt(1, 10);
                            if (nexted <= i) {
                                while (nexted <= i) {
                                    nexted = new Random().nextInt(1, 10);
                                }
                            }
                        }
                    }
            );
        };
    }
}
