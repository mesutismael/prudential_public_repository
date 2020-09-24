package com.esmael.prudential.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.esmael.prudential.model.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Emp ID",
            "Name",
            "Prefix",
            "First Name",
            "Last Name",
            "Gender",
            "E Mail",
            "Date of Birth",
            "Date of Joining",
            "Quarter of Joining",
            "Salary",
            "Phone No.",
            "Posted Region"};
    static String SHEET = "10000EmployeeRecords";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Employee> excelToObjects(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Employee> employees = new ArrayList<Employee>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Employee employee = new Employee();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            employee.setEmployeeId(currentCell.getNumericCellValue());
                            break;

                        case 1:
                            employee.setPrefix(currentCell.getStringCellValue());
                            break;


                        case 2:
                            employee.setFirstName(currentCell.getStringCellValue());
                            break;

                        case 3:
                            employee.setLastName(currentCell.getStringCellValue());
                            break;

                        case 4:
                            employee.setGender(currentCell.getStringCellValue());
                            break;

                        case 5:
                            employee.seteMail(currentCell.getStringCellValue());
                            break;

                        case 6:
                            employee.setDateOfBirth(currentCell.getDateCellValue());
                            break;

                        case 7:
                            employee.setDateOfJoining(currentCell.getDateCellValue());
                            break;

                        case 8:
                            employee.setQuarterOfJoining(currentCell.getStringCellValue());
                            break;

                        case 9:
                            employee.setSalary(currentCell.getNumericCellValue());
                            break;

                        case 10:
                            employee.setPhoneNo(currentCell.getStringCellValue());
                            break;

                        case 11:
                            employee.setPostedRegion(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                employees.add(employee);
            }

            workbook.close();

            return employees;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }


    public static void ObjectsToExcel(List<Employee> employees) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("employe db");

            XSSFRow row = spreadsheet.createRow(1);
            XSSFCell cell;
            cell = row.createCell(1);
            cell.setCellValue("EMP ID");
            cell = row.createCell(2);
            cell.setCellValue("First Name");
            cell = row.createCell(3);
            cell.setCellValue("Last Name");
            cell = row.createCell(4);
            cell.setCellValue("Email");
            cell = row.createCell(5);
            cell.setCellValue("Date of Birth");
            cell = row.createCell(6);
            cell.setCellValue("Gender");
            cell = row.createCell(7);
            cell.setCellValue("Phone number");
            cell = row.createCell(8);
            cell.setCellValue("Prefix");
            cell = row.createCell(9);
            cell.setCellValue("Quarter of joining");
            cell = row.createCell(10);
            cell.setCellValue("Posted Region");
            cell = row.createCell(11);
            cell.setCellValue("Salary");
            cell = row.createCell(12);
            cell.setCellValue("Date of joining");

            int i = 2;
            for (Employee employee : employees) {
                i++;
                row = spreadsheet.createRow(i);
                cell = row.createCell(1);
                cell.setCellValue(employee.getEmployeeId());
                cell = row.createCell(2);
                cell.setCellValue(employee.getFirstName());
                cell = row.createCell(3);
                cell.setCellValue(employee.getLastName());
                cell = row.createCell(4);
                cell.setCellValue(employee.geteMail());
                cell = row.createCell(5);
                cell.setCellValue(employee.getDateOfBirth());
                cell = row.createCell(6);
                cell.setCellValue(employee.getGender());
                cell = row.createCell(7);
                cell.setCellValue(employee.getPhoneNo());
                cell = row.createCell(8);
                cell.setCellValue(employee.getPrefix());
                cell = row.createCell(9);
                cell.setCellValue(employee.getQuarterOfJoining());
                cell = row.createCell(10);
                cell.setCellValue(employee.getPostedRegion());
                cell = row.createCell(11);
                cell.setCellValue(employee.getSalary());
                cell = row.createCell(12);
                cell.setCellValue(employee.getDateOfJoining());
            }

            FileOutputStream out = new FileOutputStream(new File("exceldatabase.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("exceldatabase.xlsx written successfully");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
