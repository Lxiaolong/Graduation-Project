package cn.net.sunet.sunetcloud.utils;
/*
 * Copyright 2018. sunet corporation All rights reserved.
 * 作者： xiaolong
 * 日期： 2018/4/25
 * 邮箱： 623585001@qq.com
 * 网址： www.sunet.net.cn
 */

import cn.net.sunet.sunetcloud.domain.MaintainMalfunction;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author Lxiaolong
 */

public class CreatePdf {
    public void createPdf(MaintainMalfunction maintainMalfunction) throws DocumentException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH: mm: ss");
        String data = sdf.format(maintainMalfunction.getStartTime());
        Document document = new Document(PageSize.A4);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream
                ("src/main/resources/pdf/"+"maintain"+maintainMalfunction.getId().toString()+".pdf"));
        document.open();
        BaseFont baseFont = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Chunk chunk = new Chunk("维修单",
                new Font(baseFont, 24));

        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(chunk);
        paragraph.setSpacingBefore(100);
        paragraph.setSpacingAfter(100);
        PdfPTable table = new PdfPTable(2);
        PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(new Chunk("维修单id", new Font(baseFont, 16))));
        PdfPCell pdfPCell2 = new PdfPCell(new Paragraph(new Chunk(maintainMalfunction.getId().toString(), new Font(baseFont,
                16))));
        PdfPCell pdfPCell3 = new PdfPCell(new Paragraph(new Chunk("设备编码", new Font(baseFont, 16))));
        PdfPCell pdfPCell4 = new PdfPCell(new Paragraph(new Chunk(maintainMalfunction.getDeviceCode(), new Font
                (baseFont, 16))));
        PdfPCell pdfPCell5 = new PdfPCell(new Paragraph(new Chunk("设备名称", new Font(baseFont, 16))));
        PdfPCell pdfPCell6 = new PdfPCell(new Paragraph(new Chunk(maintainMalfunction.getDeviceName(), new Font(baseFont, 16))));
        PdfPCell pdfPCell7 = new PdfPCell(new Paragraph(new Chunk("责任人姓名", new Font(baseFont, 16))));
        PdfPCell pdfPCell8 = new PdfPCell(new Paragraph(new Chunk(maintainMalfunction.getAccountName(), new Font(baseFont, 16))));
        PdfPCell pdfPCell9 = new PdfPCell(new Paragraph(new Chunk("责任人电话", new Font(baseFont, 16))));
        PdfPCell pdfPCell10 = new PdfPCell(new Paragraph(new Chunk(maintainMalfunction.getAccountPhone(), new Font(baseFont, 16))));
        PdfPCell pdfPCell11 = new PdfPCell(new Paragraph(new Chunk("故障代码", new Font(baseFont, 16))));
        PdfPCell pdfPCell12 = new PdfPCell(new Paragraph(new Chunk(maintainMalfunction.getCode() + " ", new Font(baseFont, 16))));
        PdfPCell pdfPCell13 = new PdfPCell(new Paragraph(new Chunk("故障时间", new Font(baseFont, 16))));
        PdfPCell pdfPCell14 = new PdfPCell(new Paragraph(new Chunk(data, new Font(baseFont, 16))));
        pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell8.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell9.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell10.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell12.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell13.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell14.setHorizontalAlignment(Element.ALIGN_CENTER);

        pdfPCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(pdfPCell1).setMinimumHeight(40);
        table.addCell(pdfPCell2);
        table.addCell(pdfPCell3).setMinimumHeight(40);
        table.addCell(pdfPCell4);
        table.addCell(pdfPCell5).setMinimumHeight(40);
        table.addCell(pdfPCell6);
        table.addCell(pdfPCell7).setMinimumHeight(40);
        table.addCell(pdfPCell8);
        table.addCell(pdfPCell9).setMinimumHeight(40);
        table.addCell(pdfPCell10);
        table.addCell(pdfPCell11).setMinimumHeight(40);
        table.addCell(pdfPCell12);
        table.addCell(pdfPCell13).setMinimumHeight(40);
        table.addCell(pdfPCell14);
        table.setSpacingAfter(60);
        Image image = Image.getInstance("src/main/resources/static/yinzhang.png");
        image.setIndentationLeft(320);
        document.add(paragraph);
        document.add(table);
        document.add(image);
        document.close();
    }
}
