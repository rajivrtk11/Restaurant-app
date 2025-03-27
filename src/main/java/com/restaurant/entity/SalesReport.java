package com.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales_report")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalesReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

//    segment => gov/private, country, product, discount band, units sold, manufacturing price, sale price,
//    gross sales, discounts, sales, cogs, profit, date, month number, month name, year
    String segment;
    String country;
    String product;
    String discountBand;
    String unitsSold;
    String manufacturingPrice;
    String salePrice;
    String grossSales;
    String discounts;
    String sales;
    String cogs;
    String profit;
    String date;
    String monthNumber;
    String monthName;
    String year;
}
