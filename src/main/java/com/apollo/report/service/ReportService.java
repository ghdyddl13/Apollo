package com.apollo.report.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apollo.member.dao.MemberDAO;
import com.apollo.task.dao.TaskDAO;
import com.apollo.vo.ProjectDTO;
import com.apollo.vo.TaskDTO;


@Service
public class ReportService {

	@Autowired
	private SqlSession session;
	
		public ArrayList<ProjectDTO> gerUserProjects(String mid){

			System.out.println("gerUserProjects 서비스 실행");
			System.out.println("mid 테스트 : " + mid);
			
			MemberDAO dao = session.getMapper(MemberDAO.class);
			ArrayList<ProjectDTO> list = new ArrayList();
			list = dao.gerUserProjects(mid);
			
			return list;
		}
	
	
		public void writeData(int pid, String report_kind, String report_title) throws Exception {

			try {

				String filePath = "C:\\Apollo_Reports\\"; // file 생성 위치 
				String filename = report_title + ".xls"; // 생성될 파일 이름
				FileOutputStream fout = setFile(filePath, filename);
				HSSFWorkbook wb = setExcel(pid, report_kind, filename);
				
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

		private HSSFWorkbook setExcel(int pid, String report_kind, String report_title) throws IOException {

			// 엑셀 파일 생성
			HSSFWorkbook wb = new HSSFWorkbook();

			// 쉬트 및 폰트 지정
			HSSFSheet sht = wb.createSheet(report_title);
			sht.setGridsPrinted(true);
			sht.setFitToPage(true);
			sht.setDisplayGuts(true);
			
			HSSFRow row = null;
			HSSFCell cell = null;

			// 쉬트 이름 주기
			wb.setSheetName(0, report_title);

			// 데이터 생성
			////////////////////////////////////////////////////
			
			System.out.println(pid);
			System.out.println(report_kind);
			
			TaskDAO taskdao = session.getMapper(TaskDAO.class);
			ArrayList<TaskDTO> tasklist = new ArrayList();
			
			if(report_kind.equals("report_progress")) {
				tasklist = taskdao.getOnProgressTasklist(pid);
			}
			else if(report_kind.equals("report_status")) {
				tasklist = taskdao.getTasklistByPidOrderByStatus(pid);
			}
			else if(report_kind.equals("report_expired")) {
				tasklist = taskdao.getExpiredTasklist(pid);
			}
			else if(report_kind.equals("report_drawnear")) {
				tasklist = taskdao.getDrawnearTasklist(pid);
			}
			else if(report_kind.equals("report_unassigned")) {
				tasklist = taskdao.getUnassingedTasklist(pid);
			}
			
			////////////////////////////////////////////////////
			
			// 제목 줄 생성
			ArrayList<String> title = new ArrayList();
			if(report_kind.equals("report_status")) {
				
				title.add("Task이름");
				title.add("시작일");
				title.add("종료일");
				title.add("상세정보");
				title.add("생성시각");
				title.add("상태");
				
			} else {
				
				title.add("Task이름");
				title.add("시작일");
				title.add("종료일");
				title.add("상세정보");
				title.add("생성시각");
				
			}
			
			if(report_kind.equals("report_status")) {
				
			} else {
				
			}
			
			String[] contents = { "1", "공지합니다!!", "엄지용", "ddd", "dddd" };

			// row 1 table start
			row = sht.createRow((short) 1);
			row.setHeight((short) 1000); // 칼럼 높이

			// ========== title1 - first row ========================
			for (int i = 0; i < title.size(); i++) {
				if((i == 1) || (i ==2 )) {
					sht.setColumnWidth(i, 10 * 500); // Column 넓이 설정
				}
				else if(i==3) {
					sht.setColumnWidth(i, 10 * 1500); // Column 넓이 설정
				}
				else {
					sht.setColumnWidth(i, 10 * 600); // Column 넓이 설정
				}
				cell = row.createCell(i);
				cell.setCellValue(new HSSFRichTextString(title.get(i)));

				cell.setCellStyle(getTitleStyle(wb));
			}

			// =========== Table Contents ===================
			row = sht.createRow(2);
			row.setHeight((short) 500); // 칼럼 높이

			for (int i = 0; i < contents.length; i++) {
				
				if((i == 1) || (i ==2 )) {
					sht.setColumnWidth(i, 10 * 500);
				}
				else if(i==3) {
					sht.setColumnWidth(i, 10 * 1500);
				}
				else {
					sht.setColumnWidth(i, 10 * 600);
				}
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
			hcs.setFillForegroundColor((short) HSSFColor.PALE_BLUE.index);
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
