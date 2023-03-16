package org.roberto;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Parametros {

    private FileInputStream archivo;
    private Workbook libroExcel;
    private Sheet hoja;
    public Parametros() {

    }

    public void loadData() throws IOException {

        this.archivo = new FileInputStream(new File(".\\src\\main\\resources\\parametros\\parametros.xlsx"));
        this.libroExcel = WorkbookFactory.create(archivo);

        this.hoja = libroExcel.getSheet("parametros");

    }

    public void cerrar() throws IOException {
        this.archivo.close();
    }

    public String getUser() {
        return hoja.getRow(3 - 1).getCell(2 -1).getStringCellValue();
    }

    public String getPassword() {
        return hoja.getRow(3-1).getCell(3-1).getStringCellValue();
    }

    public String getOrdenadoPor() {
        return hoja.getRow(6-1).getCell(2-1).getStringCellValue();
    }

    public Boolean getDesc() {
        return (int) hoja.getRow(6 - 1).getCell(3 - 1).getNumericCellValue() == 1;
    }

    public Integer getCantidadProducto() {
        return (int) hoja.getRow(6-1).getCell(5 -1 ).getNumericCellValue();
    }

    public String getNombre() {
        return hoja.getRow(11-1).getCell(2-1).getStringCellValue();
    }

    public String getApellido() {
        return hoja.getRow(11-1).getCell(3-1).getStringCellValue();
    }

    public String getCodigo() {
        return String.valueOf(hoja.getRow(11-1).getCell(4-1).getNumericCellValue());
    }

}
