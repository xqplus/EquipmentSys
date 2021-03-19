package cn.xqplus.equipmentsys.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel 导入导出工具类
 */

public class ExcelUtils {

    /**
     * Excel 导出
     * @param exportList 导出的数据集合
     * @param title Excel 标题
     * @param sheetName sheet 名称
     * @param pojoClass 导出对应实体类
     * @param fileName 导出文件名
     * @param response http 响应
     */
    public static void exportExcel(List<?> exportList, String title, String sheetName,
                                   Class<?> pojoClass, String fileName, HttpServletResponse response) {
        // Excel 的参数
        ExportParams params = new ExportParams(title, sheetName);
        Workbook workbook = ExcelExportUtil.exportExcel(params, pojoClass, exportList);
        if (workbook != null) {
            doExport(fileName, response, workbook);
        }
    }

    /**
     * 基础导出方法
     * @param fileName 文件名
     * @param response HttpServletResponse
     * @param workbook 工作表
     */
    private static void doExport(String fileName, HttpServletResponse response, Workbook workbook) {

        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        try {
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
