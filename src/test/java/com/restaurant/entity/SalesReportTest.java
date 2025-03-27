package com.restaurant.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SalesReportTest {
    private SalesReport salesReport;

    @BeforeEach
    void setUp() {
//        1401	"16185"	"Canada"	"Wed Jan 01 00:00:00 IST 2014"	"None"	"0"	"32370"	"3"	"January"	"1"	"Carretera"	"16185"	"20"	"32370"	"Government"	"1618"	"2014"
        salesReport = SalesReport.builder().id(1L)
                .cogs("16185")
                .country("Canada")
                .date(new Date().toString())
                .discountBand("None")
                .discounts("0")
                .grossSales("32370")
                .manufacturingPrice("3")
                .monthName("January")
                .monthNumber("1")
                .product("Carretera")
                .profit("16185")
                .salePrice("20")
                .sales("32370")
                .segment("Government")
                .unitsSold("1618")
                .year("2014")
                .build();
    }

    @Test
    void getId() {
        assertEquals(1L, salesReport.getId());
    }

    @Test
    void getSegment() {
        assertEquals("Government", salesReport.getSegment());
    }

    @Test
    void getCountry() {
        assertEquals("Canada", salesReport.getCountry());
    }

    @Test
    void getProduct() {
        assertEquals("Carretera", salesReport.getProduct());
    }

    @Test
    void getDiscountBand() {
        assertEquals("None", salesReport.getDiscountBand());
    }

    @Test
    void getUnitsSold() {
        assertEquals("1618", salesReport.getUnitsSold());
    }

    @Test
    void getManufacturingPrice() {
        assertEquals("3", salesReport.getManufacturingPrice());
    }

    @Test
    void getSalePrice() {
        assertEquals("20", salesReport.getSalePrice());
    }

    @Test
    void getGrossSales() {
        assertEquals("32370", salesReport.getGrossSales());
    }

    @Test
    void getDiscounts() {
        assertEquals("0", salesReport.getDiscounts());
    }

    @Test
    void getSales() {
        assertEquals("32370", salesReport.getSales());
    }

    @Test
    void getCogs() {
        assertEquals("16185", salesReport.getCogs());
    }

    @Test
    void getProfit() {
        assertEquals("16185", salesReport.getProfit());
    }

    @Test
    void getDate() {
        salesReport.getDate();
    }

    @Test
    void getMonthNumber() {
        assertEquals("1", salesReport.getMonthNumber());
    }

    @Test
    void getMonthName() {
        assertEquals("January", salesReport.getMonthName());
    }

    @Test
    void getYear() {
        assertEquals("2014", salesReport.getYear());
    }

    @Test
    void setId() {
        salesReport.setId(1L);
        assertEquals(1L, salesReport.getId());
    }

    @Test
    void setSegment() {
        salesReport.setSegment("Government");
        assertEquals("Government", salesReport.getSegment());
    }

    @Test
    void setCountry() {
        salesReport.setCountry("Canada");
        assertEquals("Canada", salesReport.getCountry());
    }

    @Test
    void setProduct() {
        salesReport.setProduct("Carretera");
        assertEquals("Carretera", salesReport.getProduct());
    }

    @Test
    void setDiscountBand() {
        salesReport.setDiscountBand("None");
        assertEquals("None", salesReport.getDiscountBand());
    }

    @Test
    void setUnitsSold() {
        salesReport.setUnitsSold("1618");
        assertEquals("1618", salesReport.getUnitsSold());
    }

    @Test
    void setManufacturingPrice() {
        salesReport.setManufacturingPrice("3");
        assertEquals("3", salesReport.getManufacturingPrice());
    }

    @Test
    void setSalePrice() {
        salesReport.setSalePrice("20");
        assertEquals("20", salesReport.getSalePrice());
    }

    @Test
    void setGrossSales() {
        salesReport.setGrossSales("32370");
        assertEquals("32370", salesReport.getGrossSales());
    }

    @Test
    void setDiscounts() {
        salesReport.setDiscounts("0");
        assertEquals("0", salesReport.getDiscounts());
    }

    @Test
    void setSales() {
        salesReport.setSales("32370");
        assertEquals("32370", salesReport.getSales());
    }

    @Test
    void setCogs() {
        salesReport.setCogs("16185");
        assertEquals("16185", salesReport.getCogs());
    }

    @Test
    void setProfit() {
        salesReport.setProfit("16185");
        assertEquals("16185", salesReport.getProfit());
    }

    @Test
    void setDate() {
        salesReport.setDate(new Date().toString());
    }

    @Test
    void setMonthNumber() {
        salesReport.setMonthNumber("1");
        assertEquals("1", salesReport.getMonthNumber());
    }

    @Test
    void setMonthName() {
        salesReport.setMonthName("January");
        assertEquals("January", salesReport.getMonthName());
    }

    @Test
    void setYear() {
        salesReport.setYear("2014");
        assertEquals("2014", salesReport.getYear());
    }

    @Test
    void testEquals() {
        SalesReport salesReport1 = SalesReport.builder().id(1L)
                .cogs("16185")
                .country("Canada")
                .date(new Date().toString())
                .discountBand("None")
                .discounts("0")
                .grossSales("32370")
                .manufacturingPrice("3")
                .monthName("January")
                .monthNumber("1")
                .product("Carretera")
                .profit("16185")
                .salePrice("20")
                .sales("32370")
                .segment("Government")
                .unitsSold("1618")
                .year("2014")
                .build();

        salesReport.getSales().equals(salesReport1.getSales());
        salesReport.getYear().equals(salesReport1.getYear());
        salesReport.getCogs().equals(salesReport1.getCogs());
        salesReport.getCogs().equals(salesReport1.getCogs());
        salesReport.getSales().equals(salesReport1.getSales());
    }

    @Test
    void testHashCode() {
        SalesReport salesReport1 = SalesReport.builder().id(1L)
                .cogs("16185")
                .country("Canada")
                .date(new Date().toString())
                .discountBand("None")
                .discounts("0")
                .grossSales("32370")
                .manufacturingPrice("3")
                .monthName("January")
                .monthNumber("1")
                .product("Carretera")
                .profit("16185")
                .salePrice("20")
                .sales("32370")
                .segment("Government")
                .unitsSold("1618")
                .year("2014")
                .build();

        SalesReport salesReport2 = SalesReport.builder().id(1L)
                .cogs("16185")
                .country("Canada")
                .date(new Date().toString())
                .discountBand("None")
                .discounts("0")
                .grossSales("32370")
                .manufacturingPrice("3")
                .monthName("January")
                .monthNumber("1")
                .product("Carretera")
                .profit("16185")
                .salePrice("20")
                .sales("32370")
                .segment("Government")
                .unitsSold("1618")
                .year("2014")
                .build();

        SalesReport salesReport3 = SalesReport.builder().id(2L)
                .cogs("16185")
                .country("Canada")
                .date(new Date().toString())
                .discountBand("None")
                .discounts("0")
                .grossSales("32370")
                .manufacturingPrice("3")
                .monthName("January")
                .monthNumber("1")
                .product("Carretera")
                .profit("16185")
                .salePrice("20")
                .sales("32370")
                .segment("Government")
                .unitsSold("1618")
                .year("2014")
                .build();

        SalesReport salesReport4 = SalesReport.builder().id(1L)
                .cogs("16185")
                .country("Canada")
                .date(new Date().toString())
                .discountBand("None")
                .discounts("0")
                .grossSales("32370")
                .manufacturingPrice("3")
                .monthName("January")
                .monthNumber("1")
                .product("Carretera")
                .profit("16185")
                .salePrice("20")
                .sales("32370")
                .segment("Government")
                .unitsSold("1618")
                .year("2014")
                .build();

        SalesReport salesReport5 = SalesReport.builder().id(1L)
                .cogs("16185")
                .country("Canada")
                .date(new Date().toString())
                .discountBand("None")
                .discounts("0")
                .grossSales("32370")
                .manufacturingPrice("3")
                .monthName("January")
                .monthNumber("1")
                .product("Carretera")
                .profit("16185")
                .salePrice("20")
                .sales("32370")
                .segment("Government")
                .unitsSold("1618")
                .year("2014")
                .build();

        Object nonUserObject = "Some String"; // Completely different type

        // Self-equality
        assertEquals(salesReport1, salesReport1, "Object should be equal to itself");

        // Equality with same values
        assertEquals(salesReport1, salesReport2, "Objects with same field values should be equal");
        assertEquals(salesReport1.hashCode(), salesReport2.hashCode(), "Hash codes should match for equal objects");

        // Inequality with different field values
        assertNotEquals(salesReport1, salesReport3, "Objects with different ID and name should not be equal");
//        assertNotEquals(user1, user4, "Objects with same ID but different roles should not be equal");
//        assertNotEquals(user1, user5, "Objects with same ID but different task list should not be equal");

        // Null and different type checks
        assertNotEquals(salesReport1, null, "Object should not be equal to null");
        assertNotEquals(salesReport1, nonUserObject, "Object should not be equal to a different class");

        // Hashcode differences for distinct objects
        assertNotEquals(salesReport1.hashCode(), salesReport3.hashCode(), "Different objects should have different hash codes");

        // `canEqual` validation (ensures correct subclass handling)
        assertTrue(salesReport1.canEqual(salesReport2), "Users with same properties should pass canEqual check");
        assertFalse(salesReport1.canEqual(nonUserObject), "Users should not be equal to a non-user object");

    }

    @Test
    void testToString() {
        assertNotNull(salesReport.toString());
    }
}