package com.restaurant.repositories;

import com.restaurant.entity.SalesReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesReportRepository extends JpaRepository<SalesReport, Long> {
}
