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

            Position stager = Position.builder().name("Стажер").build();
            Position juniorSalesConsultant = Position.builder().name("Младший продавец-консультант").build();
            Position salesConsultant = Position.builder().name("Продавец-консультант").build();
            Position seniorSalesConsultant = Position.builder().name("Старший продавец-консультант").build();
            Position leader = Position.builder().name("Ведущий продавец").build();

            Employee horoshilov = Employee.builder().name("Сергей").surname("Хорошилов").patronymic("Гаврилович")
                    .position(salesConsultant).gender(Gender.MALE).storeId(1L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1997, 12, 20)
                            )
                    ).build();

            Employee sergeyKarpov = Employee.builder().name("Валентина").surname("Констатинова").patronymic("Петровна")
                    .position(juniorSalesConsultant).gender(Gender.FEMALE).storeId(1L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1999, 7, 10)
                            )
                    ).build();

            Employee antoninaKuplina = Employee.builder().name("Евгений").surname("Сергеев").patronymic("Петрович")
                    .position(seniorSalesConsultant).gender(Gender.MALE).storeId(2L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1997, 3, 1)
                            )
                    ).build();

            Employee marinaVorohina = Employee.builder().name("Валентин").surname("Петров").patronymic("Констатинович")
                    .position(leader).gender(Gender.MALE).storeId(2L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1993, 5, 21)
                            )
                    ).build();

            Employee vladislavKruglov = Employee.builder().name("Петр").surname("Волошенко").patronymic("Борисович")
                    .position(stager).gender(Gender.MALE).storeId(3L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(2002, 4, 5)
                            )
                    ).build();

            Employee victoriaMirokhina = Employee.builder().name("Александра").surname("Иванова").patronymic("Сергеевна")
                    .position(leader).gender(Gender.FEMALE).storeId(3L)
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1990, 8, 15)
                            )
                    ).build();

            Employee mongush = Employee.builder().surname("Монгуш").name("Алексей").patronymic("Вячеславович")
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1993, 3, 3)
                            )
                    ).position(seniorSalesConsultant).storeId(4L).gender(Gender.MALE).build();

            Employee sidorov = Employee.builder().surname("Сидоров").name("Виктор").patronymic("Денисович")
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(2001, 6, 2)
                            )
                    ).position(juniorSalesConsultant).storeId(4L).gender(Gender.MALE).build();

            Employee victorov = Employee.builder().surname("Викторов").name("Тимур").patronymic("Викторович")
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(2000, 3, 17)
                            )
                    ).position(stager).storeId(5L).gender(Gender.MALE).build();

            Employee mironov = Employee.builder().surname("Миронов").name("Матвей").patronymic("Аркадьевич")
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(2000, 3, 10)
                            )
                    ).position(juniorSalesConsultant).storeId(5L).gender(Gender.MALE).build();

            Employee kuprianov = Employee.builder().surname("Куприянов").name("Артем").patronymic("Вячеславович")
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1999, 5, 17)
                            )
                    ).position(salesConsultant).storeId(6L).gender(Gender.MALE).build();

            Employee petuhov = Employee.builder().surname("Петухов").name("Борис").patronymic("Петрович")
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1994, 7, 10)
                            )
                    ).position(seniorSalesConsultant).storeId(2L).gender(Gender.MALE).build();

            Employee sherbakova = Employee.builder().surname("Щербакова").name("Евгения").patronymic("Борисовна")
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1997, 3, 1)
                            )
                    ).position(leader).storeId(5L).gender(Gender.FEMALE).build();

            Employee leonov = Employee.builder().surname("Леонов").name("Сергей").patronymic("Дмитриевич")
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(1993, 5, 21)
                            )
                    ).position(salesConsultant).storeId(6L).gender(Gender.MALE).build();

            Employee gordienko = Employee.builder().surname("Гордиенко").name("Мирон").patronymic("Лаврентьевич")
                    .birthDate(
                            Date.valueOf(
                                    LocalDate.of(2002, 4, 5)
                            )
                    ).position(juniorSalesConsultant).storeId(2L).gender(Gender.MALE).build();

            ArrayList<Employee> employees = new ArrayList<>(
                    List.of(sergeyKarpov, vladislavKruglov, victoriaMirokhina,
                            marinaVorohina, antoninaKuplina, horoshilov, mongush, sidorov, victorov, mironov,
                            kuprianov, petuhov, sherbakova,leonov, gordienko
                    )
            );

            log.info(
                    "Position added: {}", positionRepository.saveAll(
                            List.of(juniorSalesConsultant, salesConsultant, seniorSalesConsultant, stager, leader)
                    )
            );
            log.info(
                    "Employee added: {}", employeeRepository.saveAll(employees)
            );

            log.info(
                    "Employee product types added: {}", employeeProductTypeRepository.saveAll(
                            List.of(
                                    EmployeeProductType.builder().employeeId(1L).productTypeId(1L).build(),
                                    EmployeeProductType.builder().employeeId(1L).productTypeId(2L).build(),
                                    EmployeeProductType.builder().employeeId(1L).productTypeId(3L).build(),
                                    EmployeeProductType.builder().employeeId(1L).productTypeId(4L).build(),
                                    EmployeeProductType.builder().employeeId(2L).productTypeId(6L).build(),
                                    EmployeeProductType.builder().employeeId(2L).productTypeId(7L).build(),
                                    EmployeeProductType.builder().employeeId(2L).productTypeId(8L).build(),
                                    EmployeeProductType.builder().employeeId(3L).productTypeId(3L).build(),
                                    EmployeeProductType.builder().employeeId(3L).productTypeId(4L).build(),
                                    EmployeeProductType.builder().employeeId(3L).productTypeId(5L).build(),
                                    EmployeeProductType.builder().employeeId(3L).productTypeId(2L).build(),
                                    EmployeeProductType.builder().employeeId(4L).productTypeId(9L).build(),
                                    EmployeeProductType.builder().employeeId(4L).productTypeId(10L).build(),
                                    EmployeeProductType.builder().employeeId(4L).productTypeId(11L).build(),
                                    EmployeeProductType.builder().employeeId(5L).productTypeId(3L).build(),
                                    EmployeeProductType.builder().employeeId(6L).productTypeId(1L).build(),
                                    EmployeeProductType.builder().employeeId(6L).productTypeId(2L).build(),
                                    EmployeeProductType.builder().employeeId(6L).productTypeId(4L).build(),
                                    EmployeeProductType.builder().employeeId(6L).productTypeId(5L).build(),
                                    EmployeeProductType.builder().employeeId(7L).productTypeId(7L).build(),
                                    EmployeeProductType.builder().employeeId(7L).productTypeId(8L).build(),
                                    EmployeeProductType.builder().employeeId(8L).productTypeId(10L).build(),
                                    EmployeeProductType.builder().employeeId(8L).productTypeId(11L).build(),
                                    EmployeeProductType.builder().employeeId(8L).productTypeId(2L).build(),
                                    EmployeeProductType.builder().employeeId(10L).productTypeId(9L).build(),
                                    EmployeeProductType.builder().employeeId(10L).productTypeId(10L).build(),
                                    EmployeeProductType.builder().employeeId(10L).productTypeId(11L).build(),
                                    EmployeeProductType.builder().employeeId(11L).productTypeId(6L).build(),
                                    EmployeeProductType.builder().employeeId(11L).productTypeId(7L).build(),
                                    EmployeeProductType.builder().employeeId(12L).productTypeId(6L).build(),
                                    EmployeeProductType.builder().employeeId(12L).productTypeId(7L).build(),
                                    EmployeeProductType.builder().employeeId(12L).productTypeId(9L).build(),
                                    EmployeeProductType.builder().employeeId(13L).productTypeId(3L).build(),
                                    EmployeeProductType.builder().employeeId(13L).productTypeId(4L).build(),
                                    EmployeeProductType.builder().employeeId(13L).productTypeId(5L).build(),
                                    EmployeeProductType.builder().employeeId(14L).productTypeId(1L).build(),
                                    EmployeeProductType.builder().employeeId(14L).productTypeId(2L).build(),
                                    EmployeeProductType.builder().employeeId(15L).productTypeId(10L).build(),
                                    EmployeeProductType.builder().employeeId(15L).productTypeId(10L).build()
                            )
                    )
            );
        };
    }
}
