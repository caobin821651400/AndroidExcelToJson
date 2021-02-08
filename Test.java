

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import RowDataBean;


public class
Test {

    private static List<RowDataBean> mList = new ArrayList<>();

    public static void test(Context context) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("111.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            //获取表格的第1个sheet
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            //遍历行
            for (int r = 0; r < rowsCount; r++) {
                if (r == 4) break;
                if (r == 0) continue;
                RowDataBean rowDataBean = new RowDataBean();
                Row row = sheet.getRow(r);

                rowDataBean.setId(r + "");

                int cellsCount = row.getPhysicalNumberOfCells();
                List<RowDataBean.ColumnDataBean> columnList = new ArrayList<>();
                //遍历列
                for (int c = 0; c < cellsCount; c++) {
                    if (c == 5) break;
                    if (c == 0) continue;
                    String language = getCellAsString(sheet.getRow(0), c, formulaEvaluator);
                    String value = getCellAsString(row, c, formulaEvaluator);

                    String cellInfo = "r:" + r + "; c:" + c + "; v:" + value;

                    RowDataBean.ColumnDataBean columnDataBean = new RowDataBean.ColumnDataBean(c + "", r + "");
                    columnDataBean.setLanguage(language);
                    columnDataBean.setValue(value);
                    columnList.add(columnDataBean);
             
                }
                rowDataBean.setDataBean(columnList);
                mList.add(rowDataBean);
            }


            //获取表格的第2个sheet
            XSSFSheet sheet2 = workbook.getSheetAt(1);
            int rowsCount2 = sheet2.getPhysicalNumberOfRows();
            //遍历行
            for (int r = 0; r < rowsCount2; r++) {
                if (r == 4) break;
                if (r == 0) continue;
                RowDataBean rowDataBean = mList.get(r-1);
//                RowDataBean rowDataBean = new RowDataBean();
                Row row = sheet2.getRow(r);

//                rowDataBean.setId(r + "");

                int cellsCount = row.getPhysicalNumberOfCells();
                List<RowDataBean.ColumnShortDataBean> columnList = new ArrayList<>();
                //遍历列
                for (int c = 0; c < cellsCount; c++) {
                    if (c == 5) break;
                    if (c == 0) continue;
                    String language = getCellAsString(sheet2.getRow(0), c, formulaEvaluator);
                    String value = getCellAsString(row, c, formulaEvaluator);

                    String cellInfo = "r:" + r + "; c:" + c + "; v:" + value;

                    RowDataBean.ColumnShortDataBean columnDataBean = new RowDataBean.ColumnShortDataBean(c + "", r + "");
                    columnDataBean.setLanguage(language);
                    columnDataBean.setValue(value);
                    columnList.add(columnDataBean);
                   
                }
                rowDataBean.setShortDataBean(columnList);
//                mList.add(rowDataBean);
            }


          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断类型并返回值
     *
     * @param row
     * @param c
     * @param formulaEvaluator
     * @return
     */
    protected static String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = "" + cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter =
                                new SimpleDateFormat("dd/MM/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    } else {
                        value = "" + numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = "" + cellValue.getStringValue();
                    break;
                default:
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }
}