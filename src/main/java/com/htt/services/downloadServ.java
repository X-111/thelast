package com.htt.services;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import com.htt.pojo.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class downloadServ {

    // 显示的导出Excel表的标题（通常第一行）
    private String title;
    // 导出Excel表的列名（通常第二行）
    private String[] rowName;
    // 导出Excel表的文件名
    private String fileName;

    // 导出Excel表的文件主题内容（通常从第三行往下的内容）
    // 需要注意的是：主要内容的格式为第一列为数字（序号），从第二列开始都是字符串
    // 每一行为Object[]类型，很多行用List包装起来
    private ArrayList<messageDownEnty> a;

    // 用于下载的response
    private HttpServletResponse response;

    // 构造方法，传入要导出的数据
    public downloadServ(String title, String[] rowName, ArrayList<messageDownEnty> a, HttpServletResponse response, String fileName) {
        this.rowName = rowName;
        this.title = title;
        this.a =a;
        this.response = response;
        this.fileName = fileName;
    }

    // 下载Excel的方法
    public void downloadExcel() throws Exception {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象
            HSSFSheet sheet = workbook.createSheet(title);                  // 创建工作表

            // 产生表格标题行
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);

            // sheet样式定义（两个函数均为自定义，可以自行修改）
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象
            HSSFCellStyle style = this.getStyle(workbook);                  //单元格样式对象

            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length)));
            cellTiltle.setCellStyle(columnTopStyle);
            cellTiltle.setCellValue(title);

            // 定义所需列数
            int columnNum =0;
            HSSFRow rowRowName = sheet.createRow(2);                // 在索引2的位置创建行(最顶端的行开始的第二行)

            // 将列头设置到sheet的单元格中
            HSSFCell cellRowName = rowRowName.createCell(columnNum);               //创建列头对应个数的单元格
            cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
            HSSFRichTextString text = new HSSFRichTextString("序号");
            cellRowName.setCellValue(text);                                 //设置列头单元格的值
            cellRowName.setCellStyle(columnTopStyle);
            for (int n = 0; n < 4; n++) {
                if(rowName[n]!=null)
                { cellRowName = rowRowName.createCell(columnNum+1);               //创建列头对应个数的单元格
                    cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型
                    text = new HSSFRichTextString(rowName[n]);
                    cellRowName.setCellValue(text);                                 //设置列头单元格的值
                    cellRowName.setCellStyle(columnTopStyle);
                    columnNum++;}
                //设置列头单元格样式
            }

            // 将查询出的数据设置到sheet对应的单元格中

            for(int i=0;i<a.size();i++)
            {
                HSSFRow row = sheet.createRow(i + 3);
                messageDownEnty e=a.get(i);
                for(int j=0;j<5;j++) {
                    if(j==0){HSSFCell cell = row.createCell(0, HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(i + 1);}
                   else{ HSSFCell cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(e.getBystring(rowName[j-1]));}

                }


                }

        columnNum=5;
            //让列宽随着导出的列长自动适应
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //当前行未被使用过
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }

            if (workbook != null) {
                try {
                    if (response != null) {
                        response.setContentType("application/vnd.ms-excel;charset=utf-8");
                        response.setHeader("Content-Disposition", "attachment;filename=\""+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
                        OutputStream out = response.getOutputStream();
                        workbook.write(out);
                        out.close();
                    } else {
                        FileOutputStream outputStream = new FileOutputStream("C:/"+fileName);
                        workbook.write(outputStream);
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 列头单元格样式
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 12);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("微软雅黑");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    // 列数据信息单元格样式
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体名字
        font.setFontName("微软雅黑");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }
}
