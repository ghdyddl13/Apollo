package com.apollo.report.service;

import java.io.File;
import org.apache.ibatis.session.SqlSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;


@Service
public class ReportService {

	@Autowired
	private SqlSession session;

		public void writeData() throws Exception {

			try {

				String filePath = "C:\\Apollo_Reports\\"; // file 생성 위치 
				String filename = "aaaaa.xls"; // 생성될 파일 이름
				FileOutputStream fout = setFile(filePath, filename);
				HSSFWorkbook wb = setExcel(filename);
				
				// write the workbook to the output stream
				wb.write(fout);
				fout.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private FileOutputStream setFile(String filePath, String filename) throws FileNotFoundException {

			// 엑셀 파일 생성
			// 디렉토리 없으면 생성.

			File fDir = new File(filePath);
			if (!fDir.exists()) {
				fDir.mkdirs();
			}

			FileOutputStream fout = new FileOutputStream(filePath + filename);
			return fout;
		}

		private HSSFWorkbook setExcel(String filename) throws IOException {

			// 엑셀 파일 생성
			HSSFWorkbook wb = new HSSFWorkbook();

			// 쉬트 및 폰트 지정
			HSSFSheet sht = wb.createSheet(filename);
			sht.setGridsPrinted(true);
			sht.setFitToPage(true);
			sht.setDisplayGuts(true);
			
			HSSFRow row = null;
			HSSFCell cell = null;


			// 쉬트 이름 주기
			wb.setSheetName(0, filename);

			// 제목 줄 생성
			String[] title1 = { "NO.", "제목", "작성자" };
			String[] title2 = { "NO.", "공지합니다!!", "엄지용" };
			String[] contents = { "1", "공지합니다!!", "엄지용" };
			int[] cellwidth = { 6, 20, 20 };


			// row 1 table start
			row = sht.createRow((short) 1);
			row.setHeight((short) 500); // 칼럼 높이
			short width = 265;



			// ========== title1 - first row ========================
			for (int i = 0; i < title1.length; i++) {
				sht.setColumnWidth(i, (cellwidth[i] * width)); // Column 넓이 설정
				cell = row.createCell(i);
				cell.setCellValue(new HSSFRichTextString(title1[i]));

				if (i == 1) {

					// ====== Cell 합병 ==================
					sht.addMergedRegion(new CellRangeAddress(1, 1, i, i + 1));

					// ==================================
				}

				cell.setCellStyle(getTitleStyle(wb));
			}


			// ===========title2 - Second row ====================
			row = sht.createRow(2);
			row.setHeight((short) 500); // 칼럼 높이

			for (int i = 0; i < title2.length; i++) {
				sht.setColumnWidth(i, (cellwidth[i] * width));
				cell = row.createCell((i));
				cell.setCellValue(new HSSFRichTextString(title2[i]));
				cell.setCellStyle(getTitleStyle(wb));

			}

			// =========== Table Contents ===================
			row = sht.createRow(3);
			row.setHeight((short) 500); // 칼럼 높이

			for (int i = 0; i < contents.length; i++) {
				sht.setColumnWidth(i, (cellwidth[i] * width));
				cell = row.createCell((i));

				if (i == 0)
					cell.setCellValue(new HSSFRichTextString(String.valueOf(i)));
				else
					cell.setCellValue(new HSSFRichTextString(contents[i]));
				cell.setCellStyle(getTextStyle(wb));
			}


			// 출력설정
			HSSFPrintSetup hps = sht.getPrintSetup();

			// 용지설정
			hps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);

			// 출력방향설정
			hps.setLandscape(false);

			// 출력비율설정
			hps.setScale((short) 100);

			// footer에 페이지번호 설정
			HSSFFooter footer = sht.getFooter();
			footer.setCenter(HSSFFooter.page() + "/" + HSSFFooter.numPages());

			// 쉬트 여백 설정
			sht.setMargin(HSSFSheet.TopMargin, 0.6);
			sht.setMargin(HSSFSheet.BottomMargin, 0.6);
			sht.setMargin(HSSFSheet.LeftMargin, 0.6);
			sht.setMargin(HSSFSheet.RightMargin, 0.6);

			return wb;

		}

		/*
		 * @@@@@ Font 설정 Method @@@@@
		 */

		private HSSFCellStyle getTitleStyle(HSSFWorkbook wb) {

			// 제목 폰트
			HSSFFont hf = wb.createFont();
			hf.setFontHeightInPoints((short) 8);
			hf.setColor((short) HSSFColor.BLACK.index);
			hf.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			// Header style setting
			HSSFCellStyle hcs = wb.createCellStyle();
			hcs.setFont(hf);
			hcs.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			// set border style
			hcs.setBorderBottom(HSSFCellStyle.BORDER_THICK);
			hcs.setBorderRight(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderTop(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderBottom(HSSFCellStyle.BORDER_THIN);

			// set color
			hcs.setFillBackgroundColor((short) HSSFColor.WHITE.index);
			hcs.setFillForegroundColor((short) HSSFColor.VIOLET.index);
			hcs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			hcs.setLocked(true);
			hcs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			return hcs;

		}

		/*
		 * @@@@@ Font 설정 Method @@@@@
		 */
		private HSSFCellStyle getTextStyle(HSSFWorkbook wb) {

			HSSFFont hf = wb.createFont();
			hf.setFontHeightInPoints((short) 8);
			hf.setColor((short) HSSFColor.BLACK.index);

			HSSFCellStyle hcs = wb.createCellStyle();
			hcs.setFont(hf);
			hcs.setAlignment(HSSFCellStyle.ALIGN_CENTER);


			// set border style
			hcs.setBorderBottom(HSSFCellStyle.BORDER_THICK);
			hcs.setBorderRight(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderTop(HSSFCellStyle.BORDER_THIN);
			hcs.setBorderBottom(HSSFCellStyle.BORDER_THIN);


			// set color
			hcs.setFillBackgroundColor((short) HSSFColor.WHITE.index);
			hcs.setFillForegroundColor((short) HSSFColor.WHITE.index);
			hcs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			hcs.setLocked(true);
			hcs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			return hcs;
		}
	}
