package com.karlchu.book.domain;

/**
 * Created by BVDEV75 on 12/27/2018.
 */
import java.util.Date;

public interface EmployeeRepositoryCustom {

    public long getMaxEmpId();

    public long updateEmployee(String empNo, String fullName, Date hireDate);

}
